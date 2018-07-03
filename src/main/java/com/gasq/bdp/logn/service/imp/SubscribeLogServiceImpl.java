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
import com.gasq.bdp.logn.mapper.TCustomerSubscribeLogMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCustomerSubscribeLog;
import com.gasq.bdp.logn.model.TCustomerSubscribeLogExample;
import com.gasq.bdp.logn.model.TCustomerSubscribeLogExample.Criteria;
import com.gasq.bdp.logn.service.SubscribeLogService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

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
		List<Map<String, Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		Map<String, Object> params= new  HashMap<String, Object>();
		params.put("index", start);
		params.put("pageSize", number);
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
		list = subscribelogMapper.queryPagingList(params);
		if(list==null) list = new ArrayList<Map<String,Object>>(); 
		result.put("rows",list);
		result.put("total",countByExample(bean));
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
