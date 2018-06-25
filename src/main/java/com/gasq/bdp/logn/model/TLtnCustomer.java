package com.gasq.bdp.logn.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TLtnCustomer extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer companyId;

    private Integer chuFuZhen;

    private String customername;

    private String phonenumb;

    private Integer sex;

    private String cardId;

    private Integer counsoler;

    private Integer therapeutist;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date remindtime;

    private Integer status;

    private Integer type;

    private Integer subscribeId;

    private String createuser;

    private String updateuser;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createtime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatetime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date importtime;

    private String importuser;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endtime;

    private String remark;
    
    private String[] statuss;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getChuFuZhen() {
        return chuFuZhen;
    }

    public void setChuFuZhen(Integer chuFuZhen) {
        this.chuFuZhen = chuFuZhen;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    public String getPhonenumb() {
        return phonenumb;
    }

    public void setPhonenumb(String phonenumb) {
        this.phonenumb = phonenumb == null ? null : phonenumb.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public Integer getCounsoler() {
        return counsoler;
    }

    public void setCounsoler(Integer counsoler) {
        this.counsoler = counsoler;
    }

    public Integer getTherapeutist() {
        return therapeutist;
    }

    public void setTherapeutist(Integer therapeutist) {
        this.therapeutist = therapeutist;
    }

    public Date getRemindtime() {
        return remindtime;
    }

    public void setRemindtime(Date remindtime) {
        this.remindtime = remindtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(Integer subscribeId) {
        this.subscribeId = subscribeId;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getImporttime() {
        return importtime;
    }

    public void setImporttime(Date importtime) {
        this.importtime = importtime;
    }

    public String getImportuser() {
        return importuser;
    }

    public void setImportuser(String importuser) {
        this.importuser = importuser == null ? null : importuser.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String[] getStatuss() {
		return statuss;
	}

	public void setStatuss(String[] statuss) {
		this.statuss = statuss;
	}
    
	
}