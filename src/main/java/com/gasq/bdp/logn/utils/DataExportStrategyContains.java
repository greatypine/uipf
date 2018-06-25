/**
 * 
 */
package com.gasq.bdp.logn.utils;

import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.service.DataExport;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.utils
 * @creatTime 2017年12月20日上午10:14:41
 * @remark 
 */
public class DataExportStrategyContains {

	private DataExport dataExport;

	public DataExportStrategyContains(DataExport dataExport) {
		super();
		this.dataExport = dataExport;
	}

	@SuppressWarnings("rawtypes")
	public boolean export(List<Map> datalist,String filename) throws WorkFlowJobException {
		return dataExport.export(datalist, filename);
	}
}
