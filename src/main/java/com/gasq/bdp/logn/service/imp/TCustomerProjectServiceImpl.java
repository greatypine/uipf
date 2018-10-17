/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.Date;
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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
		Map<String, Object> params= new  HashMap<String, Object>();
		if(bean.getVipId()!=null) {
			params.put("vip_id", bean.getVipId());
		}
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Map<String, Object>> listmaps = customerProjectMapper.queryPagingList(params);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
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

	@Override
	@Transactional
	public Map<String, Object> swipingCardCommit(Integer id) {
		Map<String, Object> result= new  HashMap<String, Object>();
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			TCustomerPorject customerPorject = customerProjectMapper.selectByPrimaryKey(id);
			if(customerPorject!=null) {
				Integer type = customerPorject.getProjectType();
				if(type==1) {//次数卡
					if(customerPorject.getProjectNums()<=0) {
						result.put("status", false);
						result.put("mess", "套餐次数已经用完！");
					}else {
						result.put("status", true);
						customerPorject.setUpdateTime(DateUtil.getSysCurrentDate());
						customerPorject.setUpdateUser(user.getUsername());
						customerPorject.setProjectNums(customerPorject.getProjectNums()-1);
						customerProjectMapper.updateByPrimaryKeySelective(customerPorject);
						customerProjectLogMapper.insertSelective(new TCustomerProjectLog(customerPorject.getId(),InitProperties.CUSTOMER_PROJECT_OPTION_TYPE_SUBTRACT,user.getNickname(),DateUtil.getSysCurrentDate()));
					}
				}else {//时间卡
					Date deadline = customerPorject.getDeadline();
					if(deadline.after(DateUtil.getSysCurrentDate())){//结束时间小于当前时间 -- 可用
						result.put("status", true);
						customerPorject.setUpdateTime(DateUtil.getSysCurrentDate());
						customerPorject.setUpdateUser(user.getUsername());
						customerProjectMapper.updateByPrimaryKeySelective(customerPorject);
						customerProjectLogMapper.insertSelective(new TCustomerProjectLog(customerPorject.getId(),InitProperties.CUSTOMER_PROJECT_OPTION_TYPE_SUBTRACT,user.getNickname(),DateUtil.getSysCurrentDate()));
					}else {
						result.put("status", false);
						result.put("mess", "套餐有效期已过！");
					}
				}
			}else {
				result.put("status", false);
				result.put("mess", "会员用户信息获取失败！");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	@Override
	public Map<String, Object> queryCustomerProjectLogs(TCustomerProjectLog bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		Map<String, Object> params= new  HashMap<String, Object>();
		if(bean.getCpId()!=null) {
			params.put("cpid", bean.getCpId());
		}
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Map<String, Object>> listmaps = customerProjectLogMapper.queryPagingList(params);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		return result;
	}

}
