package com.gasq.bdp.logn.model;

import java.util.Date;

public class TCustomerSubscribeLog extends ParamsObject {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer csId;

    private Integer optionType;

    private String createUser;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCsId() {
        return csId;
    }

    public void setCsId(Integer csId) {
        this.csId = csId;
    }

    public Integer getOptionType() {
        return optionType;
    }

    public void setOptionType(Integer optionType) {
        this.optionType = optionType;
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

	public TCustomerSubscribeLog(Integer id, Integer csId, Integer optionType, String createUser, Date createTime) {
		super();
		this.id = id;
		this.csId = csId;
		this.optionType = optionType;
		this.createUser = createUser;
		this.createTime = createTime;
	}

	public TCustomerSubscribeLog() {
		super();
	}

	public TCustomerSubscribeLog(Integer csId, Integer optionType, String createUser) {
		super();
		this.csId = csId;
		this.optionType = optionType;
		this.createUser = createUser;
	}
    
}