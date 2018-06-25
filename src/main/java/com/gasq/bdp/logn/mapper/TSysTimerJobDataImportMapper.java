package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerJobDataImport;
import com.gasq.bdp.logn.model.TSysTimerJobDataImportExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerJobDataImportMapper {
    long countByExample(TSysTimerJobDataImportExample example);

    int deleteByExample(TSysTimerJobDataImportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysTimerJobDataImport record);

    int insertSelective(TSysTimerJobDataImport record);

    List<TSysTimerJobDataImport> selectByExampleWithRowbounds(TSysTimerJobDataImportExample example, RowBounds rowBounds);

    List<TSysTimerJobDataImport> selectByExample(TSysTimerJobDataImportExample example);

    TSysTimerJobDataImport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysTimerJobDataImport record, @Param("example") TSysTimerJobDataImportExample example);

    int updateByExample(@Param("record") TSysTimerJobDataImport record, @Param("example") TSysTimerJobDataImportExample example);

    int updateByPrimaryKeySelective(TSysTimerJobDataImport record);

    int updateByPrimaryKey(TSysTimerJobDataImport record);

	List<Map<String, Object>> querySqlpagingList(Map<String, Object> params);
}