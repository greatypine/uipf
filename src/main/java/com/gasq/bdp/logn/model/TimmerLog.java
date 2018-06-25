/**
 * 
 */
package com.gasq.bdp.logn.model;

import java.sql.Timestamp;

/**
 * @author: Justin
 * @createTime: 2017年7月7日上午9:34:44
 * @packageName: cn.gnkj.model
 * @remark:
 */
public class TimmerLog extends BaseBean {

	public Integer getWfid() {
		return wfid;
	}
	public void setWfid(Integer wfid) {
		this.wfid = wfid;
	}
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String message;
	private String modelname;
	private Integer status;
	private Integer jobid;
	private Integer wfid;
	private Integer scid;
	private Timestamp crateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getCrateTime() {
		return crateTime;
	}
	public void setCrateTime(Timestamp crateTime) {
		this.crateTime = crateTime;
	}
	public String getModelname() {
		return modelname;
	}
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	public Integer getJobid() {
		return jobid;
	}
	public void setJobid(Integer jobid) {
		this.jobid = jobid;
	}
	public Integer getScid() {
		return scid;
	}
	public void setScid(Integer scid) {
		this.scid = scid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public TimmerLog() {
		super();
	}
	
	public TimmerLog(String message, String modelname, Integer status, Integer jobid, Integer scid) {
		super();
		this.message = message;
		this.modelname = modelname;
		this.status = status;
		this.jobid = jobid;
		this.scid = scid;
		this.crateTime = new Timestamp(System.currentTimeMillis());
	}
}
