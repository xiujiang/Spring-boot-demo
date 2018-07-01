package com.bean;

import java.math.BigDecimal;

public class TradeInfoBean {
    private String merNo;
    private BigDecimal amount;
    private BigDecimal price;
    private String symbol;
    private String buyAndSell;
    private String apiKey;
    private String sercet;
    private String coinChange;
    private String times;           //次数
    private String tradeRate;       //刷单频率

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBuyAndSell() {
        return buyAndSell;
    }

    public void setBuyAndSell(String buyAndSell) {
        this.buyAndSell = buyAndSell;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSercet() {
        return sercet;
    }

    public void setSercet(String sercet) {
        this.sercet = sercet;
    }

    public String getCoinChange() {
        return coinChange;
    }

    public void setCoinChange(String coinChange) {
        this.coinChange = coinChange;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTradeRate() {
        return tradeRate;
    }

    public void setTradeRate(String tradeRate) {
        this.tradeRate = tradeRate;
    }

    @Override
    public String toString() {
        return "TradeInfoBean{" +
                "merNo='" + merNo + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", symbol='" + symbol + '\'' +
                ", buyAndSell='" + buyAndSell + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", sercet='" + sercet + '\'' +
                ", coinChange='" + coinChange + '\'' +
                ", times='" + times + '\'' +
                ", tradeRate='" + tradeRate + '\'' +
                '}';
    }
}
