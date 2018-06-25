package com.gasq.bdp.logn.service;
import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.model.TSysPermission;

public interface TSysPermissionService {

    int add(TSysPermission record);

    List<TSysPermission> selectByExample(TSysPermission bean);

    TSysPermission selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TSysPermission bean);

	boolean saveOrUpdate(TSysPermission bean);

	boolean delete(int id);

	List<TSysPermission> selectPermissionsByRoleId(Long roleId);

	List<Map<String, Object>> queryMapBeanList(TSysPermission bean);

}