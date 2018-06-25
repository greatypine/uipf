package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerLog;
import com.gasq.bdp.logn.model.TSysTimerLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerLogMapper {
    long countByExample(TSysTimerLogExample example);

    int deleteByExample(TSysTimerLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysTimerLog record);

    int insertSelective(TSysTimerLog record);

    List<TSysTimerLog> selectByExampleWithBLOBsWithRowbounds(TSysTimerLogExample example, RowBounds rowBounds);

    List<TSysTimerLog> selectByExampleWithBLOBs(TSysTimerLogExample example);

    List<TSysTimerLog> selectByExampleWithRowbounds(TSysTimerLogExample example, RowBounds rowBounds);

    List<TSysTimerLog> selectByExample(TSysTimerLogExample example);

    TSysTimerLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysTimerLog record, @Param("example") TSysTimerLogExample example);

    int updateByExampleWithBLOBs(@Param("record") TSysTimerLog record, @Param("example") TSysTimerLogExample example);

    int updateByExample(@Param("record") TSysTimerLog record, @Param("example") TSysTimerLogExample example);

    int updateByPrimaryKeySelective(TSysTimerLog record);

    int updateByPrimaryKeyWithBLOBs(TSysTimerLog record);

    int updateByPrimaryKey(TSysTimerLog record);

	Integer getMaxNewId();
}