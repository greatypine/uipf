/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.service.DataExport;
import com.gasq.bdp.logn.utils.CommonUtils;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年12月20日上午10:22:22
 * @remark 
 */
@Service
public class JsonExport implements DataExport {

	private final static String FILEFLAG = ".json";
	@SuppressWarnings("rawtypes")
	@Override
	public boolean export(List<Map> datalist, String filename) throws WorkFlowJobException {
		try {
			String data = CommonUtils.bean2Json(datalist);
			FileUtils.writeStringToFile(new File(filename+FILEFLAG),data,"UTF-8");
			return true;
		} catch (Exception e) {
			throw new WorkFlowJobException("生成txt失败！",e);
		}
	}
}
