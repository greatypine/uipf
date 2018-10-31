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

import com.gasq.bdp.logn.mapper.TCustomerProjectLogMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCustomerProjectLog;
import com.gasq.bdp.logn.model.TCustomerProjectLogExample;
import com.gasq.bdp.logn.model.TCustomerProjectLogExample.Criteria;
import com.gasq.bdp.logn.service.TCustomerProjectLogService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TCustomerProjectLogServiceImpl implements TCustomerProjectLogService {
	@Autowired TCustomerProjectLogMapper customerProjectLogMapper;

	@Override
	public long countByExample(TCustomerProjectLogExample example) {
		return customerProjectLogMapper.countByExample(example);
	}
	
	public long countByExample(TCustomerProjectLog bean) {
		TCustomerProjectLogExample example = new TCustomerProjectLogExample();
		Criteria c = example.createCriteria();
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		return customerProjectLogMapper.countByExample(example);
	}

	@Override
	public boolean delete(int id) {
		return customerProjectLogMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public List<TCustomerProjectLog> selectByExample(TCustomerProjectLog bean) {
		TCustomerProjectLogExample example = new TCustomerProjectLogExample();
		Criteria c = example.createCriteria();
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		example.setOrderByClause(" id asc ");
		return customerProjectLogMapper.selectByExample(example);
	}

	@Override
	public TCustomerProjectLog selectByPrimaryKey(Integer id) {
		return customerProjectLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TCustomerProjectLog bean) {
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
		List<Map<String, Object>> listmaps = customerProjectLogMapper.queryPagingList(params);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		return result;
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(TCustomerProjectLog bean) {
		bean.setCreateTime(DateUtil.getSysCurrentDate());
		bean.setCreateUser(SystemUserInfo.getSystemUser().getUser().getUsername());
		customerProjectLogMapper.insertSelective(bean);
		return true;
	}

}
