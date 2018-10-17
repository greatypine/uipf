/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.mapper.TSysRoleMapper;
import com.gasq.bdp.logn.mapper.TSysRolePermissionMapper;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysRoleExample;
import com.gasq.bdp.logn.model.TSysRoleExample.Criteria;
import com.gasq.bdp.logn.model.TSysRolePermission;
import com.gasq.bdp.logn.model.TSysRolePermissionExample;
import com.gasq.bdp.logn.service.TSysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年10月31日上午11:33:35
 * @remark 
 */
@Service
@Transactional
@Repository
public class TSysRoleServiceImpl implements TSysRoleService {
	@Autowired TSysRoleMapper mapper;
	@Autowired TSysRolePermissionMapper permissionMapper;

	@Override
	public boolean delete(int id) {
		return mapper.deleteByPrimaryKey((long)id)>0?true:false;
	}

	@Override
	public int add(TSysRole record) {
		return mapper.insertSelective(record);
	}

	@Override
	public List<TSysRole> selectByExample(TSysRoleExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public TSysRole selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey((long)id);
	}

	@Override
	public Map<String, Object> queryPagingList(TSysRole bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		if(bean.getDescription()!=null && !"".equals(bean.getDescription())) {
			result.put("description", bean.getDescription());
		}
		if(bean.getRoleSign()!=null && !"".equals(bean.getRoleSign())) {
			result.put("roleSign", bean.getRoleSign());
		}
		if(bean.getId()!=null) {
			result.put("id", bean.getId());
		}
		if(bean.getRoleName()!=null && !"".equals(bean.getRoleName())) {
			result.put("roleName", bean.getRoleName());
		}
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Map<String, Object>> listmaps = mapper.queryPagingList(result);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveOrUpdate(TSysRole bean) throws Exception{
		try {
			if(bean.getId()!=null) {
				TSysRolePermissionExample example = new TSysRolePermissionExample();
				example.createCriteria().andRoleIdEqualTo(bean.getId());
				permissionMapper.deleteByExample(example);
				mapper.updateByPrimaryKeySelective(bean);
			}else {
				mapper.insertSelective(bean);
			}
			if(StringUtils.isNotBlank(bean.getPermissions())){
				for (String perm : bean.getPermissions().split(",")) {
					TSysRolePermission rolePermission = new TSysRolePermission();
					rolePermission.setRoleId(bean.getId());
					rolePermission.setPermissionId(Long.parseLong(perm));
					permissionMapper.insertSelective(rolePermission);
				}
			}
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;
		
	}

	@Override
	public List<TSysRole> selectRolesByUserId(Long userId) {
		return mapper.selectRolesByUserId(userId);
	}

	@Override
	public List<TSysRole> selectByExample(TSysRole bean) {
		TSysRoleExample example = new TSysRoleExample();
		Criteria c = example.createCriteria();
		if(bean.getDescription()!=null && !"".equals(bean.getDescription())) {
			c.andDescriptionLike("%"+bean.getDescription()+"%");
		}
		if(bean.getRoleSign()!=null && !"".equals(bean.getRoleSign())) {
			c.andRoleSignEqualTo(bean.getRoleSign());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		if(bean.getRoleName()!=null && !"".equals(bean.getRoleName())) {
			c.andRoleNameEqualTo(bean.getRoleName());
		}
		example.setOrderByClause(" id asc ");
		return mapper.selectByExample(example);
	}

	@Override
	public List<Map<String, Object>> queryMapBeanList(TSysRole bean) {
		return mapper.queryMapBeanList(bean);
	}

}
