package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TSysTimerScheduleconfig;
import com.gasq.bdp.logn.model.TSysTimerScheduleconfigExample;
import java.util.List;
import java.util.Map;

public interface TSysTimerScheduleconfigService {
    long selectScheduleCount(TSysTimerScheduleconfig scheduleconfig) throws Exception;

    int deleteByExample(TSysTimerScheduleconfigExample example) throws Exception;

    int deleteByPrimaryKey(Long id) throws Exception;

    TSysTimerScheduleconfig selectByPrimaryKey(Long id) throws Exception;

    List<TSysTimerScheduleconfig> selectByExamplePaging(TSysTimerScheduleconfig scheduleconfig,int offset,int limit) throws Exception;

	int saveOrUpdate(TSysTimerScheduleconfig scheduleconfig) throws Exception;

	boolean manualExecute(int id) throws Exception;

	List<Map<String, Object>> queryScheduleTree(TSysTimerScheduleconfig bean);

	List<TSysTimerScheduleconfig> selectByExample(TSysTimerScheduleconfig bean) throws Exception;

}