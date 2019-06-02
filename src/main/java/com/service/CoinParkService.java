package com.service;

import com.bean.ResponseBean;
import com.handler.CoinExchangerHandler;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.OrderInfoBean;
import com.util.HttpUtils2;
import com.util.SignUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.xml.ws.Response;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * coinPark 交易所api 信息
 * Created by joel on 2018/6/26.
 */
@Service("CoinParkService")
public class CoinParkService implements CoinExchangerHandler {
    private String url = "https://api.coinpark.cc/v1/mdata";
    private String tradeURL = "https://api.coinpark.cc/v1/transfer";
    /**
     * 获取单一币种价格
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public ResponseBean<Map<String,String>> getSymbolCoinPrice(OrderInfoBean orderInfoBean) throws Exception {
        //{"result":[{"result":{"id":199,"coin_symbol":"BIX","currency_symbol":"BTC","last":"0.00016109","high":"0.00018504","low":"0.00016057","change":"-0.00001712","percent":"-9.61%","vol24H":"1665","amount":"0.29","last_cny":"6.48","high_cny":"7.44","low_cny":"6.45","last_usd":"0.98","high_usd":"1.13","low_usd":"0.98"},"cmd":"api/market"}]}
        if(StringUtils.isEmpty(orderInfoBean)){
            return null;
        }
        JSONObject json = new JSONObject();
        JSONObject cmds = new JSONObject();
        cmds.put("cmd","api/market");
        JSONObject body = new JSONObject();
        body.put("pair",orderInfoBean.getSymbol());
        cmds.put("body",body);
        JSONArray jsarray = new JSONArray();
        jsarray.add(cmds);
        json.put("cmds",jsarray.toString());
        String sign = SignUtils.coinParkSign(json.toJSONString(),orderInfoBean.getSecret());
        json.put("apikey",orderInfoBean.getApiKey());
        json.put("sign",sign);
        return returnMap(url,json);
    }

    /**
     * 获取全部币种价格
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public ResponseBean<Map<String,String>> getAllCoinPrice(OrderInfoBean orderInfoBean) throws Exception {
        if(StringUtils.isEmpty(orderInfoBean)){
            return null;
        }
        JSONObject json = new JSONObject();
        JSONObject cmds = new JSONObject();
        cmds.put("cmd","api/marketAll");
        cmds.put("body","{}");
        JSONArray jsarray = new JSONArray();
        jsarray.add(cmds);
        json.put("apikey",orderInfoBean.getApiKey());
        json.put("cmds",jsarray.toString());
        String sign = SignUtils.coinParkSign(json.toJSONString(),orderInfoBean.getSecret());
        json.put("sign",sign);
        return returnMap(url,json);
    }

    /**
     * 获取币种价格深度
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public ResponseBean<Map<String,String>> getCoinDepth(OrderInfoBean orderInfoBean) throws Exception {
        if(StringUtils.isEmpty(orderInfoBean)){
            return null;
        }
        JSONObject json = new JSONObject();
        JSONObject cmds = new JSONObject();
        cmds.put("cmd","api/depth");
        JSONObject body = new JSONObject();
        body.put("pair",orderInfoBean.getSymbol());
        body.put("size","50");
        cmds.put("body",body);
        JSONArray jsarray = new JSONArray();
        jsarray.add(cmds);
        json.put("apikey",orderInfoBean.getApiKey());
        json.put("cmds",jsarray.toString());
        String sign = SignUtils.coinParkSign(json.toJSONString(),orderInfoBean.getSecret());
        json.put("sign",sign);
        return returnMap(url,json);
    }


    /**
     * 获取币种当前市场行情
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public ResponseBean<Map<String,String>> getSymbolTicker(OrderInfoBean orderInfoBean) throws Exception {
        //{"result":[{"result":{"id":199,"coin_symbol":"BIX","currency_symbol":"BTC","last":"0.00016109","high":"0.00018504","low":"0.00016057","change":"-0.00001712","percent":"-9.61%","vol24H":"1665","amount":"0.29","last_cny":"6.48","high_cny":"7.44","low_cny":"6.45","last_usd":"0.98","high_usd":"1.13","low_usd":"0.98"},"cmd":"api/market"}]}
        if(StringUtils.isEmpty(orderInfoBean)){
            return null;
        }
        JSONObject json = new JSONObject();
        JSONObject cmds = new JSONObject();
        cmds.put("cmd","api/ticker");
        JSONObject body = new JSONObject();
        body.put("pair",orderInfoBean.getSymbol());
        cmds.put("body",body);
        JSONArray jsarray = new JSONArray();
        jsarray.add(cmds);
        json.put("apikey",orderInfoBean.getApiKey());
        json.put("cmds",jsarray.toString());
        String sign = SignUtils.coinParkSign(json.toJSONString(),orderInfoBean.getSecret());
        json.put("sign",sign);
        return returnMap(url,json);
    }


    public ResponseBean<Map<String,String>> returnMap(String url,JSONObject json) throws Exception {
        String response = HttpUtils2.postJson(url,json);
        System.out.println(response);
        Map<String,String> maps = new HashMap<String, String>();
        JSONObject respJson = JSONObject.parseObject(response);
        for(String key:respJson.keySet()){
            maps.put(key,respJson.getString(key));
        }
        ResponseBean<Map<String,String>> responseBean = new ResponseBean<>();
        maps.put("aaa",response);
        responseBean.setData(maps);
        return responseBean;
    }


                                ////////////////////////////////////////////////用户资产///////////////////////////////////////////////////////////////////


    /**
     * 获取用户资产信息
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public ResponseBean<Map<String,String>> getTransferAssets(OrderInfoBean orderInfoBean) throws Exception {
        if(StringUtils.isEmpty(orderInfoBean)){
            return null;
        }
        JSONObject json = new JSONObject();
        JSONObject cmds = new JSONObject();
        cmds.put("cmd","transfer/assets");
        JSONObject body = new JSONObject();
        body.put("select","1");
        cmds.put("body",body);
        JSONArray jsarray = new JSONArray();
        jsarray.add(cmds);
        json.put("cmds",jsarray.toString());
        String sign = SignUtils.coinParkSign(json.getString("cmds"),orderInfoBean.getSecret());
        json.put("apikey",orderInfoBean.getApiKey());
        json.put("sign",sign);
        System.out.println(json.toJSONString());
        return returnMap(tradeURL,json);
    }


                 ////////////////////////////////////////////////交易///////////////////////////////////////////////////////////////////

    /**
     * 交易下单
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public ResponseBean<Map<String,String>> trade(OrderInfoBean orderInfoBean) throws Exception {
        if(StringUtils.isEmpty(orderInfoBean)){
            return null;
        }
        JSONObject json = new JSONObject();
        JSONObject cmds = new JSONObject();
        JSONObject body = new JSONObject();
        cmds.put("cmd","orderpending/trade");
        cmds.put("index",orderInfoBean.getIndex());
        body.put("pair",orderInfoBean.getSymbol());
        body.put("account_type","0");
        body.put("order_type","2");
        if("2".equals(orderInfoBean)){
            body.put("order_side","2");
        }else{
            body.put("order_side","1");
        }
        body.put("price",orderInfoBean.getPrice());
        body.put("amount",orderInfoBean.getAmount());
        cmds.put("body",body);
        JSONArray jsarray = new JSONArray();
        jsarray.add(cmds);
        json.put("cmds",jsarray.toString());
        json.put("apikey",orderInfoBean.getApiKey());
        String sign = SignUtils.coinParkSign(json.getString("cmds"),orderInfoBean.getSecret());
        json.put("sign",sign);
        System.out.println(json.toJSONString());
        return returnMap("https://api.coinpark.cc/v1/orderpending",json);
    }


    /**
     * 撤单
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public ResponseBean<Map<String,String>> cancelTrade(OrderInfoBean orderInfoBean) throws Exception {
        if(StringUtils.isEmpty(orderInfoBean)){
            return null;
        }
        JSONObject json = new JSONObject();
        JSONObject cmds = new JSONObject();
        JSONObject body = new JSONObject();
        cmds.put("cmd","orderpending/cancelTrade");
        cmds.put("index",orderInfoBean.getIndex());
        body.put("orders_id",orderInfoBean.getOrderId());
        cmds.put("body",body);
        JSONArray jsarray = new JSONArray();
        jsarray.add(cmds);
        json.put("cmds",jsarray.toString());
        json.put("apikey",orderInfoBean.getApiKey());
        String sign = SignUtils.coinParkSign(json.getString("cmds"),orderInfoBean.getSecret());
        json.put("sign",sign);
        System.out.println(json.toJSONString());
        return returnMap("https://api.coinpark.cc/v1/orderpending",json);
    }


    /**
     * 查询委托单
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public ResponseBean<Map<String,String>> orderpending(OrderInfoBean orderInfoBean) throws Exception {
        if(StringUtils.isEmpty(orderInfoBean)){
            return null;
        }
        JSONObject json = new JSONObject();
        JSONObject cmds = new JSONObject();
        JSONObject body = new JSONObject();
        cmds.put("cmd","orderpending/orderPendingList");
//        cmds.put("index",orderInfoBean.getOrderId());
//        body.put("pair",orderInfoBean.getSymbol());
//        body.put("account_type","0");
        body.put("page","1");
        body.put("size","10");
//        String[] symbol = orderInfoBean.getSymbol().split("_");
//        body.put("coin_symbol",symbol[0]);
//        body.put("currency_symbol",symbol[1]);
//        body.put("order_side",orderInfoBean.getBuyOrSell());
        cmds.put("body",body);
        JSONArray jsarray = new JSONArray();
        jsarray.add(cmds);
        json.put("cmds",jsarray.toString());
        json.put("apikey",orderInfoBean.getApiKey());
        String sign = SignUtils.coinParkSign(json.getString("cmds"),orderInfoBean.getSecret());
        json.put("sign",sign);
        System.out.println(json.toJSONString());
        return returnMap("https://api.coinpark.cc/v1/orderpending",json);
    }

    /**
     * 历史委托单查询
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public ResponseBean<Map<String,String>> pendingHistoryList(OrderInfoBean orderInfoBean) throws Exception {
        if(StringUtils.isEmpty(orderInfoBean)){
            return null;
        }
        JSONObject json = new JSONObject();
        JSONObject cmds = new JSONObject();
        JSONObject body = new JSONObject();
        cmds.put("cmd","orderpending/pendingHistoryList");
        cmds.put("index",orderInfoBean.getOrderId());
        body.put("pair",orderInfoBean.getSymbol());
        body.put("account_type","0");
        body.put("page","1");
        body.put("size",orderInfoBean.getSymbol());
        String[] symbol = orderInfoBean.getSymbol().split("_");
        body.put("coin_symbol",symbol[0]);
        body.put("currency_symbol",symbol[1]);
        body.put("order_side",orderInfoBean.getBuyOrSell());
        body.put("hide_cancel","0");
        cmds.put("body",body);
        JSONArray jsarray = new JSONArray();
        jsarray.add(cmds);
        json.put("cmds",jsarray.toString());
        String sign = SignUtils.coinParkSign(json.getString("cmds"),orderInfoBean.getSecret());
        json.put("apikey",orderInfoBean.getApiKey());
        json.put("sign",sign);
        System.out.println(json.toJSONString());
        return returnMap(tradeURL,json);
    }

//
//    public static void main(String[] args) throws Exception {
//        OrderInfoBean orderInfoBean = new OrderInfoBean("24faa71aabc17ecfc684673a8071100f12a1b107","5b3af0219332758669cc32c6266285f41d390406","BIX_BTC","","1",null,null);
//        CoinParkService coinParkService  = new CoinParkService();
//        orderInfoBean.setSymbol("CP_USDT");
//        orderInfoBean.setPrice(new BigDecimal(0.35).setScale(4,RoundingMode.HALF_DOWN));
//        orderInfoBean.setAmount(new BigDecimal(104).setScale(4,RoundingMode.HALF_DOWN));
////        orderInfoBean.setOrderId("12961283");
//        orderInfoBean.setBuyOrSell("1");
//        orderInfoBean.setIndex(System.currentTimeMillis()+"1");
////        ResponseBean<Map<String,String>> maps = coinParkService.trade(orderInfoBean);
//        //{"result":[{"result":12978028,"cmd":"orderpending/trade","index":"15307113558271"}]}
////        ResponseBean<Map<String,String>> maps = coinParkService.cancelTrade(orderInfoBean);
////        ResponseBean<Map<String,String>> maps = coinParkService.getTransferAssets(orderInfoBean);
//        ResponseBean<Map<String,String>> maps = coinParkService.orderpending(orderInfoBean);
////       Map<String,String> maps = coinParkService.pendingHistoryList(orderInfoBean);
////        JSONArray jsonArray = JSONArray.parseArray(maps.get("result"));
////        System.out.println(jsonArray.toString());
////        JSONObject jsonObject = jsonArray.getJSONObject(0);
////        System.out.println(maps.toString());
//
//
//////        while(true){
////            OrderInfoBean orderInfoBean = new OrderInfoBean("24faa71aabc17ecfc684673a8071100f12a1b107","5b3af0219332758669cc32c6266285f41d390406","BIX_BTC","","1",null,null);
////            CoinParkService coinParkService  = new CoinParkService();
////            orderInfoBean.setSymbol("CP_USDT");
////            orderInfoBean.setPrice(new BigDecimal(0.35).setScale(4,RoundingMode.HALF_DOWN));
////            orderInfoBean.setAmount(new BigDecimal(100).setScale(4,RoundingMode.HALF_DOWN));
////            orderInfoBean.setBuyOrSell("1");
////            orderInfoBean.setIndex(System.currentTimeMillis()+"1");
////            ResponseBean<Map<String,String>> maps = coinParkService.trade(orderInfoBean);
////            //{"result":[{"result":12978028,"cmd":"orderpending/trade","index":"15307113558271"}]}
////            if(maps != null){
////                JSONObject jsonObject = JSONObject.parseObject(maps.getData().get("aaa"));
////                JSONArray jsonArray = jsonObject.getJSONArray("result");
////                JSONObject js1 = JSONObject.parseObject(jsonArray.get(0).toString());
////                String orderId = js1.getString("result");
////                if("".equals(orderId) || orderId == null){
////                    System.out.println("1");
////                }
////
////            }
////            OrderInfoBean orderInfoBean1 = new OrderInfoBean("24faa71aabc17ecfc684673a8071100f12a1b107","5b3af0219332758669cc32c6266285f41d390406","BIX_BTC","","1",null,null);
////            orderInfoBean1.setBuyOrSell("2");
////            orderInfoBean1.setPrice(new BigDecimal(0.35).setScale(4,RoundingMode.HALF_DOWN));
////            orderInfoBean1.setAmount(orderInfoBean.getAmount().multiply(orderInfoBean.getPrice()));
////            orderInfoBean1.setIndex(System.currentTimeMillis()+"1");
////            ResponseBean<Map<String,String>> maps1 = coinParkService.trade(orderInfoBean);
////            System.out.println(maps1);
//////        }
//
//
//    }




}
