package com.gasq.bdp.logn.model;

import java.math.BigDecimal;
import java.util.Date;

public class TScoreExchange extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer userid;

    private String mobilePhone;

    private String userName;

    private String nickAme;

    private String sex;

    private String email;

    private String telephone;

    private Date birthday;

    private String recomCode;

    private String buycode;

    private Integer orderid;

    private BigDecimal price;

    private BigDecimal payPrice;

    private Integer companyid;

    private Date orderCreateTime;

    private String orderRemark;

    private Date createTime;

    private String createUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickAme() {
        return nickAme;
    }

    public void setNickAme(String nickAme) {
        this.nickAme = nickAme == null ? null : nickAme.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRecomCode() {
        return recomCode;
    }

    public void setRecomCode(String recomCode) {
        this.recomCode = recomCode == null ? null : recomCode.trim();
    }

    public String getBuycode() {
        return buycode;
    }

    public void setBuycode(String buycode) {
        this.buycode = buycode == null ? null : buycode.trim();
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark == null ? null : orderRemark.trim();
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

	public void setUser(TUser user) {
		this.userid = user.getUserId().intValue();
		this.birthday = user.getBirthday();
		this.email = user.getEmail();
		this.sex = user.getSex();
		this.mobilePhone = user.getMobilePhone();
		this.userName = user.getUserName();
		this.telephone = user.getTelephone();
		this.nickAme = user.getNickName();
		this.recomCode = user.getRecomCode();
	}

	public void setOrder(TOrder order) {
		this.orderid = order.getId().intValue();
		this.orderCreateTime = order.getCreateTime();
		this.orderRemark = order.getOrderRemark();
		this.buycode = order.getBugCode();
		this.payPrice = order.getPayPrice();
		this.price = order.getPrice();
	}
}