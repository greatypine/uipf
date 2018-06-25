/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.mapper.TSysRolePermissionMapper;
import com.gasq.bdp.logn.model.TSysRolePermission;
import com.gasq.bdp.logn.model.TSysRolePermissionExample;
import com.gasq.bdp.logn.model.TSysRolePermissionExample.Criteria;
import com.gasq.bdp.logn.service.TSysRolePermissionService;
import com.gasq.bdp.logn.utils.CommonUtils;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年10月31日上午11:33:35
 * @remark 
 */
@Service
public class TSysRolePermissionServiceImpl implements TSysRolePermissionService {
	@Autowired TSysRolePermissionMapper mapper;

	@Override
	public boolean delete(int id) {
		return mapper.deleteByPrimaryKey((long)id)>0?true:false;
	}

	@Override
	public int add(TSysRolePermission record) {
		return mapper.insertSelective(record);
	}

	@Override
	public TSysRolePermission selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey((long)id);
	}

	@Override
	public Map<String, Object> queryPagingList(TSysRolePermission bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysRolePermissionExample example = new TSysRolePermissionExample();
		Criteria c = example.createCriteria();
		if(bean.getPermissionId()!=null) {
			c.andPermissionIdEqualTo(bean.getPermissionId());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		if(bean.getRoleId()!=null) {
			c.andRoleIdEqualTo(bean.getRoleId());
		}
		example.setOrderByClause(" createTime desc ");
		int count = (int) mapper.countByExample(example);
		List<TSysRolePermission> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		RowBounds rowBounds = new RowBounds(start, bean.getRows());
		if(count>0) {
			list = mapper.selectByExampleWithRowbounds(example, rowBounds);
		}else {
			list = new ArrayList<TSysRolePermission>(); 
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysRolePermission bean) {
		if(bean.getId()!=null) {
			mapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			mapper.insertSelective(bean);
			return true;
		}
	}

	@Override
	public List<TSysRolePermission> selectByExample(TSysRolePermission bean) {
		TSysRolePermissionExample example = new TSysRolePermissionExample();
		Criteria c = example.createCriteria();
		if(bean.getPermissionId()!=null) {
			c.andPermissionIdEqualTo(bean.getPermissionId());
		}
		if(bean.getRoleId()!=null) {
			c.andRoleIdEqualTo(bean.getRoleId());
		}
		return mapper.selectByExample(example);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryMapBeanList(TSysRolePermission bean) {
		Map<String,Object> json2Map = CommonUtils.json2Map(CommonUtils.bean2Json(bean));
		return mapper.queryMapBeanList(json2Map);
	}

}
