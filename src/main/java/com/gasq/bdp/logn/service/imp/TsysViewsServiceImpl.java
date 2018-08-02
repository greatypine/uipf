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

import com.gasq.bdp.logn.mapper.TSysBasecodeMapper;
import com.gasq.bdp.logn.mapper.TSysViewsMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysBasecode;
import com.gasq.bdp.logn.model.TSysBasecodeExample;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TSysViews;
import com.gasq.bdp.logn.service.TsysViewsService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

/**
 * 
 * @author Ju_weigang
 * @时间 2018年8月1日下午1:53:13
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述
 */
@Service
public class TsysViewsServiceImpl implements TsysViewsService {
	@Autowired TSysViewsMapper mapper;
	@Autowired TSysBasecodeMapper baseCodeMapper;

	@Override
	@Transactional
	public boolean delete(TSysViews bean) {
		if(bean.getCompanyid()==null) bean.setCompanyid(SystemUserInfo.getSystemUser().getCompany().getId());
		TSysBasecodeExample example = new TSysBasecodeExample();
		example.createCriteria().andCompanyIdEqualTo(bean.getCompanyid()).andViewNameEqualTo(bean.getCode());
		baseCodeMapper.deleteByExample(example);
		return mapper.deleteByPrimaryKey(bean.getId())>0?true:false;
	}

	@Override
	public TSysViews selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TSysViews bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		Map<String, Object> params= new  HashMap<String, Object>();
		params.put("index", start);
		params.put("pageSize", number);
		if(bean.getName()!=null) {
			params.put("name", bean.getName());
		}
		if(bean.getStatus()!=null) {
			params.put("status", bean.getStatus());
		}
		if(bean.getCompanyid()!=null) {
			params.put("companyid", bean.getCompanyid());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				params.put("companyid", SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		list = mapper.queryPagingList(params);
		if(list==null) list = new ArrayList<Map<String,Object>>(); 
		Integer count = mapper.countByBean(params);
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysViews bean) {
		TSysUser user = SystemUserInfo.getSystemUser().getUser();
		bean.setUpdateTime(DateUtil.getSysCurrentDate());
		if(bean.getCompanyid()==null) bean.setCompanyid(user.getCompanyid());
		if(bean.getId()!=null) {
			TSysBasecode record = new TSysBasecode();
			record.setViewName(bean.getCode());
			TSysBasecodeExample example = new TSysBasecodeExample();
			example.createCriteria().andCompanyIdEqualTo(bean.getCompanyid()).andTypeEqualTo(bean.getType());
			baseCodeMapper.updateByExampleSelective(record, example);
			bean.setUpdateUser(user.getNickname());
			mapper.updateByPrimaryKeySelective(bean);
		}else {
			Integer type = baseCodeMapper.getNextType(bean.getCompanyid());
			bean.setType(type);
			bean.setCreateUser(user.getNickname());
			bean.setCreateTime(DateUtil.getSysCurrentDate());
			mapper.insertSelective(bean);
		}
		return true;
	}

}
