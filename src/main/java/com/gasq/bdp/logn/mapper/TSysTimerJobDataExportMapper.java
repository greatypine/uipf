package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerJobDataExport;
import com.gasq.bdp.logn.model.TSysTimerJobDataExportExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerJobDataExportMapper {
    long countByExample(TSysTimerJobDataExportExample example);

    int deleteByExample(TSysTimerJobDataExportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysTimerJobDataExport record);

    int insertSelective(TSysTimerJobDataExport record);

    List<TSysTimerJobDataExport> selectByExampleWithRowbounds(TSysTimerJobDataExportExample example, RowBounds rowBounds);

    List<TSysTimerJobDataExport> selectByExample(TSysTimerJobDataExportExample example);

    TSysTimerJobDataExport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysTimerJobDataExport record, @Param("example") TSysTimerJobDataExportExample example);

    int updateByExample(@Param("record") TSysTimerJobDataExport record, @Param("example") TSysTimerJobDataExportExample example);

    int updateByPrimaryKeySelective(TSysTimerJobDataExport record);

    int updateByPrimaryKey(TSysTimerJobDataExport record);

	List<Map<String, Object>> querySqlpagingList(Map<String, Object> params);

	List<Map<String, Object>> queryExprotType();
}