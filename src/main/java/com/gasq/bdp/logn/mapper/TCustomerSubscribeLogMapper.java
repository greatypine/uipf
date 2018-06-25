package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TCustomerSubscribeLog;
import com.gasq.bdp.logn.model.TCustomerSubscribeLogExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TCustomerSubscribeLogMapper {
    long countByExample(TCustomerSubscribeLogExample example);

    int deleteByExample(TCustomerSubscribeLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCustomerSubscribeLog record);

    int insertSelective(TCustomerSubscribeLog record);

    List<TCustomerSubscribeLog> selectByExampleWithRowbounds(TCustomerSubscribeLogExample example, RowBounds rowBounds);

    List<TCustomerSubscribeLog> selectByExample(TCustomerSubscribeLogExample example);

    TCustomerSubscribeLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCustomerSubscribeLog record, @Param("example") TCustomerSubscribeLogExample example);

    int updateByExample(@Param("record") TCustomerSubscribeLog record, @Param("example") TCustomerSubscribeLogExample example);

    int updateByPrimaryKeySelective(TCustomerSubscribeLog record);

    int updateByPrimaryKey(TCustomerSubscribeLog record);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);
}