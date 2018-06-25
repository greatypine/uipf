/**
 * 
 */
package com.gasq.bdp.logn.state;

import com.gasq.bdp.logn.job.EndJob;
import com.gasq.bdp.logn.job.IJob;
import com.gasq.bdp.logn.job.InterfaceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.PagingBean;
/**
 * @author 巨伟刚
 * @packageName com.hadoop.state
 * @creatTime 2017年9月8日下午12:16:11
 * @remark 
 */
@Component
public class EndWorkFlow extends WorkFlowState {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired EndJob endjob;
	@Override
	public Object run(int excute_id,Object inputObj) throws WorkFlowStateException {
		return run(excute_id,inputObj,null);
	}

	@Override
	public void stop() {
		log.info("结束节点关闭.............");
	}

	@Override
	public IJob getJob() {
		return endjob;
	}

	@Override
	public StateContext getContext() {
		return context;
	}

	@Override
	public Object run(int excute_id, Object inputObj, PagingBean pb) throws WorkFlowStateException {
		super.context.setWorkFlowState(context.endWorkFlow);
		context.setStatus(InitProperties.JOB_STATUS_RUNING);
		context.setExetype(InterfaceType.end.getValue());
		context.setTaskname(InterfaceType.getValueName(InterfaceType.end.getValue()));
		log.info("结束节点运行完毕.............");
		return null;
	}

}