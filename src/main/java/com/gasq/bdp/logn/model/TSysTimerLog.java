package com.gasq.bdp.logn.model;

import java.util.Date;

public class TSysTimerLog extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String modelname;

    private Integer status;

    private Integer jobid;

    private Integer wfid;

    private Integer scid;

    private Integer type;

    private Date createtime;

    private String createuser;

    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname == null ? null : modelname.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getJobid() {
        return jobid;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public Integer getWfid() {
        return wfid;
    }

    public void setWfid(Integer wfid) {
        this.wfid = wfid;
    }

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

	public TSysTimerLog(Integer id, String modelname, Integer status, Integer jobid, Integer wfid, Integer scid,
			Integer type, Date createtime, String createuser, String message) {
		super();
		this.id = id;
		this.modelname = modelname;
		this.status = status;
		this.jobid = jobid;
		this.wfid = wfid;
		this.scid = scid;
		this.type = type;
		this.createtime = createtime;
		this.createuser = createuser;
		this.message = message;
	}

	public TSysTimerLog() {
		super();
	}

	public TSysTimerLog(String modelname, Integer status, Integer jobid, Integer wfid, Integer scid, Integer type,
			Date createtime, String createuser, String message) {
		super();
		this.modelname = modelname;
		this.status = status;
		this.jobid = jobid;
		this.wfid = wfid;
		this.scid = scid;
		this.type = type;
		this.createtime = createtime;
		this.createuser = createuser;
		this.message = message;
	}

	public TSysTimerLog(Integer id, String modelname, Integer status, Integer jobid, Integer wfid, Integer scid,
			Integer type, Date createtime, String message) {
		super();
		this.id = id;
		this.modelname = modelname;
		this.status = status;
		this.jobid = jobid;
		this.wfid = wfid;
		this.scid = scid;
		this.type = type;
		this.createtime = createtime;
		this.message = message;
	}
    
}