package com.bean;

/**
 * Created by joel on 2018/1/13.
 */
public class OrderInfoBean {
    private String apiKey;
    private String symbol;
    private String orderId;
    private String sign;

    public OrderInfoBean(String apiKey,String symbol,String orderId){
        this.apiKey = apiKey;
        this.symbol = symbol;
        this.orderId = orderId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "apiKey='" + apiKey + '\'' +
                ", symbol='" + symbol + '\'' +
                ", orderId='" + orderId + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
