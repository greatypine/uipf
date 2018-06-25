/**
 * 
 */
package com.gasq.bdp.logn.state;

import com.gasq.bdp.logn.job.IJob;
import com.gasq.bdp.logn.job.InterfaceType;
import com.gasq.bdp.logn.job.SqlQueryOptionLogJob;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.PagingBean;
import com.gasq.bdp.logn.model.TSysTimerJobSqlQuery;
import com.gasq.bdp.logn.model.TSysTimerJobSqlQueryExample;
import com.gasq.bdp.logn.service.TSysTimerJobSqlQueryService;
/**
 * @author 巨伟刚
 * @packageName com.hadoop.state
 * @creatTime 2017年9月8日下午12:16:11
 * @remark 
 */
@Component
public class SqlQueryOptionWorkFlow extends WorkFlowState {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired SqlQueryOptionLogJob queryOptionLogJob;
	@Autowired TSysTimerJobSqlQueryService sqlqueryService;
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
		return queryOptionLogJob;
	}

	@Override
	public StateContext getContext() {
		return context;
	}

	@Override
	public Object run(int excute_id, Object inputObj, PagingBean pb) throws WorkFlowStateException {
		super.context.setWorkFlowState(context.queryOptionWorkFlow);
		TSysTimerJobSqlQueryExample sqlqueryExample = new TSysTimerJobSqlQueryExample();
		sqlqueryExample.createCriteria().andStatusEqualTo(true).andIdEqualTo((long)excute_id);
		List<TSysTimerJobSqlQuery> lists = sqlqueryService.selectByExample(sqlqueryExample);
		if(lists.size()<=0) throw new WorkFlowStateException("执行sql查询节点失败！语句为空或者状态已经关闭。");
		context.setStatus(InitProperties.JOB_STATUS_RUNING);
		context.setExetype(InterfaceType.SqlQuery.getValue());
		context.setTaskname(lists.get(0).getName());
		Object obj = queryOptionLogJob.execute(lists.get(0),inputObj,pb);
		log.info(lists.get(0).getName()+"操作完毕.............");
		return obj;
	}

}
