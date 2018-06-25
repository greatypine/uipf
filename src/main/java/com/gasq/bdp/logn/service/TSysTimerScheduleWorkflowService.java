package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflow;
import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflowExample;
import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflowKey;

import java.util.List;
import java.util.Map;

public interface TSysTimerScheduleWorkflowService {
    long countByExample(TSysTimerScheduleWorkflowExample example);

    int deleteByExample(TSysTimerScheduleWorkflowExample example);

    int deleteByPrimaryKey(TSysTimerScheduleWorkflowKey key);

    int insert(TSysTimerScheduleWorkflow record);

    List<TSysTimerScheduleWorkflow> selectByExample(TSysTimerScheduleWorkflowExample example);

    TSysTimerScheduleWorkflow selectByPrimaryKey(TSysTimerScheduleWorkflowKey key);

    int updateByExampleSelective(TSysTimerScheduleWorkflow record,TSysTimerScheduleWorkflowExample example);

    int updateByExample(TSysTimerScheduleWorkflow record,TSysTimerScheduleWorkflowExample example);

    int updateByPrimaryKeySelective(TSysTimerScheduleWorkflow record);

    int updateByPrimaryKey(TSysTimerScheduleWorkflow record);

	Map<String, Object> getScheduleWorkflowById(TSysTimerScheduleWorkflowKey key);

	int changeWorkflowStatus(TSysTimerScheduleWorkflow scheduleWorkflow);

	int changeWorkflowSort(TSysTimerScheduleWorkflow scheduleWorkflow);

}