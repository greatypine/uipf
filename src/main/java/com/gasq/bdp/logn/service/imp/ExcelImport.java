/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.service.DataImport;
import com.gasq.bdp.logn.utils.ImportExcelUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年12月20日上午10:22:22
 * @remark 
 */
@Service
public class ExcelImport implements DataImport {

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> execute(List<String> columns, String filename,Integer startIndex) throws WorkFlowJobException {
		List<Map> list = ImportExcelUtil.getBankListByExcel(columns,filename,startIndex);
		return list;
	}
}
