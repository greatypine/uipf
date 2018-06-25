package com.gasq.bdp.logn.model;

import java.util.Date;

public class TSysTimerJobSftp extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String host;

    private Integer port;

    private Integer defPort;

    private String username;

    private String password;

    private Integer timeout;

    private Boolean type;

    private String uploadfile;

    private String uploadtargetpath;

    private String downloadfilepath;

    private String downloadtargetfilepath;

    private Integer status;

    private String remark;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getDefPort() {
        return defPort;
    }

    public void setDefPort(Integer defPort) {
        this.defPort = defPort;
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

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getUploadfile() {
        return uploadfile;
    }

    public void setUploadfile(String uploadfile) {
        this.uploadfile = uploadfile == null ? null : uploadfile.trim();
    }

    public String getUploadtargetpath() {
        return uploadtargetpath;
    }

    public void setUploadtargetpath(String uploadtargetpath) {
        this.uploadtargetpath = uploadtargetpath == null ? null : uploadtargetpath.trim();
    }

    public String getDownloadfilepath() {
        return downloadfilepath;
    }

    public void setDownloadfilepath(String downloadfilepath) {
        this.downloadfilepath = downloadfilepath == null ? null : downloadfilepath.trim();
    }

    public String getDownloadtargetfilepath() {
        return downloadtargetfilepath;
    }

    public void setDownloadtargetfilepath(String downloadtargetfilepath) {
        this.downloadtargetfilepath = downloadtargetfilepath == null ? null : downloadtargetfilepath.trim();
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