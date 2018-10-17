/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.mapper.TEquipmentMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TEquipment;
import com.gasq.bdp.logn.model.TEquipmentExample;
import com.gasq.bdp.logn.service.TEquipmentService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年10月31日上午11:33:35
 * @remark 
 */
@Service
public class TEquipmentServiceImpl implements TEquipmentService {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired TEquipmentMapper mapper;

	@Override
	public boolean delete(int id) {
		return mapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public int add(TEquipment record) {
		return mapper.insertSelective(record);
	}

	@Override
	public List<TEquipment> selectByExample(TEquipmentExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public TEquipment selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TEquipment bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		Map<String, Object> params= new  HashMap<String, Object>();
		if(bean.getName()!=null) {
			params.put("name", bean.getName());
		}
		if(bean.getStatus()!=null) {
			params.put("status", bean.getStatus());
		}
		if(bean.getType()!=null) {
			params.put("type", bean.getType());
		}
		if(bean.getCompanyId()!=null) {
			params.put("companyid", bean.getCompanyId());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				params.put("companyid", SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Map<String, Object>> listmaps = mapper.queryPagingList(params);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		return result;
	}

	@Override
	public boolean saveOrUpdate(TEquipment bean) {
		bean.setUpdateTime(DateUtil.getSysCurrentDate());
		bean.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
		if(bean.getId()!=null) {
			bean.setUpdateUser(SystemUserInfo.getSystemUser().getUser().getUsername());
			mapper.updateByPrimaryKeySelective(bean);
		}else {
			bean.setCreateTime(DateUtil.getSysCurrentDate());
			bean.setCreateUser(SystemUserInfo.getSystemUser().getUser().getUsername());
			mapper.insertSelective(bean);
		}
		return true;
	}

	@Override
	public Map<String, Object> queryAmountSum(TEquipment bean) {
		Map<String, Object> params = new  HashMap<String, Object>();
		try {
			if(bean.getName()!=null) {
				params.put("name", bean.getName());
			}
			if(bean.getStatus()!=null) {
				params.put("status", bean.getStatus());
			}
			if(bean.getType()!=null) {
				params.put("type", bean.getType());
			}
			if(bean.getCompanyId()!=null) {
				params.put("companyid", bean.getCompanyId());
			}else {
				if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
					params.put("companyid", SystemUserInfo.getSystemUser().getCompany().getId());
				}
			}
			params = mapper.queryAmountSum(params);
			if(params==null) {
				params = new  HashMap<String, Object>();
				params.put("total_amount", 0);
				params.put("actual_total_amount", 0);
			}
		} catch (Exception e) {
			params.put("total_amount", 0);
			params.put("actual_total_amount", 0);
			logger.error("查询统计设备总价值出错！"+e.getMessage(), e);
		}
		return params;
	}
}
