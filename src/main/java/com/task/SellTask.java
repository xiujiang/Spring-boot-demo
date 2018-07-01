package com.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.OrderInfoBean;
import com.bean.TradeBean;
import com.constant.TradeConstant;
import com.dao.OrderDao;
import com.entity.Order;
import com.service.OkexService;
import com.service.TradeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by joel on 2018/1/13.
 */
//@Component
public class SellTask extends AbstractTask{
    @Resource
    OkexService okexService;
    @Resource
    OrderDao orderDao;
    private static final OrderInfoBean defaultOrderInfoBean;
    private static final OrderInfoBean orderInfoBean;
    private static final TradeBean tradeBean;
    private static final BigDecimal TradeAllPrice = new BigDecimal(20);
    private static final BigDecimal priceRate = new BigDecimal(1.04);       //买卖费率

    static {
        defaultOrderInfoBean = new OrderInfoBean(TradeConstant.api,TradeConstant.symbol,"-1");
        orderInfoBean = new OrderInfoBean(TradeConstant.api,TradeConstant.symbol,"");
        tradeBean = new TradeBean(TradeConstant.api,TradeConstant.symbol,"",new BigDecimal(0),new BigDecimal(0));
    }
    @Scheduled(cron = "0/1 * * * * ? ")
    @Override
    public void execute() throws Exception {
        if("true".equals(TradeConstant.closeTradeSign)){
            return;
        }
        BigDecimal TradePrice = TradeAllPrice.divide(new BigDecimal(10),2,BigDecimal.ROUND_HALF_UP);

        //查询是否有卖单信息
       List<Order> sellOrders = orderDao.findByStatusAndType("0","sell");
       for(Order order:sellOrders){
           orderInfoBean.setOrderId(order.getOrderId());
           Map<String,String> maps = okexService.orderInfo(orderInfoBean);
           String result = maps.get("result");
           if("true".equals(result) && !StringUtils.isEmpty(maps.get("orders"))) {
               String orderList = maps.get("orders");
               JSONArray jsonArray = JSONArray.parseArray(orderList);
               if(jsonArray == null || jsonArray.size() == 0){
                   return;
               }
               JSONObject json = (JSONObject) jsonArray.get(0);
               //已经成交完成，回写内容，并且卖出
               if ("2".equals(json.get("status"))) {
                   order.setStatus("2");
                   orderDao.save(order);
               }
           }
       }
        //查询是否有买单成功
        List<Order> orders = orderDao.findByStatusAndType("0","buy");
        if(orders.size() == 0){
            return;
        }
        Order order = orders.get(0);
        orderInfoBean.setOrderId(order.getOrderId());
        Map<String,String> maps = okexService.orderInfo(orderInfoBean);
        String result = maps.get("result");
        if("true".equals(result) && !"[]".equals(maps.get("orders"))){
           String orderList = maps.get("orders");
            JSONArray jsonArray = JSONArray.parseArray(orderList);
            JSONObject json = jsonArray.getJSONObject(0);
            System.out.println("当前订单号："+order.getOrderId()+",是否买入成功:"+("2".equals(json.get("status").toString())?"是":"否"));
            //已经成交完成，回写内容，并且卖出
            if("2".equals(json.get("status").toString())){
                order.setStatus("2");
                orderDao.save(order);
                System.out.println("开始卖出订单"+order.getOrderId());
                //卖出订单
                Map<String,String> tickerMap = okexService.ticker(defaultOrderInfoBean.getSymbol());
                String ticker = tickerMap.get("ticker");
                JSONObject tickerJson = JSONObject.parseObject(ticker);
                BigDecimal lastPrice = new BigDecimal(tickerJson.get("last").toString());
                BigDecimal fourPrice = order.getPrice().multiply(new BigDecimal(1.04));

                System.out.println("当前买入价格为:"+order.getPrice()+",当前last价格为："+tickerJson.get("last").toString()+",上浮价格为:"+fourPrice);
                //优先高价格卖出
                if(lastPrice.compareTo(fourPrice) >= 0){
                    tradeBean.setPrice(lastPrice);
                }else{
                    tradeBean.setPrice(fourPrice);
                }
                Double amount = Double.parseDouble(order.getAmount());
                tradeBean.setAmount(new BigDecimal(amount-0.5));
                tradeBean.setType("sell");
                Map<String,String> tradeMap = okexService.SellOrBuyTrade(tradeBean);
                String tradeResult = tradeMap.get("result");
                if(!StringUtils.isEmpty(tradeResult) && "true".equals(tradeResult)){
                    //插入数据库
                    String orderId = tradeMap.get("order_id");
                    System.out.println("下单成功:订单号"+orderId);
                    Order sellOrder = new Order();
                    sellOrder.setType("sell");
                    sellOrder.setPrice(tradeBean.getPrice());
                    sellOrder.setAmount(tradeBean.getAmount().toString());
                    sellOrder.setOrderId(orderId);
                    sellOrder.setStatus("0");
                    sellOrder.setSymbol(defaultOrderInfoBean.getSymbol());
                    sellOrder.setTotal(tradeBean.getAmount().multiply(tradeBean.getPrice()).toString());
                    sellOrder.setCreateTime(new Date());
                    sellOrder.setLastUpdateTime(new Date());
                    orderDao.save(sellOrder);
                }
            }
        }
    }


    /**
     * 判断是否可以卖出，以及卖出价格
     * @param oldPrice
     * @param newPrice
     * @return
     */
    public BigDecimal sellPrice(BigDecimal oldPrice,BigDecimal newPrice){
        BigDecimal zero = new BigDecimal(0);
        if(oldPrice.compareTo(newPrice) > -1 ){
            return null;
        }
        BigDecimal sellPrice = oldPrice.multiply(priceRate);        //0.5
        if(sellPrice.compareTo(newPrice) != -1){             //0.6
            return sellPrice;
        }else{
            return newPrice;
        }
    }

  /*  public static void main(String[] args) throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, null, null);

        SSLSocketFactory factory = (SSLSocketFactory) context.getSocketFactory();
        SSLSocket socket = (SSLSocket) factory.createSocket();

        String[] protocols = socket.getSupportedProtocols();

        System.out.println("Supported Protocols: " + protocols.length);
        for (int i = 0; i < protocols.length; i++) {
            System.out.println(" " + protocols[i]);
        }

        protocols = socket.getEnabledProtocols();

        System.out.println("Enabled Protocols: " + protocols.length);
        for (int i = 0; i < protocols.length; i++) {
            System.out.println(" " + protocols[i]);
        }

    }*/
}
