package com.gasq.bdp.logn.model;

import java.util.Date;

public class TCustomerPorject extends ParamsObject{
    public TCustomerPorject(Integer vipId, Integer orderId, Integer projectId, Integer projectType, Integer projectNums,
			Integer totalProjectNums, Date deadline, String createUser, Date createTime) {
		super();
		this.vipId = vipId;
		this.orderId = orderId;
		this.projectId = projectId;
		this.projectType = projectType;
		this.projectNums = projectNums;
		this.totalProjectNums = totalProjectNums;
		this.deadline = deadline;
		this.createUser = createUser;
		this.createTime = createTime;
	}

	public TCustomerPorject() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer vipId;

    private Integer orderId;

    private Integer projectId;

    private Integer projectType;

    private Integer projectNums;

    private Integer totalProjectNums;

    private Date deadline;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    public Integer getProjectNums() {
        return projectNums;
    }

    public void setProjectNums(Integer projectNums) {
        this.projectNums = projectNums;
    }

    public Integer getTotalProjectNums() {
        return totalProjectNums;
    }

    public void setTotalProjectNums(Integer totalProjectNums) {
        this.totalProjectNums = totalProjectNums;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}