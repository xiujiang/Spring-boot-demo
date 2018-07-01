package com.bean;

import java.math.BigDecimal;

/**
 * Created by joel on 2018/1/13.
 */
public class OrderInfoBean {
    private String apiKey;
    private String symbol;
    private String orderId;
    private String secret;
    private String sign;
    private String buyOrSell;
    private BigDecimal amount;
    private BigDecimal price;
    private String index;
    private String coinExchange;
    private String memberNo;

    public OrderInfoBean() {

    }
    public OrderInfoBean(String apiKey,String symbol,String orderId){
        this.apiKey = apiKey;
        this.symbol = symbol;
        this.orderId = orderId;
    }
    public OrderInfoBean(String apiKey,String secret,String symbol,String orderId,String buyOrSell,BigDecimal amount,BigDecimal price){
        this.apiKey = apiKey;
        this.symbol = symbol;
        this.orderId = orderId;
        this.secret = secret;
        this.buyOrSell = buyOrSell;
        this.amount = amount;
        this.price = price;
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

    public String getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCoinExchange() {
        return coinExchange;
    }

    public void setCoinExchange(String coinExchange) {
        this.coinExchange = coinExchange;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    @Override
    public String toString() {
        return "OrderInfoBean{" +
                "apiKey='" + apiKey + '\'' +
                ", symbol='" + symbol + '\'' +
                ", orderId='" + orderId + '\'' +
                ", secret='" + secret + '\'' +
                ", sign='" + sign + '\'' +
                ", buyOrSell='" + buyOrSell + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", index='" + index + '\'' +
                ", coinExchange='" + coinExchange + '\'' +
                '}';
    }
}
