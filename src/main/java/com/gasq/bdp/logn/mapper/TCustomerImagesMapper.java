package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TCustomerImages;
import com.gasq.bdp.logn.model.TCustomerImagesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TCustomerImagesMapper {
    long countByExample(TCustomerImagesExample example);

    int deleteByExample(TCustomerImagesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCustomerImages record);

    int insertSelective(TCustomerImages record);

    List<TCustomerImages> selectByExampleWithRowbounds(TCustomerImagesExample example, RowBounds rowBounds);

    List<TCustomerImages> selectByExample(TCustomerImagesExample example);

    TCustomerImages selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCustomerImages record, @Param("example") TCustomerImagesExample example);

    int updateByExample(@Param("record") TCustomerImages record, @Param("example") TCustomerImagesExample example);

    int updateByPrimaryKeySelective(TCustomerImages record);

    int updateByPrimaryKey(TCustomerImages record);
}