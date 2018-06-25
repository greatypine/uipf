package com.gasq.bdp.logn.model;

import java.util.Date;

public class TSysTimerJobSqlInsertUpdate extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String dbCode;

    private String tableName;

    private Integer isbatch;

    private Integer batchsize;

    private Boolean status;

    private Integer updateColumns;

    private Integer updateKeys;

    private Integer inputParams;

    private Integer inputType;

    private Date createtime;

    private Date updatetime;

    private String createuser;

    private String remark;

    private String updateuser;

    private String groupname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDbCode() {
        return dbCode;
    }

    public void setDbCode(String dbCode) {
        this.dbCode = dbCode == null ? null : dbCode.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public Integer getIsbatch() {
        return isbatch;
    }

    public void setIsbatch(Integer isbatch) {
        this.isbatch = isbatch;
    }

    public Integer getBatchsize() {
        return batchsize;
    }

    public void setBatchsize(Integer batchsize) {
        this.batchsize = batchsize;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getUpdateColumns() {
        return updateColumns;
    }

    public void setUpdateColumns(Integer updateColumns) {
        this.updateColumns = updateColumns;
    }

    public Integer getUpdateKeys() {
        return updateKeys;
    }

    public void setUpdateKeys(Integer updateKeys) {
        this.updateKeys = updateKeys;
    }

    public Integer getInputParams() {
        return inputParams;
    }

    public void setInputParams(Integer inputParams) {
        this.inputParams = inputParams;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
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

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }
}