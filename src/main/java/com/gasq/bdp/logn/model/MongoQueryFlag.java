/**
 * 
 */
package com.gasq.bdp.logn.model;

/**
 * @author Ju_weigang
 * @时间 2018年1月10日上午9:44:06
 * @项目路径 com.gasq.bdp.logn.model
 * @描述 
 */
public class MongoQueryFlag extends ParamsObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String key;
	private String flag;
	private Object value;
	private Object value1;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	public Object getValue1() {
		return value1;
	}
	public void setValue1(Object value1) {
		this.value1 = value1;
	}
	public MongoQueryFlag(String key, String flag, Object value, Object value1) {
		super();
		this.key = key;
		this.flag = flag;
		this.value = value;
		this.value1 = value1;
	}
	

}
