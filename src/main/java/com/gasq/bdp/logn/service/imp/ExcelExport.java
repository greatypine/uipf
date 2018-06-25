/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.service.DataExport;
import com.gasq.bdp.logn.utils.DataImportExportUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年12月20日上午10:22:22
 * @remark 
 */
@Service
public class ExcelExport implements DataExport {

	private final static String FILEFLAG = ".xlsx";
	@SuppressWarnings("rawtypes")
	@Override
	public boolean export(List<Map> datalist, String filename) throws WorkFlowJobException {
		try {
			DataImportExportUtil.createExcelSheeet(datalist,filename+FILEFLAG);
			return true;
		} catch (Exception e) {
			throw new WorkFlowJobException("生成excel失败！",e);
		}
	}

}
