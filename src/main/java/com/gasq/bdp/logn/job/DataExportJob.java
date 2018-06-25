package com.gasq.bdp.logn.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.iexception.InterfaceTypeException;
import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.PagingBean;
import com.gasq.bdp.logn.model.ParamsObject;
import com.gasq.bdp.logn.model.TSysData;
import com.gasq.bdp.logn.model.TSysDataColumns;
import com.gasq.bdp.logn.model.TSysDataColumnsExample;
import com.gasq.bdp.logn.model.TSysTimerJobDataExport;
import com.gasq.bdp.logn.service.TSysDataColumnsService;
import com.gasq.bdp.logn.service.TSysDataService;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DataExportStrategyContains;
import com.gasq.bdp.logn.utils.DataExportType;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.job
 * @creatTime 2017年12月19日上午11:03:47
 * @remark
 */
@Component
public class DataExportJob implements IJob {
	
	private static final long serialVersionUID = 942841348557593909L;
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired TSysDataService dataService;
	@Autowired TSysDataColumnsService dataColumnsService;
	
	public Object execute(ParamsObject paramsObject, Object inputObj) throws WorkFlowJobException {
		return execute(paramsObject, inputObj, null);
	}

	public void destory() {
	}

	@SuppressWarnings({ "rawtypes"})
	@Override
	public Object execute(ParamsObject params, Object inputObj, PagingBean pb) throws WorkFlowJobException {
		TSysTimerJobDataExport bean = (TSysTimerJobDataExport) params;
		String filename = getFullName(bean);
		Integer ipm = bean.getInputParams();
		String path = bean.getOutpath();
		if(inputObj!=null) {
			boolean b = false;
			List<Map> datalist = null;
			path = path.endsWith("/")?path:path+"/";
			if(ipm!=null && ipm>0) {
				TSysData tdata = dataService.selectByPrimaryKey(ipm);
				TSysDataColumnsExample example = new TSysDataColumnsExample();
				example.createCriteria().andDataIdEqualTo(ipm).andStatusEqualTo(true);
				example.setOrderByClause(" id asc ");
				List<TSysDataColumns> listdc = dataColumnsService.selectByExample(example);
				if(listdc.size()>0) {
					datalist = paramsHandle(inputObj,listdc,tdata.getCode());
				}else {
					throw new WorkFlowJobException("输入参数配置错误！");
				}
			}else {//没有添加输入参数时 将按照传入对象导出
				datalist = transformInputParams(inputObj);
			}
			filename = path+filename;
			//导出策略类 根据传入的导出服务类 导出数据库指定好的文件格式
			try {
				DataExportStrategyContains desc = new DataExportStrategyContains(DataExportType.valueOf(bean.getType()).getDataExportTypeService());
				b = desc.export(datalist, filename);
			} catch (InterfaceTypeException e) {
				throw new WorkFlowJobException("获取导出服务类失败！",e);
			}
			if(b) {//导出成功
				 log.info("导出文件《"+filename+"》成功！文件地址："+bean.getOutpath()+"。导出数据("+datalist.size()+")条！");
			}
		}else {
			log.info("上个节点返回值为空！");
		}
		destory();
		return null;
	}
	
	private String getFullName(TSysTimerJobDataExport bean) {
		String filename = bean.getFilename();
		String nametype = bean.getNameType();
		String strdate = DateUtil.getDateStr(DateUtil.getSysCurrentDate(), nametype);
		String fileAllName = filename+strdate;
		return fileAllName;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map> paramsHandle(Object inputObj,List<TSysDataColumns> listdc, String code){
		List<Map> result = new ArrayList<Map>();
		List<Map> lm = WorkFlowUtil.transformParams(code,inputObj);
		if(lm.size()>0) {
			Map<String,Object> map1 = null;
			for (Map map : lm) {
				map1 = new HashMap<String,Object>();
				Set<Entry> set = map.entrySet();
				for (Entry entry : set) {
					String key = entry.getKey().toString();
					for (TSysDataColumns tSysDataColumns : listdc) {
						if(key.equals(tSysDataColumns.getCode())) {
							String tkey = tSysDataColumns.getName()!=null?tSysDataColumns.getName():tSysDataColumns.getCode();
							if(CommonUtils.isEmpty(tkey)) tkey = key;
							map1.put(tkey, entry.getValue());
							break;
						}
					}
				}
				System.out.println();
				result.add(map1);
			}
		}
		return result;
	}
	
//	@SuppressWarnings("rawtypes")
//	private boolean exportData(List<Map> lm,String filename) throws WorkFlowJobException{
//		try {
//			ExcelUtil.createExcelSheeet(lm,filename);
//			return true;
//		} catch (Exception e) {
//			throw new WorkFlowJobException("生成excel失败！",e);
//		}
//	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map> transformInputParams(Object inputObj){
		List<Map> datalist = null;
		Map<String, Object> map = (Map<String, Object>) CommonUtils.JsonToBean(CommonUtils.BeanToJSON(inputObj), Map.class);
		if(map.keySet().size()==1) {
			String key = map.keySet().toArray()[0].toString();
			TSysData tdata = new TSysData();
			tdata.setCode(key);
			tdata.setStatus(true);
			List<TSysData> listd = dataService.queryList(tdata);
			if(listd.size()>0) {
				datalist = CommonUtils.json2List(CommonUtils.BeanToJSON(map.get(map.keySet().toArray()[0])), Map.class);
			}else {
				datalist = new ArrayList<Map>();
				datalist.add(map);
			}
		}
		return datalist;
	}
}