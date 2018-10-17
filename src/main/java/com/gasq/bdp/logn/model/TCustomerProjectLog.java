package com.gasq.bdp.logn.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TCustomerProjectLog extends ParamsObject{
    public TCustomerProjectLog() {
		super();
	}

	public TCustomerProjectLog(Integer cpId, Integer type, String createUser, Date createTime) {
		super();
		this.cpId = cpId;
		this.type = type;
		this.createUser = createUser;
		this.createTime = createTime;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer cpId;

    private Integer type;

    private String createUser;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;
    
    private Integer companyid;
    
    public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}