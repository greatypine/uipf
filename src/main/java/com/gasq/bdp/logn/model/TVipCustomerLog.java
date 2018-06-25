package com.gasq.bdp.logn.model;

import java.math.BigDecimal;
import java.util.Date;

public class TVipCustomerLog {
    private Integer id;

    private Integer vipcId;

    private Integer optionType;

    private BigDecimal money;

    private Integer companyId;

    private String createUser;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVipcId() {
        return vipcId;
    }

    public void setVipcId(Integer vipcId) {
        this.vipcId = vipcId;
    }

    public Integer getOptionType() {
        return optionType;
    }

    public void setOptionType(Integer optionType) {
        this.optionType = optionType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

	public TVipCustomerLog() {
		super();
	}

	public TVipCustomerLog(Integer vipcId, Integer optionType, BigDecimal money, Integer companyId, String createUser) {
		super();
		this.vipcId = vipcId;
		this.optionType = optionType;
		this.money = money;
		this.companyId = companyId;
		this.createUser = createUser;
	}
    
}