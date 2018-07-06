package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TCustomerProjectLog;
import com.gasq.bdp.logn.model.TCustomerProjectLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TCustomerProjectLogMapper {
    long countByExample(TCustomerProjectLogExample example);

    int deleteByExample(TCustomerProjectLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCustomerProjectLog record);

    int insertSelective(TCustomerProjectLog record);

    List<TCustomerProjectLog> selectByExampleWithRowbounds(TCustomerProjectLogExample example, RowBounds rowBounds);

    List<TCustomerProjectLog> selectByExample(TCustomerProjectLogExample example);

    TCustomerProjectLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCustomerProjectLog record, @Param("example") TCustomerProjectLogExample example);

    int updateByExample(@Param("record") TCustomerProjectLog record, @Param("example") TCustomerProjectLogExample example);

    int updateByPrimaryKeySelective(TCustomerProjectLog record);

    int updateByPrimaryKey(TCustomerProjectLog record);
}