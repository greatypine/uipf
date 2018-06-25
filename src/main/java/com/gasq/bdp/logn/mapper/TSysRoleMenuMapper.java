package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysRoleMenuExample;
import com.gasq.bdp.logn.model.TSysRoleMenuKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysRoleMenuMapper {
    long countByExample(TSysRoleMenuExample example);

    int deleteByExample(TSysRoleMenuExample example);

    int deleteByPrimaryKey(TSysRoleMenuKey key);

    int insert(TSysRoleMenuKey record);

    int insertSelective(TSysRoleMenuKey record);

    List<TSysRoleMenuKey> selectByExampleWithRowbounds(TSysRoleMenuExample example, RowBounds rowBounds);

    List<TSysRoleMenuKey> selectByExample(TSysRoleMenuExample example);

    int updateByExampleSelective(@Param("record") TSysRoleMenuKey record, @Param("example") TSysRoleMenuExample example);

    int updateByExample(@Param("record") TSysRoleMenuKey record, @Param("example") TSysRoleMenuExample example);
}