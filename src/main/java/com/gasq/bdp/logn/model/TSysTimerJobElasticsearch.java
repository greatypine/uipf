package com.gasq.bdp.logn.model;

import java.util.Date;

public class TSysTimerJobElasticsearch extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String serverIp;

    private Integer port;

    private String clusterName;

    private Integer scorollMinute;

    private String queryIndex;

    private String queryType;

    private String outputfile;

    private Integer diffDay;

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

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp == null ? null : serverIp.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName == null ? null : clusterName.trim();
    }

    public Integer getScorollMinute() {
        return scorollMinute;
    }

    public void setScorollMinute(Integer scorollMinute) {
        this.scorollMinute = scorollMinute;
    }

    public String getQueryIndex() {
        return queryIndex;
    }

    public void setQueryIndex(String queryIndex) {
        this.queryIndex = queryIndex == null ? null : queryIndex.trim();
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType == null ? null : queryType.trim();
    }

    public String getOutputfile() {
        return outputfile;
    }

    public void setOutputfile(String outputfile) {
        this.outputfile = outputfile == null ? null : outputfile.trim();
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