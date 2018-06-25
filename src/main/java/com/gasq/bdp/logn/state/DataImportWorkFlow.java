package com.gasq.bdp.logn.state;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.job.DataImportJob;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.PagingBean;
import com.gasq.bdp.logn.model.TSysTimerJobDataImport;
import com.gasq.bdp.logn.model.TSysTimerJobDataImportExample;
import com.gasq.bdp.logn.service.TSysTimerJobDataImportService;
import com.gasq.bdp.logn.job.IJob;
import com.gasq.bdp.logn.job.InterfaceType;

/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.state
 * @creatTime 2017年12月19日上午9:48:54
 * @remark
 */
@Component
public class DataImportWorkFlow extends WorkFlowState {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired DataImportJob excelImportJob;
	@Autowired TSysTimerJobDataImportService excelExportService;
	@Override
	public Object run(int excute_id,Object inputObj) throws WorkFlowStateException {
		return run(excute_id, inputObj, null);
	}

	@Override
	public void stop() {
		log.info("导入excel节点关闭.............");
	}

	@Override
	public IJob getJob() {
		return excelImportJob;
	}

	@Override
	public StateContext getContext() {
		return context;
	}

	@Override
	public Object run(int excute_id, Object inputObj, PagingBean pb) throws WorkFlowStateException {
		super.context.setWorkFlowState(context.excelExportWorkFlow);
		TSysTimerJobDataImportExample esexample = new TSysTimerJobDataImportExample();
		esexample.createCriteria().andStatusEqualTo(true).andIdEqualTo(excute_id);
		List<TSysTimerJobDataImport> lists = excelExportService.selectByExample(esexample);
		if(lists.size()<=0) throw new WorkFlowStateException("执行导入数据失败！导出对象为空。");
		context.setStatus(InitProperties.JOB_STATUS_RUNING);
		context.setExetype(InterfaceType.DataImport.getValue());
		context.setTaskname(lists.get(0).getName());
		Object obj = excelImportJob.execute(lists.get(0),inputObj);
		log.info("导入数据执行完毕.............");
		return obj;
	}
}
