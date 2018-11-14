/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.service.DataExport;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年12月20日上午10:22:22
 * @remark 
 */
@Service
public class CSVExport implements DataExport {

	private final static String FILEFLAG = ".csv";
	@SuppressWarnings("rawtypes")
	@Override
	public boolean export(List<Map> datalist, String filename) throws WorkFlowJobException {
		try {
			List<String> data = new ArrayList<String>(datalist.size());
			for (Map map : datalist) {
				Object[] valueCollection = map.values().toArray();
				data.add(StringUtils.join(valueCollection, " "));
			}
			FileUtils.writeLines(new File(filename+FILEFLAG),"UTF-8",data);
			return true;
		} catch (Exception e) {
			throw new WorkFlowJobException("生成excel失败！",e);
		}
	}

}
