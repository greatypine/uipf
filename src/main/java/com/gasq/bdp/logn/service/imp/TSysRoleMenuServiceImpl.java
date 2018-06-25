package com.gasq.bdp.logn.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.mapper.TSysMenuMapper;
import com.gasq.bdp.logn.mapper.TSysRoleMenuMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysRoleMenuExample;
import com.gasq.bdp.logn.model.TSysRoleMenuKey;
import com.gasq.bdp.logn.service.TSysRoleMenuService;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月15日下午3:03:23
 * @remark 
 */
@Service
public class TSysRoleMenuServiceImpl implements TSysRoleMenuService{
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired TSysRoleMenuMapper roleMenuMapper;
	@Autowired TSysMenuMapper menuMapper;

	@SuppressWarnings({ "rawtypes"})
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Boolean saveOrUpdate(Integer roleid, List<Map> menus)  {
		try {
			if(menus.size()>0) {
				boolean f = true;
				for (Map map : menus) {
					Integer id = Integer.parseInt(map.get("id").toString());
					if(f) {
						TSysRoleMenuExample example = new TSysRoleMenuExample();
						example.createCriteria().andRoleidEqualTo(roleid).andCompanyidEqualTo(SystemUserInfo.getSystemUser().getCompany().getId());
						roleMenuMapper.deleteByExample(example);
						f = false;
					}
					TSysRoleMenuKey record = new TSysRoleMenuKey();
					record.setMenuid(id);
					record.setRoleid(roleid);
					record.setCompanyid(SystemUserInfo.getSystemUser().getCompany().getId());
					roleMenuMapper.insert(record);
				}
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;
	}
	
}
