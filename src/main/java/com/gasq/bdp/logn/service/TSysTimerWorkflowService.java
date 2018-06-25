package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TSysTimerWorkflow;
import com.gasq.bdp.logn.model.TSysTimerWorkflowExample;
import java.util.List;
import java.util.Map;

public interface TSysTimerWorkflowService {
    long countByExample(TSysTimerWorkflowExample example);

    int deleteByExample(TSysTimerWorkflowExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TSysTimerWorkflow record);

    int updateByExample(TSysTimerWorkflow record, TSysTimerWorkflowExample example);

    int updateByPrimaryKeySelective(TSysTimerWorkflow record);
    
	Map<String, Object> queryWorkFlowTree(Integer pid);

	List<Map<String, Object>> getWorkflowJobList(Integer type, Integer jobid);

	List<Map<String, Object>> getWorkflowJobType(Integer id);

	List<TSysTimerWorkflow> queryList(TSysTimerWorkflow workflow);

	Boolean saveOrUpdate(TSysTimerWorkflow workflow);

	Boolean delete(int id);
}