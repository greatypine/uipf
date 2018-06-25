package com.gasq.bdp.logn.state;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.job.DataExportJob;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.PagingBean;
import com.gasq.bdp.logn.model.TSysTimerJobDataExport;
import com.gasq.bdp.logn.model.TSysTimerJobDataExportExample;
import com.gasq.bdp.logn.service.TSysTimerJobDataExportService;
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
public class DataExportWorkFlow extends WorkFlowState {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired DataExportJob excelExportJob;
	@Autowired TSysTimerJobDataExportService excelExportService;
	@Override
	public Object run(int excute_id,Object inputObj) throws WorkFlowStateException {
		return run(excute_id, inputObj, null);
	}

	@Override
	public void stop() {
		log.info("导出数据节点关闭.............");
	}

	@Override
	public IJob getJob() {
		return excelExportJob;
	}

	@Override
	public StateContext getContext() {
		return context;
	}

	@Override
	public Object run(int excute_id, Object inputObj, PagingBean pb) throws WorkFlowStateException {
		super.context.setWorkFlowState(context.excelExportWorkFlow);
		TSysTimerJobDataExportExample esexample = new TSysTimerJobDataExportExample();
		esexample.createCriteria().andStatusEqualTo(true).andIdEqualTo(excute_id);
		List<TSysTimerJobDataExport> lists = excelExportService.selectByExample(esexample);
		if(lists.size()<=0) throw new WorkFlowStateException("执行导出数据失败！导出对象为空。");
		context.setStatus(InitProperties.JOB_STATUS_RUNING);
		context.setExetype(InterfaceType.DataExport.getValue());
		context.setTaskname(lists.get(0).getName());
		Object obj = excelExportJob.execute(lists.get(0),inputObj);
		log.info("导出数据执行完毕.............");
		return obj;
	}
}
