package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TCustomerConsumptonLog;
import com.gasq.bdp.logn.model.TCustomerConsumptonLogExample;
import com.gasq.bdp.logn.model.TCustomerConsumptonLogKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TCustomerConsumptonLogMapper {
    long countByExample(TCustomerConsumptonLogExample example);

    int deleteByExample(TCustomerConsumptonLogExample example);

    int deleteByPrimaryKey(TCustomerConsumptonLogKey key);

    int insert(TCustomerConsumptonLog record);

    int insertSelective(TCustomerConsumptonLog record);

    List<TCustomerConsumptonLog> selectByExampleWithRowbounds(TCustomerConsumptonLogExample example, RowBounds rowBounds);

    List<TCustomerConsumptonLog> selectByExample(TCustomerConsumptonLogExample example);

    TCustomerConsumptonLog selectByPrimaryKey(TCustomerConsumptonLogKey key);

    int updateByExampleSelective(@Param("record") TCustomerConsumptonLog record, @Param("example") TCustomerConsumptonLogExample example);

    int updateByExample(@Param("record") TCustomerConsumptonLog record, @Param("example") TCustomerConsumptonLogExample example);

    int updateByPrimaryKeySelective(TCustomerConsumptonLog record);

    int updateByPrimaryKey(TCustomerConsumptonLog record);
}