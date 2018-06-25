package com.gasq.bdp.logn.model;

public class TSysRoleMenuKey extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer roleid;

    private Integer menuid;

    private Integer companyid;


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

	public TSysRoleMenuKey(Integer id, Integer roleid, Integer menuid, Integer companyid) {
		super();
		this.id = id;
		this.roleid = roleid;
		this.menuid = menuid;
		this.companyid = companyid;
	}

	public TSysRoleMenuKey() {
		super();
	}

	public TSysRoleMenuKey(Integer roleid, Integer menuid, Integer companyid) {
		super();
		this.roleid = roleid;
		this.menuid = menuid;
		this.companyid = companyid;
	}
    
}