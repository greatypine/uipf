/**
 * 
 */
package com.gasq.bdp.logn.model;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.model
 * @creatTime 2017年11月16日下午2:16:04
 * @remark 
 */
public class PagingBean extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	private int index;
	private int size;
	
}
