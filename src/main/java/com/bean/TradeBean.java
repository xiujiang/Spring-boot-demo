package com.bean;

import java.math.BigDecimal;

/**
 * Created by joel on 2018/1/13.
 */
public class TradeBean {
    private String apiKey;
    private String symbol;
    private String type;
    private BigDecimal price;
    private BigDecimal amount;
    private String sign;

    public TradeBean(String apiKey,String symbol,String type,BigDecimal price,BigDecimal amount ){
        this.apiKey = apiKey;
        this.symbol = symbol;
        this.type = type;
        this.price = price;
        this.amount = amount;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "TradeBean{" +
                "apiKey='" + apiKey + '\'' +
                ", symbol='" + symbol + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", sign='" + sign + '\'' +
                '}';
    }
}
