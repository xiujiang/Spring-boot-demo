package com.service;

import com.CoinExchangerHandler;
import com.entity.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * okex
 * Created by joel on 2018/1/12.
 */
@Service
public class TradeService {
    @Resource
    Map<String, CoinExchangerHandler> handlerMap;

    /**
     * 刷单交易
     * @param member
     */
    public void ClickFarmingTrade(Member member){
        CoinExchangerHandler handler = handlerMap.get("CoinPark"+"Service");
        //1.查询订单（本地数据库和网站）

        //2.检查订单（如果没有订单，就下单，如果有订单，则判断是否可以售出）

        //2.交易订单（执行买入或卖出操作）

        //3.完成订单
    }
}
