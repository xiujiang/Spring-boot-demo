package com.bean;

import java.math.BigDecimal;

public class UserTradeInfoBean {
    private String merNo;
    private String times;       //总次数
    private String nowTimes;       //当前次数
    private String tradeRate;       //频率
    private BigDecimal account;       //刷总金额
    private BigDecimal nowAccount;      //当前金额
    private String buyOrSell;

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getNowTimes() {
        return nowTimes;
    }

    public void setNowTimes(String nowTimes) {
        this.nowTimes = nowTimes;
    }

    public String getTradeRate() {
        return tradeRate;
    }

    public void setTradeRate(String tradeRate) {
        this.tradeRate = tradeRate;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public BigDecimal getNowAccount() {
        return nowAccount;
    }

    public void setNowAccount(BigDecimal nowAccount) {
        this.nowAccount = nowAccount;
    }

    public String getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    @Override
    public String toString() {
        return "UserTradeInfoBean{" +
                "merNo='" + merNo + '\'' +
                ", times='" + times + '\'' +
                ", nowTimes='" + nowTimes + '\'' +
                ", tradeRate='" + tradeRate + '\'' +
                ", account=" + account +
                ", nowAccount=" + nowAccount +
                ", buyOrSell='" + buyOrSell + '\'' +
                '}';
    }
}
