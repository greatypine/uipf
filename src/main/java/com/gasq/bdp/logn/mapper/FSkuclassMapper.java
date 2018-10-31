package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.FSkuclass;
import com.gasq.bdp.logn.model.FSkuclassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FSkuclassMapper {
    long countByExample(FSkuclassExample example);

    int deleteByExample(FSkuclassExample example);

    int deleteByPrimaryKey(String id);

    int insert(FSkuclass record);

    int insertSelective(FSkuclass record);

    List<FSkuclass> selectByExampleWithRowbounds(FSkuclassExample example, RowBounds rowBounds);

    List<FSkuclass> selectByExample(FSkuclassExample example);

    FSkuclass selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FSkuclass record, @Param("example") FSkuclassExample example);

    int updateByExample(@Param("record") FSkuclass record, @Param("example") FSkuclassExample example);

    int updateByPrimaryKeySelective(FSkuclass record);

    int updateByPrimaryKey(FSkuclass record);
}