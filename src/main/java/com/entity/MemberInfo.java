package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * member 详细信息
 * Created by joel on 2018/6/26.
 */
@Entity
@Table(name="member_info")
public class MemberInfo {
    @Id
    private int id;
    private String merNo;
    private String coinExchanger;        //交易所
    private String appKey;              //appKey
    private String secret;      // 秘钥
    private String status;
    private String type;
    private Date createTime;
    private Date lastUpdateTime;

    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name="mer_no")
    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    @Column(name="coin_exchanger")
    public String getCoinExchanger() {
        return coinExchanger;
    }

    public void setCoinExchanger(String coinExchanger) {
        this.coinExchanger = coinExchanger;
    }

    @Column(name="app_key")
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
    @Column(name="status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Column(name="create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Column(name="last_update_time")
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    @Column(name="secret")
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
    @Override
    public String toString() {
        return "MemberInfo{" +
                "id=" + id +
                ", merNo='" + merNo + '\'' +
                ", coinExchange='" + coinExchanger + '\'' +
                ", appKey='" + appKey + '\'' +
                ", secret='" + secret + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
