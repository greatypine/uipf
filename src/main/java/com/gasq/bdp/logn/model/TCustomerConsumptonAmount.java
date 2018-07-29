package com.gasq.bdp.logn.model;

import java.math.BigDecimal;
import java.util.Date;

public class TCustomerConsumptonAmount extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer customerId;

    private Integer projectId;

    private BigDecimal projectAmount;

    private Double discount;

    private BigDecimal totalAmount;

    private BigDecimal arrears;

    private BigDecimal repayment;

    private BigDecimal movePayment;

    private BigDecimal cashIncome;

    private BigDecimal creditCashIncome;

    private String createuser;

    private String updateuser;

    private Date createtime;

    private Date updatetime;

    private String remark;

    private Integer deadline;

    private Integer projectNums;
    
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public BigDecimal getProjectAmount() {
        return projectAmount;
    }

    public void setProjectAmount(BigDecimal projectAmount) {
        this.projectAmount = projectAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getArrears() {
        return arrears;
    }

    public void setArrears(BigDecimal arrears) {
        this.arrears = arrears;
    }

    public BigDecimal getRepayment() {
        return repayment;
    }

    public void setRepayment(BigDecimal repayment) {
        this.repayment = repayment;
    }

    public BigDecimal getMovePayment() {
        return movePayment;
    }

    public void setMovePayment(BigDecimal movePayment) {
        this.movePayment = movePayment;
    }

    public BigDecimal getCashIncome() {
        return cashIncome;
    }

    public void setCashIncome(BigDecimal cashIncome) {
        this.cashIncome = cashIncome;
    }

    public BigDecimal getCreditCashIncome() {
        return creditCashIncome;
    }

    public void setCreditCashIncome(BigDecimal creditCashIncome) {
        this.creditCashIncome = creditCashIncome;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public Integer getProjectNums() {
        return projectNums;
    }

    public void setProjectNums(Integer projectNums) {
        this.projectNums = projectNums;
    }
}