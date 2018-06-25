package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerJobconfig;
import com.gasq.bdp.logn.model.TSysTimerJobconfigExample;
import com.gasq.bdp.logn.model.TSysTimerJobconfigKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerJobconfigMapper {
    long countByExample(TSysTimerJobconfigExample example);

    int deleteByExample(TSysTimerJobconfigExample example);

    int deleteByPrimaryKey(TSysTimerJobconfigKey key);

    int insert(TSysTimerJobconfig record);

    int insertSelective(TSysTimerJobconfig record);

    List<TSysTimerJobconfig> selectByExampleWithRowbounds(TSysTimerJobconfigExample example, RowBounds rowBounds);

    List<TSysTimerJobconfig> selectByExample(TSysTimerJobconfigExample example);

    TSysTimerJobconfig selectByPrimaryKey(TSysTimerJobconfigKey key);

    int updateByExampleSelective(@Param("record") TSysTimerJobconfig record, @Param("example") TSysTimerJobconfigExample example);

    int updateByExample(@Param("record") TSysTimerJobconfig record, @Param("example") TSysTimerJobconfigExample example);

    int updateByPrimaryKeySelective(TSysTimerJobconfig record);

    int updateByPrimaryKey(TSysTimerJobconfig record);
}