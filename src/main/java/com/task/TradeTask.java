package com.task;

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
import java.util.Map;

/**
 * Created by joel on 2018/1/13.
 */
//@Component
public class TradeTask extends AbstractTask {

    @Resource
    OkexService okexService;
    @Resource
    OrderDao orderDao;
    private static final OrderInfoBean defaultOrderInfoBean;
    private static final OrderInfoBean orderInfoBean;
    private static final TradeBean tradeBean;
    private static final BigDecimal TradeAllPrice = new BigDecimal(20);
    static {
        defaultOrderInfoBean = new OrderInfoBean(TradeConstant.api,TradeConstant.symbol,"-1");
        orderInfoBean = new OrderInfoBean(TradeConstant.api,TradeConstant.symbol,"");
        tradeBean = new TradeBean(TradeConstant.api,TradeConstant.symbol,"",new BigDecimal(0),new BigDecimal(0));
    }
    @Scheduled(cron = "0/10 * * * * ? ")
    @Override
    public void execute() throws Exception {
        if("true".equals(TradeConstant.closeTradeSign)){
            System.out.println("关闭系统");
            return;
        }
        //不做批量
//         BigDecimal TradePrice = TradeAllPrice.divide(new BigDecimal(10),2,BigDecimal.ROUND_HALF_UP);
        BigDecimal TradePrice = new BigDecimal(1);
        //获取当前未完成订单信息
        Map<String,String> orderMaps = okexService.orderInfo(defaultOrderInfoBean);
        String orderInfoResult = orderMaps.get("result");
        String orders = orderMaps.get("orders");
        if("[]".equals(orders)){
            //没有订单信息，则进行买卖操作
            System.out.println("没有订单信息,执行买入操作");
            Map<String,String> tickerMap = okexService.ticker(defaultOrderInfoBean.getSymbol());
            String ticker = tickerMap.get("ticker");
            JSONObject tickerJson = JSONObject.parseObject(ticker);
            if(TradeConstant.highPrice .compareTo(new BigDecimal(tickerJson.get("last").toString())) != -1){
                TradeConstant.closeTradeSign = "true";
                System.out.println("当前价格过高，关闭系统");
                return;
            }
            tradeBean.setPrice(new BigDecimal(tickerJson.get("last").toString()));
            tradeBean.setAmount(TradePrice.divide(tradeBean.getPrice(),6,BigDecimal.ROUND_HALF_UP));
            tradeBean.setType("buy");

            Map<String,String> tradeMap = okexService.SellOrBuyTrade(tradeBean);
            String tradeResult = tradeMap.get("result");
            if(!StringUtils.isEmpty(tradeResult) && "true".equals(tradeResult)){
                //插入数据库
                String orderId = tradeMap.get("order_id");
                System.out.println("下单成功:订单号"+orderId);
                Order order = new Order();
                order.setType("buy");
                order.setPrice(tradeBean.getPrice());
                order.setAmount(tradeBean.getAmount().toString());
                order.setOrderId(orderId);
                order.setStatus("0");
                order.setSymbol(defaultOrderInfoBean.getSymbol());
                order.setTotal(tradeBean.getAmount().multiply(tradeBean.getPrice()).toString());
                order.setCreateTime(new Date());
                order.setLastUpdateTime(new Date());
                orderDao.saveAndFlush(order);
            }
        }
        //是否可以进行多次买入操作
        /*else{
            //有订单存在
            JSONArray jsonArray = JSONArray.parseArray(orders);
//            System.out.println(jsonArray.toJSONString());
            //有订单存在且存在买单
            for(int i =0; i < jsonArray.size();i++){
                JSONObject json = (JSONObject) jsonArray.get(i);
                if("buy".equals(json.get("type")) && "0".equals(json.get("status"))){
                    return;
                }
            }

            List<Order> sucOrder = orderDao.findByStatusAndType("2","buy");
            if(sucOrder.size() > 0){
                Order firstOrder = sucOrder.get(0);
//                System.out.println("当前最新已经成交买单:"+firstOrder);
                Map<String,String> tickerMap = tradeService.ticker(defaultOrderInfoBean.getSymbol());
                String ticker = tickerMap.get("ticker");
                JSONObject tickerJson = JSONObject.parseObject(ticker);
                BigDecimal ltPrice = new BigDecimal(tickerJson.get("last").toString());
                BigDecimal st = firstOrder.getPrice().multiply(new BigDecimal(0.04));
                System.out.println("上次买入的价格为："+firstOrder.getPrice()+"当前价格为："+ltPrice+"当前适合买入的价格为:"+ltPrice.add(st));
                if(ltPrice.add(st).compareTo(firstOrder.getPrice()) <= 0){
                    System.out.println("当前价格合适,进行买单操作"+ltPrice);
                    tradeBean.setPrice(ltPrice);
                    tradeBean.setAmount(TradePrice.divide(tradeBean.getPrice(),4,BigDecimal.ROUND_HALF_UP));
                    tradeBean.setType("buy");
                    Map<String,String> tradeMap = tradeService.SellOrBuyTrade(tradeBean);
                    String tradeResult = tradeMap.get("result");
                    if(!StringUtils.isEmpty(tradeResult) && "true".equals(tradeResult)){
                        //插入数据库
                        String orderId = tradeMap.get("order_id");
                        System.out.println("下单成功:订单号"+orderId);
                        Order order = new Order();
                        order.setType("buy");
                        order.setPrice(tradeBean.getPrice());
                        order.setAmount(tradeBean.getAmount().toString());
                        order.setOrderId(orderId);
                        order.setStatus("0");
                        order.setSymbol(defaultOrderInfoBean.getSymbol());
                        order.setTotal(tradeBean.getAmount().multiply(tradeBean.getPrice()).toString());
                        order.setCreateTime(new Date());
                        order.setLastUpdateTime(new Date());
                        orderDao.saveAndFlush(order);
                    }
                }
            }

        }*/
    }

}
