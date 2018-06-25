package com.gasq.bdp.logn.model;

import java.math.BigDecimal;
import java.util.Date;

public class TInventoryLog extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer productId;

    private Integer optionType;

    private String customername;

    private Date orderfinishtime;

    private BigDecimal totalamount;

    private Integer companyid;

    private BigDecimal numbs;

    private String createUser;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOptionType() {
        return optionType;
    }

    public void setOptionType(Integer optionType) {
        this.optionType = optionType;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    public Date getOrderfinishtime() {
        return orderfinishtime;
    }

    public void setOrderfinishtime(Date orderfinishtime) {
        this.orderfinishtime = orderfinishtime;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public BigDecimal getNumbs() {
        return numbs;
    }

    public void setNumbs(BigDecimal numbs) {
        this.numbs = numbs;
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

	public TInventoryLog() {
		super();
	}

	public TInventoryLog(Integer productId, Integer optionType, String customername, Date orderfinishtime,
			BigDecimal totalamount, Integer companyid, BigDecimal numbs, String createUser) {
		super();
		this.productId = productId;
		this.optionType = optionType;
		this.customername = customername;
		this.orderfinishtime = orderfinishtime;
		this.totalamount = totalamount;
		this.companyid = companyid;
		this.numbs = numbs;
		this.createUser = createUser;
	}
    
}