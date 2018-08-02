package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TWorkforcemanagement;
import com.gasq.bdp.logn.model.TWorkforcemanagementExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TWorkforcemanagementMapper {
    long countByExample(TWorkforcemanagementExample example);

    int deleteByExample(TWorkforcemanagementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TWorkforcemanagement record);

    int insertSelective(TWorkforcemanagement record);

    List<TWorkforcemanagement> selectByExampleWithRowbounds(TWorkforcemanagementExample example, RowBounds rowBounds);

    List<TWorkforcemanagement> selectByExample(TWorkforcemanagementExample example);

    TWorkforcemanagement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TWorkforcemanagement record, @Param("example") TWorkforcemanagementExample example);

    int updateByExample(@Param("record") TWorkforcemanagement record, @Param("example") TWorkforcemanagementExample example);

    int updateByPrimaryKeySelective(TWorkforcemanagement record);

    int updateByPrimaryKey(TWorkforcemanagement record);

	void insertBatch(@Param("list") List<TWorkforcemanagement> beans);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);

	Integer counTWorkforcemanagementByBean(Map<String, Object> params);

	List<Map<String, Object>> queryExportDataList(@Param("record") TWorkforcemanagement bean);
}