package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.model.TSysMenu;
import com.gasq.bdp.logn.model.TSysMenuExample;
import java.util.List;
import java.util.Map;

public interface TSysMenuService {
	long countByExample(TSysMenuExample example);

	int deleteByExample(TSysMenuExample example);

	int insert(TSysMenu record);

	int insertSelective(TSysMenu record);

	List<TSysMenu> selectByExample(TSysMenuExample example);

	int updateByExampleSelective(TSysMenu record, TSysMenuExample example);

	int updateByExampleWithBLOBs(TSysMenu record, TSysMenuExample example);

	int updateByExample(TSysMenu record, TSysMenuExample example);

	List<TSysMenu> selectMenuBeanByRole(Integer pid);

	Map<String, Object> queryPagingList(TSysMenu bean);

	boolean saveOrUpdate(TSysMenu bean) throws WorkFlowStateException;

	boolean delete(Integer id) throws WorkFlowStateException;

	List<Map<String, Object>> selectAllMenusByRole(Integer pid, Integer roleid);

	List<Map<String, Object>> selectAllMenusTreeByRole(Integer roleid);
	
}