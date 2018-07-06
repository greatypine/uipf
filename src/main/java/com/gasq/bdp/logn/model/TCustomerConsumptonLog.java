package com.gasq.bdp.logn.model;

import java.math.BigDecimal;

public class TCustomerConsumptonLog extends TCustomerConsumptonLogKey {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerName;

    private String customerPhone;

    private Integer sex;

    private BigDecimal totalconsumptonamount;

    private BigDecimal accountbalance;

    private Integer type;

    private Integer companyId;

    private String createUser;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone == null ? null : customerPhone.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public BigDecimal getTotalconsumptonamount() {
        return totalconsumptonamount;
    }

    public void setTotalconsumptonamount(BigDecimal totalconsumptonamount) {
        this.totalconsumptonamount = totalconsumptonamount;
    }

    public BigDecimal getAccountbalance() {
        return accountbalance;
    }

    public void setAccountbalance(BigDecimal accountbalance) {
        this.accountbalance = accountbalance;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

	public TCustomerConsumptonLog() {
		super();
	}

	public TCustomerConsumptonLog(Integer id,String customerName, String customerPhone, Integer sex,
			BigDecimal totalconsumptonamount, BigDecimal accountbalance, Integer type, Integer companyId,
			String createUser) {
		super();
		this.setId(id);
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.sex = sex;
		this.totalconsumptonamount = totalconsumptonamount;
		this.accountbalance = accountbalance;
		this.type = type;
		this.companyId = companyId;
		this.createUser = createUser;
	}
    
}