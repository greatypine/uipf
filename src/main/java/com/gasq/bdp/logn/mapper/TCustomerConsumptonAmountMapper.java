package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TCustomerConsumptonAmount;
import com.gasq.bdp.logn.model.TCustomerConsumptonAmountExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TCustomerConsumptonAmountMapper {
    long countByExample(TCustomerConsumptonAmountExample example);

    int deleteByExample(TCustomerConsumptonAmountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCustomerConsumptonAmount record);

    int insertSelective(TCustomerConsumptonAmount record);

    List<TCustomerConsumptonAmount> selectByExampleWithRowbounds(TCustomerConsumptonAmountExample example, RowBounds rowBounds);

    List<TCustomerConsumptonAmount> selectByExample(TCustomerConsumptonAmountExample example);

    TCustomerConsumptonAmount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCustomerConsumptonAmount record, @Param("example") TCustomerConsumptonAmountExample example);

    int updateByExample(@Param("record") TCustomerConsumptonAmount record, @Param("example") TCustomerConsumptonAmountExample example);

    int updateByPrimaryKeySelective(TCustomerConsumptonAmount record);

    int updateByPrimaryKey(TCustomerConsumptonAmount record);

    List<Map<String, Object>> queryPagingList(@Param("record") TCustomerConsumptonAmount bean);
    
	Integer countByBean(@Param("record") TCustomerConsumptonAmount bean);
}