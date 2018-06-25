/**
 * 
 */
package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service
 * @creatTime 2017年12月20日上午10:15:47
 * @remark 
 */
public interface DataExport {

	@SuppressWarnings("rawtypes")
	public boolean export(List<Map> datalist,String filename)throws WorkFlowJobException;
}
