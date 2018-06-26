package com.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by joel on 2018/4/10.
 */
@Entity
@Table(name="fxh_coin_info")
public class FxhCoinInfo {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name="coin_name")
    private String coinName;
    @Column(name = "market_equity")
    private String marketEquity;
    @Column(name = "coin_price")
    private String coinPrice;
    @Column(name = "circulation_quantity")
    private String circulationQuantity;
    @Column(name = "day_turnover")
    private String dayTurnover;
    @Column(name = "day_increase")
    private String dayIncrease;
    @Column(name = "turnover_rate")
    private String turnoverRate;
    @Column(name = "flow_rate")
    private String flowRate;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "last_update_time")
    private Date lastUpdateTime;
    @Column(name = "remark")
    private String remark;
    public FxhCoinInfo(){
        super();
    }
    public FxhCoinInfo(String[] str){
        this.coinName = str[0];
        this.marketEquity = str[1];
        this.coinPrice = str[2];
        this.circulationQuantity =str[3];
        this.dayTurnover = str[4];
        this.dayIncrease = str[5];
        this.turnoverRate = str[6];
        this.flowRate = str[7];
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarketEquity() {
        return marketEquity;
    }

    public void setMarketEquity(String marketEquity) {
        this.marketEquity = marketEquity;
    }

    public String getCoinPrice() {
        return coinPrice;
    }

    public void setCoinPrice(String coinPrice) {
        this.coinPrice = coinPrice;
    }

    public String getCirculationQuantity() {
        return circulationQuantity;
    }

    public void setCirculationQuantity(String circulationQuantity) {
        this.circulationQuantity = circulationQuantity;
    }

    public String getDayTurnover() {
        return dayTurnover;
    }

    public void setDayTurnover(String dayTurnover) {
        this.dayTurnover = dayTurnover;
    }

    public String getDayIncrease() {
        return dayIncrease;
    }

    public void setDayIncrease(String dayIncrease) {
        this.dayIncrease = dayIncrease;
    }

    public String getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(String turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public String getFlowRate() {
        return flowRate;
    }

    public void setFlowRate(String flowRate) {
        this.flowRate = flowRate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FxhCoinInfo{" +
                "id=" + id +
                ", marketEquity='" + marketEquity + '\'' +
                ", coinPrice='" + coinPrice + '\'' +
                ", circulationQuantity='" + circulationQuantity + '\'' +
                ", dayTurnover='" + dayTurnover + '\'' +
                ", dayIncrease='" + dayIncrease + '\'' +
                ", turnoverRate='" + turnoverRate + '\'' +
                ", flowRate='" + flowRate + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
