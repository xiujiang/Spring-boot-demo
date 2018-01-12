package com.service;

import com.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joel on 2018/1/12.
 */
@Service
public class TradeService {
    String http = "https://www.okex.com/api/v1/future_trades.do";
    Logger logger = LoggerFactory.getLogger(getClass());
    public String futureTrades(String symbol,String contractType) throws Exception {
        logger.info("symbol{},contractType{}",symbol,contractType);
        Map<String,String> maps = new HashMap<String,String>();
        maps.put("symbol",symbol);
        maps.put("contract_type",contractType);
        String  response = HttpUtils.getWebContentByGet(http,"utf-8",maps);
        logger.info("response:{}",response);
        return response;
    }

}
