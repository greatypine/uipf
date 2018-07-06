package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TCustomerPorject;
import com.gasq.bdp.logn.model.TCustomerPorjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TCustomerPorjectMapper {
    long countByExample(TCustomerPorjectExample example);

    int deleteByExample(TCustomerPorjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCustomerPorject record);

    int insertSelective(TCustomerPorject record);

    List<TCustomerPorject> selectByExampleWithRowbounds(TCustomerPorjectExample example, RowBounds rowBounds);

    List<TCustomerPorject> selectByExample(TCustomerPorjectExample example);

    TCustomerPorject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCustomerPorject record, @Param("example") TCustomerPorjectExample example);

    int updateByExample(@Param("record") TCustomerPorject record, @Param("example") TCustomerPorjectExample example);

    int updateByPrimaryKeySelective(TCustomerPorject record);

    int updateByPrimaryKey(TCustomerPorject record);
}