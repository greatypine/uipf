/**
 * 
 */
package com.gasq.bdp.logn.state;

import com.gasq.bdp.logn.job.IJob;
import com.gasq.bdp.logn.job.InterfaceType;
import com.gasq.bdp.logn.job.SqlInsertOrUpdateJob;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.PagingBean;
import com.gasq.bdp.logn.model.TSysTimerJobSqlInsertUpdate;
import com.gasq.bdp.logn.model.TSysTimerJobSqlInsertUpdateExample;
import com.gasq.bdp.logn.service.TSysTimerJobSqlInsertOrUpdateService;
/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.state
 * @creatTime 2017年11月23日上午11:25:51
 * @remark
 */
@Component
public class SqlInsertOrUpdateWorkFlow extends WorkFlowState {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired SqlInsertOrUpdateJob insertOrUpdateJob;
	@Autowired TSysTimerJobSqlInsertOrUpdateService insertOrUpdateService;
	@Override
	public Object run(int excute_id,Object inputObj) throws WorkFlowStateException {
		return run(excute_id, inputObj, null);
	}

	@Override
	public void stop() {
		log.info("sqlquery节点关闭.............");
	}

	@Override
	public IJob getJob() {
		return insertOrUpdateJob;
	}

	@Override
	public StateContext getContext() {
		return context;
	}

	@Override
	public Object run(int excute_id, Object inputObj, PagingBean pb) throws WorkFlowStateException {
		super.context.setWorkFlowState(context.insertOrUpdateWorkFlow);
		TSysTimerJobSqlInsertUpdateExample example = new TSysTimerJobSqlInsertUpdateExample();
		example.createCriteria().andStatusEqualTo(true).andIdEqualTo((long)excute_id);
		List<TSysTimerJobSqlInsertUpdate> lists = insertOrUpdateService.selectByExample(example);
		if(lists.size()<=0) throw new WorkFlowStateException("执行更新或插入节点失败！对象为空或状态已经关闭。");
		context.setStatus(InitProperties.JOB_STATUS_RUNING);
		context.setExetype(InterfaceType.InsertOrUpdate.getValue());
		context.setTaskname(lists.get(0).getName());
		Object obj = insertOrUpdateJob.execute(lists.get(0),inputObj,pb);
		log.info(lists.get(0).getName()+"操作完毕.............");
		return obj;
	}

}
