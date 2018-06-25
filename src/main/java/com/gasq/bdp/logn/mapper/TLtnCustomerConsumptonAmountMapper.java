package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TLtnCustomerConsumptonAmount;
import com.gasq.bdp.logn.model.TLtnCustomerConsumptonAmountExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TLtnCustomerConsumptonAmountMapper {
    long countByExample(TLtnCustomerConsumptonAmountExample example);

    int deleteByExample(TLtnCustomerConsumptonAmountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TLtnCustomerConsumptonAmount record);

    int insertSelective(TLtnCustomerConsumptonAmount record);

    List<TLtnCustomerConsumptonAmount> selectByExampleWithRowbounds(TLtnCustomerConsumptonAmountExample example, RowBounds rowBounds);

    List<TLtnCustomerConsumptonAmount> selectByExample(TLtnCustomerConsumptonAmountExample example);

    TLtnCustomerConsumptonAmount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TLtnCustomerConsumptonAmount record, @Param("example") TLtnCustomerConsumptonAmountExample example);

    int updateByExample(@Param("record") TLtnCustomerConsumptonAmount record, @Param("example") TLtnCustomerConsumptonAmountExample example);

    int updateByPrimaryKeySelective(TLtnCustomerConsumptonAmount record);

    int updateByPrimaryKey(TLtnCustomerConsumptonAmount record);

	List<Map<String, Object>> queryPagingList(@Param("record") TLtnCustomerConsumptonAmount bean);
}