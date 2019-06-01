package com.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class AccountInfo {

    private long id;

    private String userNo;
    private String address;
    private String addressName;
    private String remark;
    private String password;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
                '}';
    }
}
