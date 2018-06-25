package com.gasq.bdp.logn.model;

import java.util.Date;

public class TSysTimerJobMongoQuery extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private String dbId;

    private Boolean islimit;

    private Integer pagesize;

    private Integer pagenum;

    private Boolean status;

    private Integer inputParams;

    private Integer inputType;

    private Integer outParams;

    private Integer outType;

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

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId == null ? null : dbId.trim();
    }

    public Boolean getIslimit() {
        return islimit;
    }

    public void setIslimit(Boolean islimit) {
        this.islimit = islimit;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getPagenum() {
        return pagenum;
    }

    public void setPagenum(Integer pagenum) {
        this.pagenum = pagenum;
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

    public Integer getOutParams() {
        return outParams;
    }

    public void setOutParams(Integer outParams) {
        this.outParams = outParams;
    }

    public Integer getOutType() {
        return outType;
    }

    public void setOutType(Integer outType) {
        this.outType = outType;
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