/**
 * 
 */
package com.gasq.bdp.logn.model;


/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.model
 * @creatTime 2017年10月30日下午4:25:07
 * @remark 
 */
public class DataBaseBean extends BaseBean {

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String password;
	private String url;
	private String driver;
	private String code;
	public DataBaseBean(String url, String driver,String name, String password) {
		super();
		this.name = name;
		this.password = password;
		this.url = url;
		this.driver = driver;
	}
	
}
