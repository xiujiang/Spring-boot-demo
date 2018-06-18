package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by joel on 2018/2/26.
 */
@Entity
@Table(name="coin_price")
public class CoinPrice {
    @Id
    private int id;
    @Column(name = "coin_symbol")
    private String coinSymbol;          //币种名称
    @Column(name = "coin_price")
    private BigDecimal coinPrice;        //币种价格
    @Column(name = "coin_amount")
    private String coinAmount;           //币种交易量
    @Column(name = "coin_amount_price")
    private String coinAmountPrice;       //币种交易金额
    @Column(name = "buy_one_price")
    private String buyOnePrice;         //买一价
    @Column(name = "sell_one_price")
    private String sellOnePrice;        //卖一价
    @Column(name = "create_time")
    private Date createTime;              //开始时间
    @Column(name = "last_update_time")
    private Date lastUpdateTime;           //更新时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public void setCoinSymbol(String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }

    public BigDecimal getCoinPrice() {
        return coinPrice;
    }

    public void setCoinPrice(BigDecimal coinPrice) {
        this.coinPrice = coinPrice;
    }

    public String getCoinAmount() {
        return coinAmount;
    }

    public void setCoinAmount(String coinAmount) {
        this.coinAmount = coinAmount;
    }

    public String getCoinAmountPrice() {
        return coinAmountPrice;
    }

    public void setCoinAmountPrice(String coinAmountPrice) {
        this.coinAmountPrice = coinAmountPrice;
    }

    public String getBuyOnePrice() {
        return buyOnePrice;
    }

    public void setBuyOnePrice(String buyOnePrice) {
        this.buyOnePrice = buyOnePrice;
    }

    public String getSellOnePrice() {
        return sellOnePrice;
    }

    public void setSellOnePrice(String sellOnePrice) {
        this.sellOnePrice = sellOnePrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
