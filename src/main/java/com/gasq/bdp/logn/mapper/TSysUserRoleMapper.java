package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysUserRole;
import com.gasq.bdp.logn.model.TSysUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysUserRoleMapper {
    long countByExample(TSysUserRoleExample example);

    int deleteByExample(TSysUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TSysUserRole record);

    int insertSelective(TSysUserRole record);

    List<TSysUserRole> selectByExampleWithRowbounds(TSysUserRoleExample example, RowBounds rowBounds);

    List<TSysUserRole> selectByExample(TSysUserRoleExample example);

    TSysUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TSysUserRole record, @Param("example") TSysUserRoleExample example);

    int updateByExample(@Param("record") TSysUserRole record, @Param("example") TSysUserRoleExample example);

    int updateByPrimaryKeySelective(TSysUserRole record);

    int updateByPrimaryKey(TSysUserRole record);
}