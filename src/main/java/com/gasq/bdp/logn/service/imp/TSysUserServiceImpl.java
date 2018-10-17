/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.mapper.TCompanyMapper;
import com.gasq.bdp.logn.mapper.TSysPermissionMapper;
import com.gasq.bdp.logn.mapper.TSysRoleMapper;
import com.gasq.bdp.logn.mapper.TSysUserExtMapper;
import com.gasq.bdp.logn.mapper.TSysUserMapper;
import com.gasq.bdp.logn.mapper.TSysUserRoleMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUser;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysPermission;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TSysUserExample;
import com.gasq.bdp.logn.model.TSysUserExample.Criteria;
import com.gasq.bdp.logn.model.TSysUserExt;
import com.gasq.bdp.logn.model.TSysUserExtExample;
import com.gasq.bdp.logn.model.TSysUserRole;
import com.gasq.bdp.logn.model.TSysUserRoleExample;
import com.gasq.bdp.logn.service.TSysUserService;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年10月31日上午11:33:35
 * @remark 
 */
@Service
public class TSysUserServiceImpl implements TSysUserService {
	@Autowired TSysUserMapper mapper;
	@Autowired TSysUserExtMapper uxmapper;
	@Autowired TSysPermissionMapper permissionrmapper;
	@Autowired TSysRoleMapper rolemapper;
	@Autowired TCompanyMapper companymapper;
	@Autowired TSysUserRoleMapper sysUserRoleMapper;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean delete(int id) {
		try {
			TSysUserRoleExample urx = new TSysUserRoleExample();
			urx.createCriteria().andUserIdEqualTo((long)id);
			sysUserRoleMapper.deleteByExample(urx);
			TSysUserExtExample uxexample = new TSysUserExtExample();
			uxexample.createCriteria().andUserIdEqualTo((long)id);
			uxmapper.deleteByExample(uxexample);
			return mapper.deleteByPrimaryKey((long)id)>0?true:false;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return false;
	}

	@Override
	public int add(TSysUser record) {
		return mapper.insertSelective(record);
	}

	@Override
	public List<TSysUser> selectByExample(TSysUser bean) {
		TSysUserExample example = new TSysUserExample();
		Criteria c = example.createCriteria();
//		if(bean.getCompanyid()!=null) {
//			c.andCompanyidEqualTo(bean.getCompanyid());
//		}else {
//			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
//				c.andCompanyidEqualTo(SystemUserInfo.getSystemUser().getCompany().getId());
//			}else {
//				if(bean.getUsername()!=null && !"".equals(bean.getUsername())) {
//					c.andUsernameEqualTo(bean.getUsername());
//				}
//			}
//		}
		if(bean.getUsername()!=null) {
			c.andUsernameEqualTo(bean.getUsername());
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		if(bean.getCreateTime()!=null) {
			c.andCreateTimeGreaterThanOrEqualTo(bean.getCreateTime());
		}
		return mapper.selectByExample(example);
	}

	@Override
	public TSysUser selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey((long)id);
	}

	@Override
	public Map<String, Object> queryPagingList(TSysUser bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		Map<String, Object> params= new  HashMap<String, Object>();
		if(bean.getUsername()!=null) {
			params.put("username", bean.getUsername());
		}
		if(bean.getUsername()!=null) {
			params.put("username", bean.getUsername());
		}
		if(StringUtils.isNotBlank(bean.getRoleids())) {
			params.put("roleid", bean.getRoleids());
		}
		if(bean.getCreateTime()!=null) {
			params.put("createTime", DateUtil.dateToString(bean.getCreateTime()));
		}
		if(bean.getEndTime()!=null) {
			params.put("endTime",DateUtil.dateToString(bean.getEndTime()));
		}
		if(bean.getCompanyid()!=null) {
			params.put("companyid", bean.getCompanyid());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.H_OPTION)) {
				params.put("companyid", SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Map<String, Object>> listmaps = mapper.queryPagingList(params);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer saveOrUpdate(TSysUser bean) throws WorkFlowStateException{
		try {
			bean.setUpdateTime(DateUtil.getSysCurrentDate());
			bean.setGroupName(SystemUserInfo.getSystemUser().getUser().getGroupName());
			if(StringUtils.isBlank(bean.getNickname())) bean.setNickname(bean.getUsername());
			TSysUserExample userexample = new TSysUserExample();
			userexample.createCriteria().andUsernameEqualTo(bean.getUsername());
			long countuser = mapper.countByExample(userexample);
			if(bean.getStatus()==99) bean.setIsvalid(false);
			if(bean.getId()!=null) {
				TSysUserRoleExample urx = new TSysUserRoleExample();
				urx.createCriteria().andUserIdEqualTo(bean.getId());
				sysUserRoleMapper.deleteByExample(urx);
				TSysUserExt userex = uxmapper.selectByPrimaryKey(bean.getId());
				if(userex==null) {
					TSysUserExt uxb = new TSysUserExt();
					uxb.setUserId(bean.getId());
					uxb.setPassword(bean.getPassword());
					uxmapper.insertSelective(uxb);
				}
				if(!userex.getPassword().equals(bean.getPassword())) {//密码不相等
					String pwd = bean.getPassword();
					String md5pwd = CommonUtils.change2MD5(pwd);
					bean.setPassword(md5pwd);
					userex.setUserId(bean.getId());
					userex.setPassword(pwd);
					uxmapper.updateByPrimaryKeySelective(userex);
				}else {
					String pwd = bean.getPassword();
					String md5pwd = CommonUtils.change2MD5(pwd);
					bean.setPassword(md5pwd);
				}
				mapper.updateByPrimaryKeySelective(bean);
			}else {
				if(countuser>0) return 1;
				bean.setCreateTime(DateUtil.getSysCurrentDate());
				String pwd = bean.getPassword();
				String md5pwd = CommonUtils.change2MD5(pwd);
				bean.setPassword(md5pwd);
				mapper.insertSelective(bean);
				TSysUserExt uxb = new TSysUserExt();
				uxb.setUserId(bean.getId());
				uxb.setPassword(pwd);
				uxmapper.insertSelective(uxb);
			}
			if(StringUtils.isNotBlank(bean.getRoleids())){
				String[] roleids = bean.getRoleids().split(",");
				for (String rid : roleids) {
					TSysUserRole urbean = new TSysUserRole();
					urbean.setRoleId(Long.parseLong(rid));
					urbean.setUserId(bean.getId());
					sysUserRoleMapper.insert(urbean);
				}
			}
			return 0;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return 2;
	}

	@Override
	public SystemUser queryFullUser(TSysUser user) {
		SystemUser systemUser = SystemUserInfo.getInstance();
		TSysUserExample userexample = new TSysUserExample();
		Criteria c = userexample.createCriteria();
		if(user.getUsername()!=null && !"".equals(user.getUsername())) {
			c.andUsernameEqualTo(user.getUsername());
		}
		if(user.getPassword()!=null && !"".equals(user.getPassword())) {
			c.andPasswordEqualTo(CommonUtils.change2MD5(user.getPassword()));
		}
		if(systemUser.getCompany()!=null) {
			c.andCompanyidEqualTo(systemUser.getCompany().getId());
		}
		c.andIsvalidEqualTo(true);
		List<TSysUser> users = mapper.selectByExample(userexample);
		if(users.size()>0) {
			TSysUser tSysUser = users.get(0);
			TSysUserExt userExt = uxmapper.selectByPrimaryKey(tSysUser.getId());
			systemUser.setUserext(userExt);
			systemUser.setUser(tSysUser);
			systemUser.setCompany(companymapper.selectByPrimaryKey(tSysUser.getCompanyid()));
			List<TSysRole> roles = rolemapper.selectRolesByUserId(tSysUser.getId());
			systemUser.setRole(roles);
			if(roles.size()>0) {
				Object[] rids = roles.stream().map(r->r.getId().intValue()).toArray();
				String roleids = StringUtils.join(rids,",");
				tSysUser.setRoleids(roleids);
				List<TSysPermission> permssionst = new ArrayList<TSysPermission>();
				for (TSysRole tSysRole : roles) {
					List<TSysPermission> permssions = permissionrmapper.selectPermissionsByRoleId(tSysRole.getId());
					permssionst.addAll(permssions);
				}
				systemUser.setPromissions(permssionst);
			}
		}
		SystemUserInfo.setSystemUser(systemUser);
		return systemUser;
	}

	@Override
	public boolean unlockSubmit(TSysUser bean) {
		if(StringUtils.isBlank(bean.getUsername()) || StringUtils.isBlank(bean.getPassword())) return false;
		TSysUserExample userexample = new TSysUserExample();
		Criteria c = userexample.createCriteria();
			c.andUsernameEqualTo(bean.getUsername());
			c.andPasswordEqualTo(CommonUtils.change2MD5(bean.getPassword()));
		long ct = mapper.countByExample(userexample);
		return ct>0?true:false;
	}
	@Override
	public boolean checkUserNameEable(String username) {
		if(StringUtils.isBlank(username) ) return false;
		TSysUserExample userexample = new TSysUserExample();
		Criteria c = userexample.createCriteria();
			c.andUsernameEqualTo(username);
		long ct = mapper.countByExample(userexample);
		return ct<=0?true:false;
	}

	@Override
	public TSysUser getSysUserInfo(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	@Override
	public TSysUserExt getSysUserExtInfo(Long id) {
		return uxmapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public Map<String, Object> changePassword(String oldpwd, String newpwd) {
		Map<String, Object> result= new  HashMap<String, Object>();
		try {
			SystemUser systemUser = SystemUserInfo.getSystemUser();
			TSysUserExt userext = systemUser.getUserext();
			if(!userext.getPassword().equals(oldpwd)) {
				result.put("status", false);
				result.put("mess", "旧密码不正确！请查证后再次尝试。");
				return result;
			}
			TSysUser user = systemUser.getUser();
			user.setPassword(CommonUtils.change2MD5(newpwd));
			userext.setPassword(newpwd);
			uxmapper.updateByPrimaryKeySelective(userext);
			mapper.updateByPrimaryKeySelective(user);
			result.put("status", true);
			return result;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		result.put("status", false);
		result.put("mess", "操作失败，请联系系统管理员！");
		return result;
	}
}
