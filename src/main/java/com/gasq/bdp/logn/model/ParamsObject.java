/**
 * 
 */
package com.gasq.bdp.logn.model;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.model
 * @creatTime 2017年9月13日上午8:49:42
 * @remark 
 */
public abstract class ParamsObject extends BaseBean{

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	private static final long serialVersionUID = 1L;
	private int page;
	private int rows;
}
