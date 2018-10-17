/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.mapper.TSysUserRoleMapper;
import com.gasq.bdp.logn.model.TSysUserRole;
import com.gasq.bdp.logn.model.TSysUserRoleExample;
import com.gasq.bdp.logn.model.TSysUserRoleExample.Criteria;
import com.gasq.bdp.logn.service.TSysUserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年10月31日上午11:33:35
 * @remark 
 */
@Service
public class TSysUserRoleServiceImpl implements TSysUserRoleService {
	@Autowired TSysUserRoleMapper mapper;

	@Override
	public boolean delete(int id) {
		return mapper.deleteByPrimaryKey((long)id)>0?true:false;
	}

	@Override
	public int add(TSysUserRole record) {
		return mapper.insertSelective(record);
	}

	@Override
	public List<TSysUserRole> selectByExample(TSysUserRoleExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public TSysUserRole selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey((long)id);
	}

	@Override
	public Map<String, Object> queryPagingList(TSysUserRole bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysUserRoleExample example = new TSysUserRoleExample();
		Criteria c = example.createCriteria();
		if(bean.getRoleId()!=null) {
			c.andRoleIdEqualTo(bean.getRoleId());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		example.setOrderByClause(" createTime desc ");
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<TSysUserRole> listmaps = mapper.selectByExample(example);
		PageInfo<TSysUserRole> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysUserRole bean) {
		if(bean.getId()!=null) {
			mapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			mapper.insertSelective(bean);
			return true;
		}
	}

}
