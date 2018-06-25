package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

public interface TSysRoleMenuService {

	@SuppressWarnings("rawtypes")
	Boolean saveOrUpdate(Integer roleid, List<Map> menus);
}