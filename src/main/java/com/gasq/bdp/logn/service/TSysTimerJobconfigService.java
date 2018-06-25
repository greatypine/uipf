package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TSysTimerJobconfig;
import com.gasq.bdp.logn.model.TSysTimerJobconfigExample;
import com.gasq.bdp.logn.model.TSysTimerJobconfigKey;

import java.util.List;
import java.util.Map;

public interface TSysTimerJobconfigService {
    long countByExample(TSysTimerJobconfigExample example);

    int deleteByExample(TSysTimerJobconfigExample example);

    int deleteByPrimaryKey(TSysTimerJobconfigKey key);

    int insert(TSysTimerJobconfig record);

    List<TSysTimerJobconfig> queryList(TSysTimerJobconfigExample example);

    TSysTimerJobconfig selectByPrimaryKey(TSysTimerJobconfigKey key);

    int updateByPrimaryKeySelective(TSysTimerJobconfig record);

    Map<String, Object> queryJobViewList(int wfid);

	boolean saveJobs(List<Map<String, Object>> data);

	List<TSysTimerJobconfig> getWorkFlowNodes(TSysTimerJobconfigKey jobconfigKey);

	boolean removeNode(TSysTimerJobconfig job);
	
}