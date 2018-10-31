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
import com.gasq.bdp.logn.mapper.TTherapistTreatmentTimeQueryMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TTherapistTreatmentTimeQuery;
import com.gasq.bdp.logn.model.TTherapistTreatmentTimeQueryExample;
import com.gasq.bdp.logn.model.TTherapistTreatmentTimeQueryExample.Criteria;
import com.gasq.bdp.logn.model.WorkForceParams;
import com.gasq.bdp.logn.service.TherapistTreatmentTimeQueryService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Ju_weigang
 * @时间 2018年7月30日下午2:14:00
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述 
 */
@Service
public class TherapistTreatmentTimeQueryServiceImpl implements TherapistTreatmentTimeQueryService {

	@Autowired TTherapistTreatmentTimeQueryMapper mapper;
	@Autowired TSysUserMapper userMapper;

	@Override
	public boolean delete(TTherapistTreatmentTimeQuery bean) {
		TTherapistTreatmentTimeQueryExample example = new TTherapistTreatmentTimeQueryExample();
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
	public boolean deleteByExample(TTherapistTreatmentTimeQuery bean) {
		TTherapistTreatmentTimeQueryExample example = new TTherapistTreatmentTimeQueryExample();
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
	public Map<String, Object> queryPagingList(TTherapistTreatmentTimeQuery bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		Map<String, Object> params= new  HashMap<String, Object>();
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
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Map<String, Object>> listmaps = mapper.queryPagingList(params);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		return result;
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(List<TTherapistTreatmentTimeQuery> bean) {
		TSysUser user = SystemUserInfo.getSystemUser().getUser();
		for (TTherapistTreatmentTimeQuery TTherapistTreatmentTimeQuery : bean) {
			if(TTherapistTreatmentTimeQuery.getId()!=null) {
				TTherapistTreatmentTimeQuery.setUpdateTime(DateUtil.getSysCurrentDate());
				TTherapistTreatmentTimeQuery.setUpdateUser(user.getNickname());
				mapper.updateByPrimaryKeySelective(TTherapistTreatmentTimeQuery);
			}else {
				TTherapistTreatmentTimeQuery.setCreateTime(DateUtil.getSysCurrentDate());
				TTherapistTreatmentTimeQuery.setCreateUser(user.getNickname());
				mapper.insertSelective(TTherapistTreatmentTimeQuery);
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
		month = (Integer.parseInt(month)<10)?"0"+Integer.parseInt(month):month;
		String cycle = bean.getYear()+month;
		TTherapistTreatmentTimeQueryExample example = new TTherapistTreatmentTimeQueryExample();
		example.createCriteria().andCycleEqualTo(cycle).andCompanyidEqualTo(bean.getCompanyid());
		long ct = mapper.countByExample(example);
		if(ct>0) {
			result.put("status", false);
			result.put("mess", "版本已经存在！可查询后直接编辑！");
			return result;
		}
		List<TTherapistTreatmentTimeQuery> beans = new ArrayList<TTherapistTreatmentTimeQuery>();
		if(companyid==null) companyid = user.getCompanyid();
		List<Integer> userids = bean.getUserids();
		for (Integer id : userids) {
			TTherapistTreatmentTimeQuery wfbean = new TTherapistTreatmentTimeQuery();
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
}
