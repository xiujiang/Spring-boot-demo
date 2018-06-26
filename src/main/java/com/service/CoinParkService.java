package com.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.OrderInfoBean;
import com.util.HttpUtils2;
import com.util.SignUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * coinPark 交易所api 信息
 * Created by joel on 2018/6/26.
 */
@Service
public class CoinParkService {
    private String url = "https://api.coinpark.cc/v1/mdata";

    public Map<String,String> orderInfo(OrderInfoBean orderInfoBean) throws Exception {
        if(StringUtils.isEmpty(orderInfoBean)){
            return null;
        }
        JSONObject json = new JSONObject();
        JSONObject cmds = new JSONObject();
        cmds.put("cmd","/api/pairList");
        cmds.put("body","{}");
        JSONArray jsarray = new JSONArray();
        jsarray.add(cmds);
        json.put("apikey",orderInfoBean.getApiKey());
        json.put("cmds",jsarray);
        String sign = SignUtils.coinParkSign(json,orderInfoBean.getSecret());
        json.put("sign",sign);
        System.out.println(json.toString());
        String response = HttpUtils2.getWebContentByJsonPost(url,json.toString());
        System.out.println(response);
        Map<String,String> maps = new HashMap<String, String>();
        JSONObject respJson = JSONObject.parseObject(response);
        for(String key:respJson.keySet()){
            maps.put(key,respJson.getString(key));
        }
        return  maps;
    }

    public static void main(String[] args) throws Exception {
        OrderInfoBean orderInfoBean = new OrderInfoBean("24faa71aabc17ecfc684673a8071100f12a1b107","5b3af0219332758669cc32c6266285f41d390406","","");
        CoinParkService coinParkService  = new CoinParkService();
        coinParkService.orderInfo(orderInfoBean);
    }
}
