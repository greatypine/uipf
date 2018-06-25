package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TCustomerSubscribe;
import com.gasq.bdp.logn.model.TCustomerSubscribeExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TCustomerSubscribeMapper {
    long countByExample(TCustomerSubscribeExample example);

    int deleteByExample(TCustomerSubscribeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCustomerSubscribe record);

    int insertSelective(TCustomerSubscribe record);

    List<TCustomerSubscribe> selectByExampleWithRowbounds(TCustomerSubscribeExample example, RowBounds rowBounds);

    List<TCustomerSubscribe> selectByExample(TCustomerSubscribeExample example);

    TCustomerSubscribe selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCustomerSubscribe record, @Param("example") TCustomerSubscribeExample example);

    int updateByExample(@Param("record") TCustomerSubscribe record, @Param("example") TCustomerSubscribeExample example);

    int updateByPrimaryKeySelective(TCustomerSubscribe record);

    int updateByPrimaryKey(TCustomerSubscribe record);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);
}