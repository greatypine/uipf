package com.gasq.bdp.logn.model;

import java.util.Date;

public class TSysTimerScheduleWorkflow extends TSysTimerScheduleWorkflowKey {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

    private Boolean status;

    private Integer sort;

    private Boolean ignoreerror;

    private Boolean replay;

    private Integer replaysize;

    private String remark;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private String groupname;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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