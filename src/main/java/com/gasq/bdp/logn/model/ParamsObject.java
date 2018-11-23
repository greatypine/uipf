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

	public Integer getPage() {
		if(this.page==null || this.page==0) this.page = 1;
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		if(this.rows==null || this.rows==0) this.rows = 1;
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	private static final long serialVersionUID = 1L;
	private Integer page;
	private Integer rows;
	private String q;
	private Integer pageIndex;
	private Integer start;
	private Integer length;
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	
	
}
