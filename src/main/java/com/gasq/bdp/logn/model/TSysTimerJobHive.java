package com.gasq.bdp.logn.model;

import java.util.Date;

public class TSysTimerJobHive extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private String dirvername;

    private String url;

    private String user;

    private String pass;

    private String dbname;

    private String tablename;

    private String filepath;

    private Integer exetype;

    private Boolean isimport;

    private Boolean ischeck;

    private Integer diffDay;

    private Integer status;

    private String remark;

    private Date createtime;

    private String createuser;

    private Date updatetime;

    private String updateuser;

    private String groupname;

    private String hql;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDirvername() {
        return dirvername;
    }

    public void setDirvername(String dirvername) {
        this.dirvername = dirvername == null ? null : dirvername.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname == null ? null : dbname.trim();
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public Integer getExetype() {
        return exetype;
    }

    public void setExetype(Integer exetype) {
        this.exetype = exetype;
    }

    public Boolean getIsimport() {
        return isimport;
    }

    public void setIsimport(Boolean isimport) {
        this.isimport = isimport;
    }

    public Boolean getIscheck() {
        return ischeck;
    }

    public void setIscheck(Boolean ischeck) {
        this.ischeck = ischeck;
    }

    public Integer getDiffDay() {
        return diffDay;
    }

    public void setDiffDay(Integer diffDay) {
        this.diffDay = diffDay;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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

    public String getHql() {
        return hql;
    }

    public void setHql(String hql) {
        this.hql = hql == null ? null : hql.trim();
    }
}