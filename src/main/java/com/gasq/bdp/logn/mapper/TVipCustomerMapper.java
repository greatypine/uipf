package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TLtnCustomerConsumptonAmount;
import com.gasq.bdp.logn.model.TVipCustomer;
import com.gasq.bdp.logn.model.TVipCustomerExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TVipCustomerMapper {
    long countByExample(TVipCustomerExample example);

    int deleteByExample(TVipCustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TVipCustomer record);

    int insertSelective(TVipCustomer record);

    List<TVipCustomer> selectByExampleWithRowbounds(TVipCustomerExample example, RowBounds rowBounds);

    List<TVipCustomer> selectByExample(TVipCustomerExample example);

    TVipCustomer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TVipCustomer record, @Param("example") TVipCustomerExample example);

    int updateByExample(@Param("record") TVipCustomer record, @Param("example") TVipCustomerExample example);

    int updateByPrimaryKeySelective(TVipCustomer record);

    int updateByPrimaryKey(TVipCustomer record);

	List<Map<String, Object>> queryPagingList(@Param("record") TVipCustomer bean);

	List<Map<String, Object>> queryMapGridList(@Param("record") TVipCustomer bean);

	List<Map<String, Object>> queryMapGridChildren(@Param("record") TLtnCustomerConsumptonAmount tca);

	Integer countByBean(@Param("record") TVipCustomer bean);
}