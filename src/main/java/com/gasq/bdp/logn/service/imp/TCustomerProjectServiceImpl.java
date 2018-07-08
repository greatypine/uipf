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
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.mapper.TCustomerPorjectMapper;
import com.gasq.bdp.logn.mapper.TCustomerProjectLogMapper;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCustomerPorject;
import com.gasq.bdp.logn.model.TCustomerPorjectExample;
import com.gasq.bdp.logn.model.TCustomerProjectLog;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.service.TCustomerProjectService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author Ju_weigang
 * @时间 2018年7月7日下午9:57:25
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述 
 */
@Service
public class TCustomerProjectServiceImpl implements TCustomerProjectService{
	@Autowired TCustomerPorjectMapper customerProjectMapper;
	@Autowired TCustomerProjectLogMapper customerProjectLogMapper;
	
	
	@Override
	public boolean delete(Integer id) {
		return customerProjectMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public List<TCustomerPorject> selectByExample(TCustomerPorjectExample example) {
		return customerProjectMapper.selectByExample(example);
	}

	@Override
	public Map<String, Object> queryPagingList(TCustomerPorject bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		Map<String, Object> params= new  HashMap<String, Object>();
		params.put("index", start);
		params.put("pageSize", number);
		if(bean.getVipId()!=null) {
			params.put("vip_id", bean.getVipId());
		}
		list = customerProjectMapper.queryPagingList(params);
		if(list==null) list = new ArrayList<Map<String,Object>>(); 
		Integer count = customerProjectMapper.countByBean(params);
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveOrUpdate(TCustomerPorject bean) throws WorkFlowStateException {
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			if(bean.getId()!=null) {
				bean.setUpdateTime(DateUtil.getSysCurrentDate());
				bean.setUpdateUser(user.getUsername());
				customerProjectMapper.updateByPrimaryKeySelective(bean);
				customerProjectLogMapper.insertSelective(new TCustomerProjectLog(bean.getId(),InitProperties.CUSTOMER_PROJECT_OPTION_TYPE_SUBTRACT,user.getNickname(),DateUtil.getSysCurrentDate()));
			}else {
				bean.setCreateTime(DateUtil.getSysCurrentDate());
				bean.setCreateUser(user.getUsername());
				customerProjectMapper.insertSelective(bean);
				customerProjectLogMapper.insertSelective(new TCustomerProjectLog(bean.getId(),InitProperties.CUSTOMER_PROJECT_OPTION_TYPE_ADD,user.getNickname(),DateUtil.getSysCurrentDate()));
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;
	}

	@Override
	public int deleteByExample(TCustomerPorjectExample example) {
		return customerProjectMapper.deleteByExample(example);
	}

}
