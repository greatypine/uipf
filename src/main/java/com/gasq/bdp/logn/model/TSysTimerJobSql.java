package com.gasq.bdp.logn.model;

import java.util.Date;

public class TSysTimerJobSql extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String dbCode;

    private Boolean status;

    private Integer inputParams;

    private Integer inputType;

    private Boolean ismultiple;

    private Date createtime;

    private Date updatetime;

    private String createuser;

    private String remark;

    private String updateuser;

    private String groupname;

    private String diySql;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public Boolean getIsmultiple() {
        return ismultiple;
    }

    public void setIsmultiple(Boolean ismultiple) {
        this.ismultiple = ismultiple;
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

    public String getDiySql() {
        return diySql;
    }

    public void setDiySql(String diySql) {
        this.diySql = diySql == null ? null : diySql.trim();
    }
}