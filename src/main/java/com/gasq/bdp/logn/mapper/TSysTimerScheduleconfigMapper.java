package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerScheduleconfig;
import com.gasq.bdp.logn.model.TSysTimerScheduleconfigExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerScheduleconfigMapper {
    long countByExample(TSysTimerScheduleconfigExample example);

    int deleteByExample(TSysTimerScheduleconfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TSysTimerScheduleconfig record);

    int insertSelective(TSysTimerScheduleconfig record);

    List<TSysTimerScheduleconfig> selectByExampleWithRowbounds(TSysTimerScheduleconfigExample example, RowBounds rowBounds);

    List<TSysTimerScheduleconfig> selectByExample(TSysTimerScheduleconfigExample example);

    TSysTimerScheduleconfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TSysTimerScheduleconfig record, @Param("example") TSysTimerScheduleconfigExample example);

    int updateByExample(@Param("record") TSysTimerScheduleconfig record, @Param("example") TSysTimerScheduleconfigExample example);

    int updateByPrimaryKeySelective(TSysTimerScheduleconfig record);

    int updateByPrimaryKey(TSysTimerScheduleconfig record);

	List<Map<String, Object>> queryScheduleTree(@Param("record") TSysTimerScheduleconfig bean);
}