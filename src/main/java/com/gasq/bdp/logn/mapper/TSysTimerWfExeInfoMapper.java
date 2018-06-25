package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerWfExeInfo;
import com.gasq.bdp.logn.model.TSysTimerWfExeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerWfExeInfoMapper {
    long countByExample(TSysTimerWfExeInfoExample example);

    int deleteByExample(TSysTimerWfExeInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysTimerWfExeInfo record);

    int insertSelective(TSysTimerWfExeInfo record);

    List<TSysTimerWfExeInfo> selectByExampleWithBLOBsWithRowbounds(TSysTimerWfExeInfoExample example, RowBounds rowBounds);

    List<TSysTimerWfExeInfo> selectByExampleWithBLOBs(TSysTimerWfExeInfoExample example);

    List<TSysTimerWfExeInfo> selectByExampleWithRowbounds(TSysTimerWfExeInfoExample example, RowBounds rowBounds);

    List<TSysTimerWfExeInfo> selectByExample(TSysTimerWfExeInfoExample example);

    TSysTimerWfExeInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysTimerWfExeInfo record, @Param("example") TSysTimerWfExeInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") TSysTimerWfExeInfo record, @Param("example") TSysTimerWfExeInfoExample example);

    int updateByExample(@Param("record") TSysTimerWfExeInfo record, @Param("example") TSysTimerWfExeInfoExample example);

    int updateByPrimaryKeySelective(TSysTimerWfExeInfo record);

    int updateByPrimaryKeyWithBLOBs(TSysTimerWfExeInfo record);

    int updateByPrimaryKey(TSysTimerWfExeInfo record);
}