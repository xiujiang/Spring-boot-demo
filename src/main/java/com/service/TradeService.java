package com.service;

import com.alibaba.fastjson.JSONObject;
import com.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by joel on 2018/1/12.
 */
@Service
public class TradeService {
    String http = "https://www.okex.com/api/v1/future_trades.do";
    Logger logger = LoggerFactory.getLogger(getClass());
    public String futureTrades(String symbol,String contractType) throws Exception {
        logger.info("symbol{},contractType{}",symbol,contractType);
        JSONObject json = new JSONObject();
        json.put("symbol",symbol);
        json.put("contract_type",contractType);
        String  response = HttpUtils.getWebContentByJsonGet(http,json.toJSONString(),false,"",0);
        logger.info("response:{}",response);
        return response;
    }

}
