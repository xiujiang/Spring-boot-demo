package com.bean;

/**
 * Created by joel on 2018/1/13.
 */
public class OrderInfoBean {
    private String apiKey;
    private String symbol;
    private String orderId;
    private String secret;
    private String sign;

    public OrderInfoBean(String apiKey,String symbol,String orderId){
        this.apiKey = apiKey;
        this.symbol = symbol;
        this.orderId = orderId;
    }
    public OrderInfoBean(String apiKey,String secret,String symbol,String orderId){
        this.apiKey = apiKey;
        this.symbol = symbol;
        this.orderId = orderId;
        this.secret = secret;
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

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "OrderInfoBean{" +
                "apiKey='" + apiKey + '\'' +
                ", symbol='" + symbol + '\'' +
                ", orderId='" + orderId + '\'' +
                ", secret='" + secret + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}