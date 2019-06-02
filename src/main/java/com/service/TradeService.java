package com.service;

import com.bean.ResponseBean;
import com.handler.CoinExchangerHandler;
import com.bean.OrderInfoBean;
import com.dao.MemberInfoDao;
import com.dao.OrderDao;
import com.entity.Member;
import com.entity.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * okex
 * Created by joel on 2018/1/12.
 */
@Service
public class TradeService {
    @Resource
    Map<String, CoinExchangerHandler> handlerMap;
    @Resource
    OrderDao orderDao;
    @Resource
    MemberInfoDao memberInfoDao;
    /**
     * 刷单交易
     */
    public Map<String,String> ClickFarmingTrade(OrderInfoBean orderInfoBean) throws Exception {

        CoinExchangerHandler handler = handlerMap.get(orderInfoBean.getCoinExchange()+"Service");
        Map<String,String> clickFarmingTrade = new HashMap<>();
        ResponseBean<Map<String,String>> responseOrder = handler.trade(orderInfoBean);
        //验证交易完成
        if(!ResponseBean.SUCCESS.equals(responseOrder.getCode())){
            return null;
        }
        Map<String,String> returnMaps = responseOrder.getData();


        return clickFarmingTrade;
    }





    public void checkOrderInfo(){

    }


}
