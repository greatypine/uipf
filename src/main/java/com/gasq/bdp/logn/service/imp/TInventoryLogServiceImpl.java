/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gasq.bdp.logn.mapper.TInventoryLogMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TInventoryLog;
import com.gasq.bdp.logn.model.TInventoryLogExample;
import com.gasq.bdp.logn.model.TInventoryLogExample.Criteria;
import com.gasq.bdp.logn.service.TInventoryLogService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TInventoryLogServiceImpl implements TInventoryLogService {
	@Autowired TInventoryLogMapper TInventoryLogMapper;

	@Override
	public long countByExample(TInventoryLogExample example) {
		return TInventoryLogMapper.countByExample(example);
	}
	
	public long countByExample(TInventoryLog bean) {
		TInventoryLogExample example = new TInventoryLogExample();
		Criteria c = example.createCriteria();
		if(bean.getCompanyid()!=null) {
			c.andCompanyidEqualTo(bean.getCompanyid());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		return TInventoryLogMapper.countByExample(example);
	}

	@Override
	public boolean delete(int id) {
		return TInventoryLogMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public List<TInventoryLog> selectByExample(TInventoryLog bean) {
		TInventoryLogExample example = new TInventoryLogExample();
		Criteria c = example.createCriteria();
		if(bean.getCompanyid()!=null) {
			c.andCompanyidEqualTo(bean.getCompanyid());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		example.setOrderByClause(" id asc ");
		return TInventoryLogMapper.selectByExample(example);
	}

	@Override
	public TInventoryLog selectByPrimaryKey(Integer id) {
		return TInventoryLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TInventoryLog bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		Map<String, Object> params= new  HashMap<String, Object>();
		if(bean.getCompanyid()!=null) {
			params.put("companyid", bean.getCompanyid());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				params.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		if(bean.getCreateTime()!=null) {
			params.put("createTime", bean.getCreateTime());
		}
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Map<String, Object>> listmaps = TInventoryLogMapper.queryPagingList(params);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		return result;
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(TInventoryLog bean) {
		bean.setCreateTime(DateUtil.getSysCurrentDate());
		bean.setCreateUser(SystemUserInfo.getSystemUser().getUser().getUsername());
		TInventoryLogMapper.insertSelective(bean);
		return true;
	}

}
