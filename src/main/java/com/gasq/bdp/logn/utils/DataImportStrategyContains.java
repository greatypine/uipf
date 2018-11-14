/**
 * 
 */
package com.gasq.bdp.logn.utils;

import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.service.DataImport;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.utils
 * @creatTime 2017年12月20日上午10:14:41
 * @remark 
 */
public class DataImportStrategyContains {

	private DataImport dataImport;

	public DataImportStrategyContains(DataImport dataImport) {
		super();
		this.dataImport = dataImport;
	}

	@SuppressWarnings("rawtypes")
	public List<Map> execute(List<String> columns,String filename, Integer startIndex) throws WorkFlowJobException {
		return dataImport.execute(columns, filename,startIndex);
	}
}
