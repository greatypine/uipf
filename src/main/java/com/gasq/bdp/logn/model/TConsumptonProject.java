package com.gasq.bdp.logn.model;

import java.math.BigDecimal;
import java.util.Date;

public class TConsumptonProject extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 728752260453144146L;

	private Integer id;

    private Integer consumptonAmountId;

    private Integer projectId;

    private BigDecimal numbs;

    private String createuser;

    private String updateuser;

    private Date createtime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsumptonAmountId() {
        return consumptonAmountId;
    }

    public void setConsumptonAmountId(Integer consumptonAmountId) {
        this.consumptonAmountId = consumptonAmountId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public BigDecimal getNumbs() {
        return numbs;
    }

    public void setNumbs(BigDecimal numbs) {
        this.numbs = numbs;
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
}