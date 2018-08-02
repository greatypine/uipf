/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gasq.bdp.logn.mapper.TSysUserMapper;
import com.gasq.bdp.logn.mapper.TWorkforcemanagementMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TWorkforcemanagement;
import com.gasq.bdp.logn.model.TWorkforcemanagementExample;
import com.gasq.bdp.logn.model.TWorkforcemanagementExample.Criteria;
import com.gasq.bdp.logn.model.WorkForceParams;
import com.gasq.bdp.logn.service.WorkforceManagementService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

/**
 * @author Ju_weigang
 * @时间 2018年7月30日下午2:14:00
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述 
 */
@Service
public class WorkforceManagementServiceImpl implements WorkforceManagementService {

	@Autowired TWorkforcemanagementMapper mapper;
	@Autowired TSysUserMapper userMapper;

	@Override
	public boolean delete(TWorkforcemanagement bean) {
		TWorkforcemanagementExample example = new TWorkforcemanagementExample();
		Criteria c = example.createCriteria();
		if(bean.getCompanyid()!=null) {
			c.andCompanyidEqualTo(bean.getCompanyid());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				c.andCompanyidEqualTo(SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		c.andCycleEqualTo(bean.getCycle());
		return mapper.deleteByExample(example)>0?true:false;
	}
	@Override
	public boolean deleteByExample(TWorkforcemanagement bean) {
		TWorkforcemanagementExample example = new TWorkforcemanagementExample();
		Criteria c = example.createCriteria();
		if(bean.getCompanyid()!=null) {
			c.andCompanyidEqualTo(bean.getCompanyid());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				c.andCompanyidEqualTo(SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		int ct = mapper.deleteByExample(example);
		return ct>0?true:false;
	}

	@Override
	public Map<String, Object> queryPagingList(TWorkforcemanagement bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		Map<String, Object> params= new  HashMap<String, Object>();
		params.put("index", start);
		params.put("pageSize", number);
		if(bean.getCycle()!=null) {
			params.put("cycle", bean.getCycle());
		}
		if(bean.getCompanyid()!=null) {
			params.put("companyid", bean.getCompanyid());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				params.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		list = mapper.queryPagingList(params);
		if(list==null) list = new ArrayList<Map<String,Object>>(); 
		Integer count = mapper.counTWorkforcemanagementByBean(params);
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(List<TWorkforcemanagement> bean) {
		TSysUser user = SystemUserInfo.getSystemUser().getUser();
		for (TWorkforcemanagement tWorkforcemanagement : bean) {
			if(tWorkforcemanagement.getId()!=null) {
				tWorkforcemanagement.setUpdateTime(DateUtil.getSysCurrentDate());
				tWorkforcemanagement.setUpdateUser(user.getNickname());
				mapper.updateByPrimaryKeySelective(tWorkforcemanagement);
			}else {
				tWorkforcemanagement.setCreateTime(DateUtil.getSysCurrentDate());
				tWorkforcemanagement.setCreateUser(user.getNickname());
				mapper.insertSelective(tWorkforcemanagement);
			}
		}
		return true;
	}
	@Override
	public Map<String, Object> addwf(WorkForceParams bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysUser user = SystemUserInfo.getSystemUser().getUser();
		Integer companyid = bean.getCompanyid();
		if(companyid==null) companyid = user.getCompanyid();
		String month = bean.getMonth();
		month = (Integer.parseInt(month)<10)?"0"+month:month;
		String cycle = bean.getYear()+month;
		TWorkforcemanagementExample example = new TWorkforcemanagementExample();
		example.createCriteria().andCycleEqualTo(cycle).andCompanyidEqualTo(bean.getCompanyid());
		long ct = mapper.countByExample(example);
		if(ct>0) {
			result.put("status", false);
			result.put("mess", "版本已经存在！可查询后直接编辑！");
			return result;
		}
		List<TWorkforcemanagement> beans = new ArrayList<TWorkforcemanagement>();
		if(companyid==null) companyid = user.getCompanyid();
		List<Integer> userids = bean.getUserids();
		for (Integer id : userids) {
			TWorkforcemanagement wfbean = new TWorkforcemanagement();
			TSysUser usert = userMapper.selectByPrimaryKey(id.longValue());
			wfbean.setCompanyid(companyid);
			wfbean.setUsername(usert.getNickname());
			wfbean.setCreateTime(DateUtil.getSysCurrentDate());
			wfbean.setCreateUser(user.getNickname());
			wfbean.setCycle(cycle);
			beans.add(wfbean);
		}
		mapper.insertBatch(beans);
		result.put("status", true);
		return result;
	}
	@Override
	public List<Map<String, Object>> queryExportDataList(TWorkforcemanagement bean) {
		List<Map<String, Object>> list = null;
		if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
			bean.setCompanyid(SystemUserInfo.getSystemUser().getCompany().getId());
		}
		list = mapper.queryExportDataList(bean);
		return list;
	}
}
