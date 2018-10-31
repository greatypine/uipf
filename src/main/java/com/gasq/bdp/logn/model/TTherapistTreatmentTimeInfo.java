package com.gasq.bdp.logn.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TTherapistTreatmentTimeInfo {
    private Integer id;

    private Integer userId;

    private Integer orderId;

    private Short chuFuZhen;

    private Integer treatmentTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date day;

    private Boolean status;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private Integer companyid;
    
    private Integer daytype;
    
    public Integer getDaytype() {
		return daytype;
	}

	public void setDaytype(Integer daytype) {
		this.daytype = daytype;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Short getChuFuZhen() {
        return chuFuZhen;
    }

    public void setChuFuZhen(Short chuFuZhen) {
        this.chuFuZhen = chuFuZhen;
    }

    public Integer getTreatmentTime() {
        return treatmentTime;
    }

    public void setTreatmentTime(Integer treatmentTime) {
        this.treatmentTime = treatmentTime;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }
}