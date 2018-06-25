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

import com.gasq.bdp.logn.mapper.TSysSqlUpdateColumnsMapper;
import com.gasq.bdp.logn.model.SystemUser;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysSqlUpdateColumns;
import com.gasq.bdp.logn.model.TSysSqlUpdateColumnsExample;
import com.gasq.bdp.logn.model.TSysSqlUpdateColumnsExample.Criteria;
import com.gasq.bdp.logn.service.UpdateColumnsService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年11月17日下午4:52:37
 * @remark 
 */
@Service
public class UpdateColumnsServiceImpl implements UpdateColumnsService {
	@Autowired
	TSysSqlUpdateColumnsMapper updateColumnsMapper;
	@Override
	public Map<String, Object> queryPagingList(TSysSqlUpdateColumns bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysSqlUpdateColumnsExample example = new TSysSqlUpdateColumnsExample();
		Criteria c = example.createCriteria();
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getCode()!=null && !"".equals(bean.getCode())) {
			c.andCodeLike("%"+bean.getCode()+"%");
		}
		if(bean.getInsertUpdateJobId()!=null) {
			c.andInsertUpdateJobIdEqualTo(bean.getInsertUpdateJobId());
		}
		if(bean.getCreatetime()!=null) {
			c.andCreatetimeGreaterThanOrEqualTo(bean.getCreatetime());
		}
		example.setOrderByClause(" createTime desc ");
		int count = (int) updateColumnsMapper.countByExample(example);
		List<TSysSqlUpdateColumns> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		RowBounds rowBounds = new RowBounds(start, bean.getRows());
		if(count>0) {
			list = updateColumnsMapper.selectByExampleWithRowbounds(example, rowBounds);
		}else {
			list = new ArrayList<TSysSqlUpdateColumns>();
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysSqlUpdateColumns bean) {
		if(bean.getId()!=null) {
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			updateColumnsMapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			updateColumnsMapper.insertSelective(bean);
			return true;
		}
	}

	@Override
	public boolean delete(int id) {
		return updateColumnsMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public List<TSysSqlUpdateColumns> selectByExample(TSysSqlUpdateColumnsExample sqlqueryExample) {
		return updateColumnsMapper.selectByExample(sqlqueryExample);
	}

	@Override
	public long countByExample(TSysSqlUpdateColumnsExample example) {
		return updateColumnsMapper.countByExample(example);
	}

	@Override
	public List<Map<String,Object>> queryList(TSysSqlUpdateColumns bean) {
		SystemUser suser = SystemUserInfo.getSystemUser();
		String username = suser.getUser().getUsername();
		String groupname = suser.getUser().getGroupName();
		bean.setGroupname(groupname);
		List<TSysRole> roles = SystemUserInfo.getSystemUser().getRole();
		boolean flag = false;
		for (TSysRole tSysRole : roles) {
			if(tSysRole.getRoleSign().equals("sadmin") || tSysRole.getRoleSign().equals("admin")) {
				flag = true;
				break;
			}
		}
		if(!flag) {
			bean.setCreateuser(username);
		}
		return updateColumnsMapper.queryList(bean);
	}

	@Override
	public List<TSysSqlUpdateColumns> queryBeanList(TSysSqlUpdateColumns bean) {
		TSysSqlUpdateColumnsExample example = new TSysSqlUpdateColumnsExample();
		Criteria c = example.createCriteria();
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getCode()!=null && !"".equals(bean.getCode())) {
			c.andCodeLike("%"+bean.getCode()+"%");
		}
		if(bean.getInsertUpdateJobId()!=null) {
			c.andInsertUpdateJobIdEqualTo(bean.getInsertUpdateJobId());
		}
		if(bean.getCreatetime()!=null) {
			c.andCreatetimeGreaterThanOrEqualTo(bean.getCreatetime());
		}
		example.setOrderByClause(" createTime desc ");
		return updateColumnsMapper.selectByExample(example);
	}

}
