package com.gasq.bdp.logn.model;

import java.math.BigDecimal;
import java.util.Date;

public class TUser {
    private Long userId;

    private String mobilePhone;

    private String nickName;

    private String userName;

    private String sex;

    private Short userType;

    private String userSource;

    private String password;

    private String recomCode;

    private Long historyIntegral;

    private Long validIntegral;

    private Long consumeIntegral;

    private Long outDateIntegral;

    private Long referrerUserId;

    private Date birthday;

    private String telephone;

    private String email;

    private Long creditScore;

    private String userImgUrl;

    private Date lastLogin;

    private String deviceOs;

    private String appToken;

    private String wxPubOpenId;

    private Long growth;

    private Long grade;

    private String plusFlag;

    private Date plusExpiryDate;

    private Byte status;

    private BigDecimal historyMoney;

    private BigDecimal validMoney;

    private BigDecimal consumeMoney;

    private Date registerTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Short getUserType() {
        return userType;
    }

    public void setUserType(Short userType) {
        this.userType = userType;
    }

    public String getUserSource() {
        return userSource;
    }

    public void setUserSource(String userSource) {
        this.userSource = userSource == null ? null : userSource.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRecomCode() {
        return recomCode;
    }

    public void setRecomCode(String recomCode) {
        this.recomCode = recomCode == null ? null : recomCode.trim();
    }

    public Long getHistoryIntegral() {
        return historyIntegral;
    }

    public void setHistoryIntegral(Long historyIntegral) {
        this.historyIntegral = historyIntegral;
    }

    public Long getValidIntegral() {
        return validIntegral;
    }

    public void setValidIntegral(Long validIntegral) {
        this.validIntegral = validIntegral;
    }

    public Long getConsumeIntegral() {
        return consumeIntegral;
    }

    public void setConsumeIntegral(Long consumeIntegral) {
        this.consumeIntegral = consumeIntegral;
    }

    public Long getOutDateIntegral() {
        return outDateIntegral;
    }

    public void setOutDateIntegral(Long outDateIntegral) {
        this.outDateIntegral = outDateIntegral;
    }

    public Long getReferrerUserId() {
        return referrerUserId;
    }

    public void setReferrerUserId(Long referrerUserId) {
        this.referrerUserId = referrerUserId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Long creditScore) {
        this.creditScore = creditScore;
    }

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl == null ? null : userImgUrl.trim();
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs == null ? null : deviceOs.trim();
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken == null ? null : appToken.trim();
    }

    public String getWxPubOpenId() {
        return wxPubOpenId;
    }

    public void setWxPubOpenId(String wxPubOpenId) {
        this.wxPubOpenId = wxPubOpenId == null ? null : wxPubOpenId.trim();
    }

    public Long getGrowth() {
        return growth;
    }

    public void setGrowth(Long growth) {
        this.growth = growth;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public String getPlusFlag() {
        return plusFlag;
    }

    public void setPlusFlag(String plusFlag) {
        this.plusFlag = plusFlag == null ? null : plusFlag.trim();
    }

    public Date getPlusExpiryDate() {
        return plusExpiryDate;
    }

    public void setPlusExpiryDate(Date plusExpiryDate) {
        this.plusExpiryDate = plusExpiryDate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public BigDecimal getHistoryMoney() {
        return historyMoney;
    }

    public void setHistoryMoney(BigDecimal historyMoney) {
        this.historyMoney = historyMoney;
    }

    public BigDecimal getValidMoney() {
        return validMoney;
    }

    public void setValidMoney(BigDecimal validMoney) {
        this.validMoney = validMoney;
    }

    public BigDecimal getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(BigDecimal consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}