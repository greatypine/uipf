package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TVipCustomerLog;
import com.gasq.bdp.logn.model.TVipCustomerLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TVipCustomerLogMapper {
    long countByExample(TVipCustomerLogExample example);

    int deleteByExample(TVipCustomerLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TVipCustomerLog record);

    int insertSelective(TVipCustomerLog record);

    List<TVipCustomerLog> selectByExampleWithRowbounds(TVipCustomerLogExample example, RowBounds rowBounds);

    List<TVipCustomerLog> selectByExample(TVipCustomerLogExample example);

    TVipCustomerLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TVipCustomerLog record, @Param("example") TVipCustomerLogExample example);

    int updateByExample(@Param("record") TVipCustomerLog record, @Param("example") TVipCustomerLogExample example);

    int updateByPrimaryKeySelective(TVipCustomerLog record);

    int updateByPrimaryKey(TVipCustomerLog record);
}