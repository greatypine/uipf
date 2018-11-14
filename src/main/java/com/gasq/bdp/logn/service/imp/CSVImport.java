/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.service.DataImport;
/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年12月20日上午10:22:22
 * @remark 
 */
@Service
public class CSVImport implements DataImport {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> execute(List<String> columns,String filename, Integer startIndex) throws WorkFlowJobException {
		List<Map> rst = new ArrayList<Map>();
		try {
			List lines = FileUtils.readLines(new File(filename), "UTF-8");
			if(columns!=null && columns.size()>0) {
				Map map = null;
				for (Object object : lines) {
					map = new HashMap();
					int i = 0;
					String[] linedata = object.toString().split(" ");
					for (String key : columns) {
						map.put(key, linedata[i]);
						i++;
					}
					rst.add(map);
				}
			}
		} catch (Exception e) {
			throw new WorkFlowJobException("生成txt失败！",e);
		}
		return rst;
	}
}
