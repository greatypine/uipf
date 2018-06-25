package com.gasq.bdp.logn.model;

import java.util.Date;

public class TSysTimerMongo extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private String hostPorts;

    private Integer maxConnect;

    private Integer maxWaitThread;

    private Integer maxTimeOut;

    private Integer maxWaitTime;

    private String username;

    private String password;

    private String mgDatabase;

    private String mgCollection;

    private Boolean status;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private String groupname;

    private String remark;

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

    public String getHostPorts() {
        return hostPorts;
    }

    public void setHostPorts(String hostPorts) {
        this.hostPorts = hostPorts == null ? null : hostPorts.trim();
    }

    public Integer getMaxConnect() {
        return maxConnect;
    }

    public void setMaxConnect(Integer maxConnect) {
        this.maxConnect = maxConnect;
    }

    public Integer getMaxWaitThread() {
        return maxWaitThread;
    }

    public void setMaxWaitThread(Integer maxWaitThread) {
        this.maxWaitThread = maxWaitThread;
    }

    public Integer getMaxTimeOut() {
        return maxTimeOut;
    }

    public void setMaxTimeOut(Integer maxTimeOut) {
        this.maxTimeOut = maxTimeOut;
    }

    public Integer getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(Integer maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMgDatabase() {
        return mgDatabase;
    }

    public void setMgDatabase(String mgDatabase) {
        this.mgDatabase = mgDatabase == null ? null : mgDatabase.trim();
    }

    public String getMgCollection() {
        return mgCollection;
    }

    public void setMgCollection(String mgCollection) {
        this.mgCollection = mgCollection == null ? null : mgCollection.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}