/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.TSysDataColumns;
import com.gasq.bdp.logn.service.DataImport;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DataImportExportUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年12月20日上午10:22:22
 * @remark 
 */
@Service
public class JsonImport implements DataImport {

	@SuppressWarnings({"rawtypes"})
	@Override
	public List<Map> execute(List<TSysDataColumns> columns,String filename, Integer startIndex) throws WorkFlowJobException {
		List<Map> rst = new ArrayList<Map>();
		List<Map> rst1 = new ArrayList<Map>();
		try {
			String readstr = FileUtils.readFileToString(new File(filename), "UTF-8");
			if(readstr.startsWith("[")&& readstr.endsWith("]")) {
				rst = CommonUtils.json2List(readstr, Map.class);
			}else {
				Map map = CommonUtils.json2Map(readstr);
				rst.add(map);
			}
			if(columns!=null && columns.size()>0) {
				rst1 = DataImportExportUtil.paramsJsonImportHandle(columns, rst);
			}else {
				rst1 = rst;
			}
		} catch (Exception e) {
			throw new WorkFlowJobException("导入json文件:"+filename+"失败！",e);
		}
		return rst1;
	}
}
