package com.service;

import com.alibaba.fastjson.JSONObject;
import com.bean.OrderInfoBean;
import com.bean.TradeBean;
import com.util.ExcelUtil;
import com.util.HttpUtilManager;
import com.util.HttpUtils2;
import com.util.SignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joel on 2018/1/12.
 */
@Service
public class TradeService {
    String http = "https://www.okex.com/api/v1/future_trades.do";
    Logger logger = LoggerFactory.getLogger(getClass());
    /**
     *  获取币币交易行情
     * @return
     */
    public Map<String,String> ticker(String symbol) throws Exception {
        String http = "/api/v1/ticker.do?symbol="+symbol;
        HttpUtilManager ht = HttpUtilManager.getInstance();
        String response = ht.requestHttpGet("https://www.okex.com",http,null);
//        String  response = HttpUtils2.get(http);
//        System.out.println("response:{}"+response);
        Map<String,String> respMaps = new HashMap<String, String>();
        JSONObject respJson = JSONObject.parseObject(response);
        for(String key:respJson.keySet()){
            respMaps.put(key,respJson.getString(key));
        }
        System.out.println("当前币价:{}"+respMaps);
        return  respMaps;
    }




    public Map<String,String> SellOrBuyTrade(TradeBean tradeBean) throws Exception {
        String http = "https://www.okex.com/api/v1/trade.do";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("api_key",tradeBean.getApiKey());
        map.put("symbol",tradeBean.getSymbol());
        map.put("type",tradeBean.getType());
        DecimalFormat df1 = new DecimalFormat("0.0000");
        String str = df1.format(tradeBean.getPrice());
        map.put("price",str);
        String str1 = df1.format(tradeBean.getAmount());
        map.put("amount",str1);
        String sign = SignUtils.getMapstoSign(map,"31F93510352C57A0A606EB94B1D9275A");
        map.put("sign",sign);
        String response = HttpUtils2.post(http,map);

//        String response = HttpUtils.getWebContentByJsonPost(http,json.toString(),false,"",0);
        System.out.println("交易response:{}"+response);
        Map<String,String> maps = new HashMap<String, String>();
        JSONObject respJson = JSONObject.parseObject(response);
        for(String key:respJson.keySet()){
            maps.put(key,respJson.getString(key));
        }
        if(maps.get("error_code") != null){
            throw new Exception("交易错误"+maps.get("error_code"));
        }
        System.out.println("当前交易方式:{},交易id:{}"+tradeBean.getType()+"      "+respJson.get("order_id"));
        return  maps;
    }

    public Map<String,String> orderInfo(OrderInfoBean orderInfoBean) throws Exception {
        String http = "https://www.okex.com/api/v1/order_info.do";
        if(StringUtils.isEmpty(orderInfoBean)){
            return null;
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("api_key",orderInfoBean.getApiKey());
        map.put("symbol",orderInfoBean.getSymbol());
        map.put("order_id",orderInfoBean.getOrderId());
        String sign = SignUtils.getMapstoSign(map,"31F93510352C57A0A606EB94B1D9275A");
        map.put("sign",sign);
//        System.out.println("查询订单内容"+map);
        String response = HttpUtils2.post(http,map);
//        System.out.println("response:"+response);
        Map<String,String> maps = new HashMap<String, String>();
        JSONObject respJson = JSONObject.parseObject(response);
        for(String key:respJson.keySet()){
            maps.put(key,respJson.getString(key));
        }
//        System.out.println("当前订单信息为:"+maps);
        return  maps;
    }

    public static void main(String[] args) throws Exception {
//        String  s = "http://api.feixiaohao.com/coins/download/";
//        byte[] bytes = HttpUtils2.postEntity(s,null);
//        File file = new File("b.xls");
//        OutputStream out = new FileOutputStream(file);
//        out.write(bytes);
//        out.close();
//        System.out.println(file.l
        ExcelUtil.getExcelAsFile("b.xls");
    }

}
