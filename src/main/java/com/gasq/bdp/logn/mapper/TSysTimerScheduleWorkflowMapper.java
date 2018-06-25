package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflow;
import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflowExample;
import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflowKey;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerScheduleWorkflowMapper {
    long countByExample(TSysTimerScheduleWorkflowExample example);

    int deleteByExample(TSysTimerScheduleWorkflowExample example);

    int deleteByPrimaryKey(TSysTimerScheduleWorkflowKey key);

    int insert(TSysTimerScheduleWorkflow record);

    int insertSelective(TSysTimerScheduleWorkflow record);

    List<TSysTimerScheduleWorkflow> selectByExampleWithRowbounds(TSysTimerScheduleWorkflowExample example, RowBounds rowBounds);

    List<TSysTimerScheduleWorkflow> selectByExample(TSysTimerScheduleWorkflowExample example);

    TSysTimerScheduleWorkflow selectByPrimaryKey(TSysTimerScheduleWorkflowKey key);

    int updateByExampleSelective(@Param("record") TSysTimerScheduleWorkflow record, @Param("example") TSysTimerScheduleWorkflowExample example);

    int updateByExample(@Param("record") TSysTimerScheduleWorkflow record, @Param("example") TSysTimerScheduleWorkflowExample example);

    int updateByPrimaryKeySelective(TSysTimerScheduleWorkflow record);

    int updateByPrimaryKey(TSysTimerScheduleWorkflow record);

	List<Map<String, Object>> getScheduleWorkflowById(TSysTimerScheduleWorkflowKey key);
}