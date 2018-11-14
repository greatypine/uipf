package com.gasq.bdp.logn.utils;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gasq.bdp.logn.iexception.InterfaceTypeException;
import com.gasq.bdp.logn.iexception.WorkFlowJobException;

/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.job
 * @creatTime 2017年12月19日上午11:03:47
 * @remark
 */
public class DataExportUtil{
	
	private static final Logger log = LoggerFactory.getLogger(DataExportUtil.class);
	
	@SuppressWarnings("rawtypes")
	public static Object exprot(List<Map> datalist,String filename,int filetype) throws WorkFlowJobException {
		log.info("开始进入导出节点程序....");
		boolean b = false;
		try {
			//导出策略类 根据传入的导出服务类 导出数据库指定好的文件格式
			DataExportStrategyContains desc = new DataExportStrategyContains(DataExportType.valueOf(filetype).getDataExportTypeService());
			b = desc.export(datalist, filename);
		} catch (InterfaceTypeException e) {
			log.error("获取导出服务类失败！"+e.getMessage());
			throw new WorkFlowJobException("获取导出服务类失败！"+e.getMessage(),e);
		}
		if(b) {//导出成功
			 log.info("导出文件《"+filename+"》成功！文件地址："+filename+"。导出数据("+datalist.size()+")条！");
		}
		return null;
	}
}