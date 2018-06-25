package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysUserExt;
import com.gasq.bdp.logn.model.TSysUserExtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysUserExtMapper {
    long countByExample(TSysUserExtExample example);

    int deleteByExample(TSysUserExtExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(TSysUserExt record);

    int insertSelective(TSysUserExt record);

    List<TSysUserExt> selectByExampleWithRowbounds(TSysUserExtExample example, RowBounds rowBounds);

    List<TSysUserExt> selectByExample(TSysUserExtExample example);

    TSysUserExt selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") TSysUserExt record, @Param("example") TSysUserExtExample example);

    int updateByExample(@Param("record") TSysUserExt record, @Param("example") TSysUserExtExample example);

    int updateByPrimaryKeySelective(TSysUserExt record);

    int updateByPrimaryKey(TSysUserExt record);
}