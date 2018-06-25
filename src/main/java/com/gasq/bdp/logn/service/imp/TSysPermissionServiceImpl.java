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
import com.gasq.bdp.logn.mapper.TSysPermissionMapper;
import com.gasq.bdp.logn.model.TSysPermission;
import com.gasq.bdp.logn.model.TSysPermissionExample;
import com.gasq.bdp.logn.model.TSysPermissionExample.Criteria;
import com.gasq.bdp.logn.service.TSysPermissionService;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年10月31日上午11:33:35
 * @remark 
 */
@Service
public class TSysPermissionServiceImpl implements TSysPermissionService {
	@Autowired TSysPermissionMapper mapper;

	@Override
	public boolean delete(int id) {
		return mapper.deleteByPrimaryKey((long)id)>0?true:false;
	}

	@Override
	public int add(TSysPermission record) {
		return mapper.insertSelective(record);
	}

	@Override
	public List<TSysPermission> selectByExample(TSysPermission bean) {
		TSysPermissionExample example = new TSysPermissionExample();
		Criteria c = example.createCriteria();
		if(bean.getDescription()!=null && !"".equals(bean.getDescription())) {
			c.andDescriptionLike("%"+bean.getDescription()+"%");
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		if(bean.getPermissionName()!=null && !"".equals(bean.getPermissionName())) {
			c.andPermissionNameEqualTo(bean.getPermissionName());
		}
		if(bean.getPermissionSign()!=null && !"".equals(bean.getPermissionSign())) {
			c.andPermissionSignEqualTo(bean.getPermissionSign());
		}
		return mapper.selectByExample(example);
	}

	@Override
	public TSysPermission selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey((long)id);
	}

	@Override
	public Map<String, Object> queryPagingList(TSysPermission bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysPermissionExample example = new TSysPermissionExample();
		Criteria c = example.createCriteria();
		if(bean.getDescription()!=null && !"".equals(bean.getDescription())) {
			c.andDescriptionLike("%"+bean.getDescription()+"%");
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		if(bean.getPermissionName()!=null && !"".equals(bean.getPermissionName())) {
			c.andPermissionNameLike("%"+bean.getPermissionName()+"%");
		}
		if(bean.getPermissionSign()!=null && !"".equals(bean.getPermissionSign())) {
			c.andPermissionSignEqualTo(bean.getPermissionSign());
		}
		example.setOrderByClause(" id asc ");
		int count = (int) mapper.countByExample(example);
		List<TSysPermission> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		RowBounds rowBounds = new RowBounds(start, bean.getRows());
		if(count>0) {
			list = mapper.selectByExampleWithRowbounds(example, rowBounds);
		}else {
			list = new ArrayList<TSysPermission>(); 
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysPermission bean) {
		if(bean.getId()!=null) {
			mapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			mapper.insertSelective(bean);
			return true;
		}
	}
	@Override
    public List<TSysPermission> selectPermissionsByRoleId(Long roleId) {
        return mapper.selectPermissionsByRoleId(roleId);
    }

	@Override
	public List<Map<String, Object>> queryMapBeanList(TSysPermission bean) {
		return null;
	}
}
