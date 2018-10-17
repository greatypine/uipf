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

import com.gasq.bdp.logn.mapper.TCustomerSubscribeLogMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCustomerSubscribeLog;
import com.gasq.bdp.logn.model.TCustomerSubscribeLogExample;
import com.gasq.bdp.logn.model.TCustomerSubscribeLogExample.Criteria;
import com.gasq.bdp.logn.service.SubscribeLogService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SubscribeLogServiceImpl implements SubscribeLogService {
	@Autowired TCustomerSubscribeLogMapper subscribelogMapper;

	@Override
	public long countByExample(TCustomerSubscribeLogExample example) {
		return subscribelogMapper.countByExample(example);
	}
	
	public long countByExample(TCustomerSubscribeLog bean) {
		TCustomerSubscribeLogExample example = new TCustomerSubscribeLogExample();
		Criteria c = example.createCriteria();
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		return subscribelogMapper.countByExample(example);
	}

	@Override
	public boolean delete(int id) {
		return subscribelogMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public List<TCustomerSubscribeLog> selectByExample(TCustomerSubscribeLog bean) {
		TCustomerSubscribeLogExample example = new TCustomerSubscribeLogExample();
		Criteria c = example.createCriteria();
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		example.setOrderByClause(" id asc ");
		return subscribelogMapper.selectByExample(example);
	}

	@Override
	public TCustomerSubscribeLog selectByPrimaryKey(Integer id) {
		return subscribelogMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TCustomerSubscribeLog bean) {
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
		List<Map<String, Object>> listmaps = subscribelogMapper.queryPagingList(params);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		return result;
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(TCustomerSubscribeLog bean) {
		bean.setCreateTime(DateUtil.getSysCurrentDate());
		bean.setCreateUser(SystemUserInfo.getSystemUser().getUser().getUsername());
		subscribelogMapper.insertSelective(bean);
		return true;
	}

}
