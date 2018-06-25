package com.gasq.bdp.logn.job;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.iexception.InterfaceTypeException;
import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.PagingBean;
import com.gasq.bdp.logn.model.ParamsObject;
import com.gasq.bdp.logn.model.TSysDataColumns;
import com.gasq.bdp.logn.model.TSysDataColumnsExample;
import com.gasq.bdp.logn.model.TSysTimerJobDataImport;
import com.gasq.bdp.logn.service.TSysDataColumnsService;
import com.gasq.bdp.logn.service.TSysDataService;
import com.gasq.bdp.logn.utils.DataImportStrategyContains;
import com.gasq.bdp.logn.utils.DataImportType;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.job
 * @creatTime 2017年12月19日上午11:03:47
 * @remark
 */
@Component
public class DataImportJob implements IJob {
	
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
		TSysTimerJobDataImport bean = (TSysTimerJobDataImport) params;
		String filename = getFullName(bean);
		Integer ipm = bean.getOutputParams();
		String path = bean.getInputpath();
		List<Map> result = null;
		List<TSysDataColumns> listdc = null;
		path = path.endsWith("/")?path:path+"/";
		if(ipm!=null && ipm>0) {
			TSysDataColumnsExample example = new TSysDataColumnsExample();
			example.createCriteria().andDataIdEqualTo(ipm).andStatusEqualTo(true);
			example.setOrderByClause(" id asc ");
			listdc = dataColumnsService.selectByExample(example);
		}else {
			throw new WorkFlowJobException("输出参数不能为空！");
		}
		filename = path+filename;
		//导出策略类 根据传入的导出服务类 导出数据库指定好的文件格式
		try {
			DataImportStrategyContains desc = new DataImportStrategyContains(DataImportType.valueOf(bean.getType()).getDataImportTypeService());
			result = desc.execute(listdc, filename,bean.getStartindex());
		} catch (InterfaceTypeException e) {
			throw new WorkFlowJobException("获取导出服务类失败！",e);
		}
		log.info("导入文件《"+filename+"》成功！导入数据("+result.size()+")条！");
		destory();
		return result;
	}
	
	private String getFullName(TSysTimerJobDataImport bean) {
		String filename = bean.getFilename();
		String nametype = bean.getNameType();
		String strdate = nametype==null?"":DateUtil.getDateStr(DateUtil.getSysCurrentDate(), nametype);
		String fileAllName = filename+strdate;
		return fileAllName;
	}
}