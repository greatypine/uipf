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
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.mapper.TCustomerCommentMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCustomerComment;
import com.gasq.bdp.logn.model.TCustomerCommentExample;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.service.TCustomerCommentService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
		Map<String, Object> params= new  HashMap<String, Object>();
		if(bean.getVipId()!=null) {
			params.put("vip_id", bean.getVipId());
		}
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Map<String, Object>> listmaps = customerCommentMapper.queryPagingList(params);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
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
