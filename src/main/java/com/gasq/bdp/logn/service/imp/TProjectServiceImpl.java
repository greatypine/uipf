/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月13日上午10:14:37
 * @remark 
 */
@Service
public class TProjectServiceImpl implements TSysProjectService {
	protected Logger logger = Logger.getLogger(this.getClass());
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
		if(bean.getQ()!=null && !"".equals(bean.getQ())) {
			if(!bean.getQ().equals(" ")) {
				c.andProjectNameLike("%"+bean.getQ()+"%");
				Criteria c1 = example.createCriteria();
				c1.andSpellLike("%"+bean.getQ()+"%");
				example.or(c1);
			}
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
		Map<String, Object> params= new  HashMap<String, Object>();
		if(bean.getProjectName()!=null) {
			params.put("projectname", bean.getProjectName());
		}
		if(bean.getStatus()!=null) {
			params.put("status", bean.getStatus());
		}
		if(bean.getProjectModel()!=null) {
			params.put("projectModel", bean.getProjectModel());
		}
		if(bean.getProjectType()!=null) {
			params.put("projectType", bean.getProjectType());
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
		if(bean.getCompanyId()!=null) {
			params.put("companyid", bean.getCompanyId());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.H_OPTION)) {
				params.put("companyid", SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Map<String, Object>> listmaps = projectMapper.queryPagingList(params);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		return result;
	}

	@Override
	public boolean saveOrUpdate(TProject bean) {
		bean.setUpdatetime(DateUtil.getSysCurrentDate());
		if(bean.getCompanyId()==null) bean.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
		if(bean.getId()!=null) {
			bean.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			projectMapper.updateByPrimaryKeySelective(bean);
		}else {
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			bean.setCreateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			projectMapper.insertSelective(bean);
		}
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】项目插入或更新完成！");
		return true;
	}

	@Override
	public List<TProject> queryProjectList(Integer companyId, Integer index, Integer size) {
		TProjectExample example = new TProjectExample();
		Criteria c = example.createCriteria();
		if(companyId!=null) {
			c.andCompanyIdEqualTo(companyId);
		}
		c.andStatusEqualTo(true);
		int start = 0;
		int intPage = ( index==0) ? 1 : index;
		int number = (size==0) ? 10 : size;
		start = (intPage - 1) * number;
		RowBounds rowBounds = new RowBounds(start, size);
		return projectMapper.selectByExampleWithRowbounds(example, rowBounds);
	}

}
