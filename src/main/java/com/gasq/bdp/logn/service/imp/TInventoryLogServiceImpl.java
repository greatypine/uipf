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

import com.gasq.bdp.logn.mapper.TInventoryLogMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TInventoryLog;
import com.gasq.bdp.logn.model.TInventoryLogExample;
import com.gasq.bdp.logn.model.TInventoryLogExample.Criteria;
import com.gasq.bdp.logn.service.TInventoryLogService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

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
		List<Map<String, Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
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
		params.put("index", start);
		params.put("pageSize", number);
		list = TInventoryLogMapper.queryPagingList(params);
		if(list==null) list = new ArrayList<Map<String,Object>>(); 
		result.put("rows",list);
		result.put("total",countByExample(bean));
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
