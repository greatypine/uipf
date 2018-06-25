package com.gasq.bdp.logn.model;

import java.util.Date;

public class TSysSqlUpdateKeys extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer id;

    private String code;

    private String name;

    private Integer type;

    private String compareSymbol;

    private String dataCode1;

    private String dataCode2;

    private Integer insertUpdateJobId;

    private Date createtime;

    private Date updatetime;

    private String createuser;

    private String updateuser;

    private String groupname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCompareSymbol() {
        return compareSymbol;
    }

    public void setCompareSymbol(String compareSymbol) {
        this.compareSymbol = compareSymbol == null ? null : compareSymbol.trim();
    }

    public String getDataCode1() {
        return dataCode1;
    }

    public void setDataCode1(String dataCode1) {
        this.dataCode1 = dataCode1 == null ? null : dataCode1.trim();
    }

    public String getDataCode2() {
        return dataCode2;
    }

    public void setDataCode2(String dataCode2) {
        this.dataCode2 = dataCode2 == null ? null : dataCode2.trim();
    }

    public Integer getInsertUpdateJobId() {
        return insertUpdateJobId;
    }

    public void setInsertUpdateJobId(Integer insertUpdateJobId) {
        this.insertUpdateJobId = insertUpdateJobId;
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