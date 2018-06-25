package com.gasq.bdp.logn.model;

import java.util.Date;

public class TSysTimerWfExeInfo extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer logid;

    private Integer scid;

    private Integer wfid;

    private Integer jobid;

    private Double exeTime;

    private Boolean status;

    private Date createtime;

    private String massage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public Integer getWfid() {
        return wfid;
    }

    public void setWfid(Integer wfid) {
        this.wfid = wfid;
    }

    public Integer getJobid() {
        return jobid;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public Double getExeTime() {
        return exeTime;
    }

    public void setExeTime(Double exeTime) {
        this.exeTime = exeTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage == null ? null : massage.trim();
    }

	public TSysTimerWfExeInfo(Integer id, Integer logid, Integer scid, Integer wfid, Integer jobid, Double exeTime,
			Boolean status, Date createtime, String massage) {
		super();
		this.id = id;
		this.logid = logid;
		this.scid = scid;
		this.wfid = wfid;
		this.jobid = jobid;
		this.exeTime = exeTime;
		this.status = status;
		this.createtime = createtime;
		this.massage = massage;
	}

	public TSysTimerWfExeInfo() {
		super();
	}

	public TSysTimerWfExeInfo(Integer logid, Integer scid, Integer wfid, Integer jobid, Double exeTime, Boolean status,
			Date createtime, String massage) {
		super();
		this.logid = logid;
		this.scid = scid;
		this.wfid = wfid;
		this.jobid = jobid;
		this.exeTime = exeTime;
		this.status = status;
		this.createtime = createtime;
		this.massage = massage;
	}
    
}