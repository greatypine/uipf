/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.mapper.TProjectMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TProject;
import com.gasq.bdp.logn.model.TProjectExample;
import com.gasq.bdp.logn.model.TProjectExample.Criteria;
import com.gasq.bdp.logn.service.TSysProjectService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月13日上午10:14:37
 * @remark 
 */
@Service
public class TProjectServiceImpl implements TSysProjectService {
	@Autowired TProjectMapper projectMapper;

	@Override
	public long countByExample(TProjectExample example) {
		return projectMapper.countByExample(example);
	}
	
	public long countByExample(TProject bean) {
		TProjectExample example = new TProjectExample();
		Criteria c = example.createCriteria();
		if(bean.getProjectName()!=null && !"".equals(bean.getProjectName())) {
			c.andProjectNameLike("%"+bean.getProjectName()+"%");
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		if(bean.getCompanyId()!=null) {
			c.andCompanyIdEqualTo(bean.getCompanyId());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				c.andCompanyIdEqualTo(SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		if(StringUtils.isNotBlank(bean.getRemark())) {
			c.andRemarkLike("%"+bean.getRemark()+"%");
		}
		return projectMapper.countByExample(example);
	}

	@Override
	public boolean delete(int id) {
		return projectMapper.deleteByPrimaryKey((long)id)>0?true:false;
	}

	@Override
	public List<TProject> selectByExample(TProject bean) {
		TProjectExample example = new TProjectExample();
		Criteria c = example.createCriteria();
		if(bean.getProjectName()!=null && !"".equals(bean.getProjectName())) {
			c.andProjectNameLike("%"+bean.getProjectName()+"%");
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		if(bean.getCompanyId()!=null) {
			c.andCompanyIdEqualTo(bean.getCompanyId());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		if(StringUtils.isNotBlank(bean.getRemark())) {
			c.andRemarkLike("%"+bean.getRemark()+"%");
		}
		example.setOrderByClause(" id asc ");
		return projectMapper.selectByExample(example);
	}

	@Override
	public TProject selectByPrimaryKey(Long id) {
		return projectMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TProject bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		Map<String, Object> params= new  HashMap<String, Object>();
		params.put("index", start);
		params.put("pageSize", number);
		if(bean.getProjectName()!=null) {
			params.put("projectname", bean.getProjectName());
		}
		if(bean.getStatus()!=null) {
			params.put("status", bean.getStatus());
		}
		if(bean.getDiscount()!=null) {
			params.put("discount", bean.getDiscount());
		}
		if(bean.getRemark()!=null) {
			params.put("remark", bean.getRemark());
		}
		list = projectMapper.queryPagingList(params);
		if(list==null) list = new ArrayList<Map<String,Object>>(); 
		result.put("rows",list);
		result.put("total",countByExample(bean));
		return result;
	}

	@Override
	public boolean saveOrUpdate(TProject bean) {
		bean.setUpdatetime(DateUtil.getSysCurrentDate());
		bean.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
		if(bean.getId()!=null) {
			bean.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			projectMapper.updateByPrimaryKeySelective(bean);
		}else {
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			bean.setCreateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			projectMapper.insertSelective(bean);
		}
		return true;
	}

}
