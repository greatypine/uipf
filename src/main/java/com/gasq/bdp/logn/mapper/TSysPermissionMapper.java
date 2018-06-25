package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysPermission;
import com.gasq.bdp.logn.model.TSysPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysPermissionMapper {
    long countByExample(TSysPermissionExample example);

    int deleteByExample(TSysPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TSysPermission record);

    int insertSelective(TSysPermission record);

    List<TSysPermission> selectByExampleWithRowbounds(TSysPermissionExample example, RowBounds rowBounds);

    List<TSysPermission> selectByExample(TSysPermissionExample example);

    TSysPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TSysPermission record, @Param("example") TSysPermissionExample example);

    int updateByExample(@Param("record") TSysPermission record, @Param("example") TSysPermissionExample example);

    int updateByPrimaryKeySelective(TSysPermission record);

    int updateByPrimaryKey(TSysPermission record);

	List<TSysPermission> selectPermissionsByRoleId(Long roleId);
}