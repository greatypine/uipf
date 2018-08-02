/**
 * 
 */
package com.gasq.bdp.logn.model;

import java.util.List;

/**
 * @author Ju_weigang
 * @时间 2018年7月30日下午6:04:45
 * @项目路径 com.gasq.bdp.logn.model
 * @描述 
 */
public class WorkForceParams {

	private String year;
	private String month;
	private Integer companyid;
	private List<Integer> userids;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	public List<Integer> getUserids() {
		return userids;
	}
	public void setUserids(List<Integer> userids) {
		this.userids = userids;
	}
	
}
