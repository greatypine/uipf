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
import com.gasq.bdp.logn.mapper.TCustomerCommentMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCustomerComment;
import com.gasq.bdp.logn.model.TCustomerCommentExample;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.service.TCustomerCommentService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author Ju_weigang
 * @时间 2018年7月7日下午9:57:25
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述 
 */
@Service
public class TCustomerCommentServiceImpl implements TCustomerCommentService{
	@Autowired TCustomerCommentMapper customerCommentMapper;
	@Override
	public boolean delete(Integer id) {
		return customerCommentMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public List<TCustomerComment> selectByExample(TCustomerCommentExample example) {
		return customerCommentMapper.selectByExample(example);
	}

	@Override
	public Map<String, Object> queryPagingList(TCustomerComment bean) {
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
		list = customerCommentMapper.queryPagingList(params);
		if(list==null) list = new ArrayList<Map<String,Object>>(); 
		Integer count = customerCommentMapper.countByBean(params);
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveOrUpdate(TCustomerComment bean) throws WorkFlowStateException {
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			if(bean.getId()!=null) {
				customerCommentMapper.updateByPrimaryKeySelective(bean);
			}else {
				bean.setCreateTime(DateUtil.getSysCurrentDate());
				bean.setCreateUser(user.getNickname());
				customerCommentMapper.insertSelective(bean);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;
	}

}
