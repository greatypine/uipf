package com.gasq.bdp.logn.model;

import java.util.Date;

public class TSysTimerWorkflow extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Boolean status;

    private Boolean ignoreerror;

    private Boolean replay;

    private Integer replaysize;

    private String groupname;

    private Date createtime;

    private Date updatetime;

    private String createuser;

    private String remark;

    private String updateuser;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIgnoreerror() {
        return ignoreerror;
    }

    public void setIgnoreerror(Boolean ignoreerror) {
        this.ignoreerror = ignoreerror;
    }

    public Boolean getReplay() {
        return replay;
    }

    public void setReplay(Boolean replay) {
        this.replay = replay;
    }

    public Integer getReplaysize() {
        return replaysize;
    }

    public void setReplaysize(Integer replaysize) {
        this.replaysize = replaysize;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
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
}