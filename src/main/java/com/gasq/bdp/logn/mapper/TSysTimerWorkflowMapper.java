package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerWorkflow;
import com.gasq.bdp.logn.model.TSysTimerWorkflowExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerWorkflowMapper {
    long countByExample(TSysTimerWorkflowExample example);

    int deleteByExample(TSysTimerWorkflowExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TSysTimerWorkflow record);

    int insertSelective(TSysTimerWorkflow record);

    List<TSysTimerWorkflow> selectByExampleWithRowbounds(TSysTimerWorkflowExample example, RowBounds rowBounds);

    List<TSysTimerWorkflow> selectByExample(TSysTimerWorkflowExample example);

    TSysTimerWorkflow selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TSysTimerWorkflow record, @Param("example") TSysTimerWorkflowExample example);

    int updateByExample(@Param("record") TSysTimerWorkflow record, @Param("example") TSysTimerWorkflowExample example);

    int updateByPrimaryKeySelective(TSysTimerWorkflow record);

    int updateByPrimaryKey(TSysTimerWorkflow record);

	List<Map<String, Object>> queryWorkFlowTree(Map<String, Object> result);

	List<Map<String, Object>> getWorkflowJobList(Map<String, Object> params);

	List<Map<String, Object>> getWorkflowJobType(Integer id);
}