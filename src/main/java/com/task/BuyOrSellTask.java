package com.task;

import com.bean.ResponseBean;
import com.constant.TradeConstant;
import com.entity.Order;
import com.handler.CoinExchangerHandler;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.OrderInfoBean;
import com.bean.TradeInfoBean;
import com.bean.UserTradeInfoBean;
import com.constant.MemberConstant;
import com.dao.MemberDao;
import com.dao.MemberInfoDao;
import com.dao.OrderDao;
import com.entity.Member;
import com.entity.MemberInfo;
import com.service.TradeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专为挖矿产生
 */
@Component
public class BuyOrSellTask  extends AbstractTask {

    @Resource
    Map<String, CoinExchangerHandler> handlerMap;
    @Resource
    TradeService tradeService;
    @Resource
    OrderDao orderDao;
    @Resource
    MemberInfoDao memberInfoDao;
    @Resource
    MemberDao memberDao;


    @Scheduled(cron="0/2 * * * * ? ")
    @Override
    public void execute() throws Exception {
//        System.out.println("挖矿开始。。。。。。。");
        System.out.println("挖矿开始");
        //先判断是否可以进行挖矿
        System.out.println();
        boolean b = true;
        if (!b) {
            return;
        }
        Map<String, TradeInfoBean> tradeInfoBeanHashMap = MemberConstant.getTradeInfoBeanHashMap();
        Map<String, UserTradeInfoBean> userTradeInfoBeanMap = MemberConstant.getUserTradeInfoBeanMap();
        if (tradeInfoBeanHashMap.isEmpty()) {
            return;
        }
        System.out.println("当前会员在线个数为:{}"+tradeInfoBeanHashMap.size());
        for (String memberNo : tradeInfoBeanHashMap.keySet()) {
            TradeInfoBean tradeInfoBean = tradeInfoBeanHashMap.get(memberNo);
            //用户交易信息
            UserTradeInfoBean userTradeInfoBean = userTradeInfoBeanMap.get(memberNo);
            //用户订单信息
            OrderInfoBean orderInfoBean = getOrderInfoBean(tradeInfoBean, userTradeInfoBean);
            System.out.println("会员交易：{}"+orderInfoBean);
            //检查是否有订单
            Map<String, String> checkPaddingOrder = checkCoinOrder(orderInfoBean);
            System.out.println("查询订单结束{}"+checkPaddingOrder);
            if (!ResponseBean.SUCCESS.equals(checkPaddingOrder.get("code"))) {
                System.out.println(checkPaddingOrder.get("msg"));
                tradeInfoBeanHashMap.remove(memberNo);
                continue;
            }
            //刷单交易
            Map<String, String> clickFarmingTradeMaps = tradeService.ClickFarmingTrade(orderInfoBean);
            if (userTradeInfoBean != null) {
                userTradeInfoBean.setNowTimes(userTradeInfoBean.getNowTimes() + 1);
                userTradeInfoBean.setNowAccount(orderInfoBean.getAmount());
                userTradeInfoBean.setBuyOrSell(userTradeInfoBean.getBuyOrSell().equals("1") ? "2" : "1");
                if (userTradeInfoBean.getTimes().equals(userTradeInfoBean.getNowTimes())) {
                    tradeInfoBeanHashMap.remove(memberNo);
                    userTradeInfoBeanMap.remove(memberNo);
                    System.out.println("会员:{}交易次数达到上线，关闭交易"+ memberNo);
                    continue;
                }
                if (userTradeInfoBean.getAccount().compareTo(userTradeInfoBean.getNowAccount()) == 0) {
                    tradeInfoBeanHashMap.remove(memberNo);
                    userTradeInfoBeanMap.remove(memberNo);
                    System.out.println("会员:{}交易金额达到上线，关闭交易"+memberNo);
                    continue;
                }
            }

        }
    }

    public Map<String, String> checkCoinOrder(OrderInfoBean orderInfoBean) throws Exception {
        Map<String, String> maps = new HashMap<>();
        //第一步，检查是否已经下单
        CoinExchangerHandler handler = handlerMap.get(orderInfoBean.getCoinExchange() + "Service");
        Map<String,String> userMap = checkInfo(orderInfoBean.getCoinExchange(), handler, orderInfoBean);
        if(!ResponseBean.SUCCESS.equals(userMap.get("code"))){
            return userMap;
        }
        //1.查询订单（本地数据库和网站）
        OrderInfoBean orderPendingBean = new OrderInfoBean();
        MemberInfo memberInfo = memberInfoDao.findByMemberInfoByMerNo(orderInfoBean.getMemberNo(), orderInfoBean.getCoinExchange());
        if (memberInfoDao == null) {
            maps.put("code", ResponseBean.FAIL_98);
            maps.put("msg", "该会员没有权限");
            return maps;
        }
        orderPendingBean.setApiKey(orderInfoBean.getApiKey());
        orderPendingBean.setSecret(orderInfoBean.getSecret());
        orderPendingBean.setSymbol(orderInfoBean.getSymbol());
        ResponseBean<Map<String, String>> suerOrders = handler.orderpending(orderPendingBean);
        if (!ResponseBean.SUCCESS.equals(suerOrders.getCode())) {
            maps.put("code", ResponseBean.FAIL_98);
            maps.put("msg", "委托单查询失败");
            return maps;
        }
        Map<String, String> returnMap = suerOrders.getData();
        List<Order> order = orderDao.findByMerNoAndCoinExchangeAndStatus(orderInfoBean.getMemberNo(),orderInfoBean.getCoinExchange(),TradeConstant.ORDER_STATUS_NO_DEAL);
        if(order.isEmpty()){
            maps.put("code", ResponseBean.SUCCESS);
            return maps;
        }
        if(returnMap.isEmpty()){
            for(int i = 0; i < order.size(); i++){
                Order or = order.get(i);
                or.setStatus(TradeConstant.ORDER_STATUS_SETTLE);
                orderDao.save(or);
            }
        }

        if (!returnMap.isEmpty()) {
            String orderId = "";
            orderId = order.get(0).getOrderId();
            boolean orderSign = false;
            for(String key:returnMap.keySet()){
                JSONObject jsonObject = JSONObject.parseObject(key);
                if(orderId.equals(jsonObject.getString("orderId"))){
                    maps.put("code", ResponseBean.FAIL_98);
                    maps.put("msg", "订单"+orderId+"还未成交");
                    return maps;
                }
            }
            //当前订单已经被成交
            if(orderSign){
                for(int i = 0; i < order.size(); i++){
                    Order or = order.get(i);
                    or.setStatus(TradeConstant.ORDER_STATUS_SETTLE);
                    orderDao.save(or);
                }
            }
            //判断订单是否已经成交
            maps.put("code", ResponseBean.FAIL_98);
            maps.put("msg", "已经有订单存在");
            return maps;
        }
        maps.put("code", ResponseBean.SUCCESS);
        return maps;
    }

    /**
     * 检查交易前信息
     *
     * @param coinExchange
     */
    public Map<String, String> checkInfo(String coinExchange, CoinExchangerHandler handler, OrderInfoBean orderInfoBean) throws Exception {
        Map<String, String> maps = new HashMap<>();
        Member member = memberDao.findByMerNo(orderInfoBean.getMemberNo());
        if (MemberConstant.DEFAULT_LEVEL.equals(member.getLevel())) {
            maps.put("code", ResponseBean.FAIL_98);
            maps.put("msg", "该会员等级不足");
            return maps;
        }
        MemberInfo memberInfo = memberInfoDao.findByMemberInfoByMerNo(member.getMerNo(), coinExchange);
        if (memberInfoDao == null) {
            maps.put("code", ResponseBean.FAIL_98);
            maps.put("msg", "该会员产品信息未开通");
            return maps;
        }

        /*
                检查余额
         */
        String coinSymbol = orderInfoBean.getSymbol().split("_")[0];
        String currencySymbol = orderInfoBean.getSymbol().split("_")[1];
        ResponseBean<Map<String, String>> responseBean = handler.getTransferAssets(orderInfoBean);
        System.out.println("当前用户余额为:"+responseBean);
        if (!ResponseBean.SUCCESS.equals(responseBean.getCode())) {
            maps.put("code", ResponseBean.FAIL_98);
            maps.put("msg", "余额查询失败");
            return maps;
        }
        Map<String, String> respMap = responseBean.getData();

        //用户余额
        String startUserAccount = respMap.get(orderInfoBean.getSymbol().split("_")[0]);
        String endUserAccount = respMap.get(orderInfoBean.getSymbol().split("_")[0]);
        BigDecimal amount = orderInfoBean.getAmount().multiply(orderInfoBean.getPrice());
        if("1".equals(orderInfoBean.getBuyOrSell())){
            //买保证endUserAccount充足
            System.out.println("当前用户余额为:"+endUserAccount);
            if (StringUtils.isEmpty(endUserAccount)) {
                maps.put("code", ResponseBean.FAIL_98);
                maps.put("msg", "余额信息为空");
                return maps;
            } else {
                //当前交易金额
                if (amount.compareTo(new BigDecimal(endUserAccount)) > 0) {
                    maps.put("code", ResponseBean.FAIL_98);
                    maps.put("msg", "用户当前余额不足，无法交易");
                    return maps;
                }
            }
        }else{
            //卖保证startuserAccount充足
            System.out.println("当前用户余额为:"+startUserAccount);
            if (StringUtils.isEmpty(startUserAccount)) {
                maps.put("code", ResponseBean.FAIL_98);
                maps.put("msg", "余额信息为空");
                return maps;
            } else {
                //当前交易金额
                if (amount.compareTo(new BigDecimal(startUserAccount)) > 0) {
                    maps.put("code", ResponseBean.FAIL_98);
                    maps.put("msg", "用户当前余额不足，无法交易");
                    return maps;
                }
            }
        }

        maps.put("code", ResponseBean.SUCCESS);
        return maps;
    }

    public OrderInfoBean getOrderInfoBean(TradeInfoBean tradeInfoBean,UserTradeInfoBean userTradeInfoBean) throws Exception {
        OrderInfoBean orderInfoBean = new OrderInfoBean();
        orderInfoBean.setSymbol(tradeInfoBean.getSymbol());
        orderInfoBean.setSecret(tradeInfoBean.getSercet());
        orderInfoBean.setApiKey(tradeInfoBean.getApiKey());
        orderInfoBean.setMemberNo(tradeInfoBean.getMerNo());
        //查询当前价格
        String type = tradeInfoBean.getBuyAndSell().equals("1")?"buy":"sell";
        List<Order> orders = orderDao.findByMerNoAndCoinExchangeAndType(orderInfoBean.getMemberNo(),orderInfoBean.getCoinExchange(),type);
        orderInfoBean.setCoinExchange(tradeInfoBean.getCoinChange());
        orderInfoBean.setAmount(tradeInfoBean.getAmount());
        String orderId = tradeInfoBean.getMerNo()+System.currentTimeMillis();
//        orderInfoBean.setOrderId(orderId);


        if("buy".equals(type)){
            //查询价格，买入
            String buy = "";
            String sell = "";
            CoinExchangerHandler handler = handlerMap.get(orderInfoBean.getCoinExchange() + "Service");
            ResponseBean<Map<String,String>> coinPrice = handler.getSymbolCoinPrice(orderInfoBean);
            if(ResponseBean.SUCCESS.equals(coinPrice.getCode())){
                Map<String,String> maps = coinPrice.getData();
                orderInfoBean.setPrice(new BigDecimal(maps.get("buy")));
            }
        }else{
            orderInfoBean.setPrice(orders.get(0).getPrice());
        }
        if(userTradeInfoBean.getTimes().equals("0")){
            orderInfoBean.setBuyOrSell(tradeInfoBean.getBuyAndSell());
        }else{
            orderInfoBean.setBuyOrSell(userTradeInfoBean.getBuyOrSell());
        }
        return  orderInfoBean;
    }
}
