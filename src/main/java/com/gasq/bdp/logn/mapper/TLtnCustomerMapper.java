package com.gasq.bdp.logn.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.gasq.bdp.logn.model.TLtnCustomer;
import com.gasq.bdp.logn.model.TLtnCustomerExample;

public interface TLtnCustomerMapper {
    long countByExample(TLtnCustomerExample example);

    int deleteByExample(TLtnCustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TLtnCustomer record);

    int insertSelective(TLtnCustomer record);

    List<TLtnCustomer> selectByExampleWithRowbounds(TLtnCustomerExample example, RowBounds rowBounds);

    List<TLtnCustomer> selectByExample(TLtnCustomerExample example);

    TLtnCustomer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TLtnCustomer record, @Param("example") TLtnCustomerExample example);

    int updateByExample(@Param("record") TLtnCustomer record, @Param("example") TLtnCustomerExample example);

    int updateByPrimaryKeySelective(TLtnCustomer record);

    int updateByPrimaryKey(TLtnCustomer record);
    
    List<Map<String, Object>> queryPagingList(@Param("record") TLtnCustomer bean);
    
	Map<String, Object> queryAmountSum(@Param("record") TLtnCustomer bean);

	List<Map<String, Object>> queryExportDataList(@Param("record") TLtnCustomer customer);

	List<Map<String, Object>> countConsumptionReport(Map<String, Object> map);

	Map<String, Object> getConsumptionProjects(Integer id);

	Integer countByBean(@Param("record") TLtnCustomer bean);

	List<Map<String, Object>> queryPagingListExt(@Param("record") TLtnCustomer bean);
}