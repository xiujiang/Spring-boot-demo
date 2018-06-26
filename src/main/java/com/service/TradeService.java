package com.service;

import com.CoinExchangerHandler;
import com.alibaba.fastjson.JSONObject;
import com.bean.OrderInfoBean;
import com.bean.TradeBean;
import com.entity.Member;
import com.util.ExcelUtil;
import com.util.HttpUtilManager;
import com.util.HttpUtils2;
import com.util.SignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.DecimalFormat;
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

    public void Trade(Member member){
        CoinExchangerHandler handler = handlerMap.get("CoinPark"+"Service");
        //1.查询订单（本地数据库和网站）

        //2.检查订单（如果没有订单，就下单，如果有订单，则判断是否可以售出）

        //2.交易订单（执行买入或卖出操作）

        //3.完成订单
    }
}
