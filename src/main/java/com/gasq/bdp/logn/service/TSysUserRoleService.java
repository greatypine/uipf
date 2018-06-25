package com.gasq.bdp.logn.service;
import com.gasq.bdp.logn.model.TSysUserRole;
import com.gasq.bdp.logn.model.TSysUserRoleExample;

import java.util.List;
import java.util.Map;

public interface TSysUserRoleService {

    int add(TSysUserRole record);

    List<TSysUserRole> selectByExample(TSysUserRoleExample example);

    TSysUserRole selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TSysUserRole bean);

	boolean saveOrUpdate(TSysUserRole bean);

	boolean delete(int id);

}