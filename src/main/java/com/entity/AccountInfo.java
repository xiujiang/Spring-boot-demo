package com.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="account")
public class AccountInfo {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name = "user_no")
    private String userNo;
    @Column(name = "address")
    private String address;
    @Column(name = "address_name")
    private String addressName;
    @Column(name = "remark")
    private String remark;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private String status;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "last_update_time")
    private Date lastUpdateTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "AccountInfo{" +
                "id=" + id +
                ", userNo='" + userNo + '\'' +
                ", address='" + address + '\'' +
                ", addressName='" + addressName + '\'' +
                ", remark='" + remark + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
