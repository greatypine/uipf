package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.mapper.TSysTimerJobSqlQueryMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysTimerJobSqlQuery;
import com.gasq.bdp.logn.model.TSysTimerJobSqlQueryExample;
import com.gasq.bdp.logn.model.TSysTimerJobSqlQueryExample.Criteria;
import com.gasq.bdp.logn.service.TSysTimerJobSqlQueryService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月13日上午10:14:37
 * @remark 
 */
@Service
public class TSysTimerJobSqlQueryServiceImpl implements TSysTimerJobSqlQueryService {
	@Autowired TSysTimerJobSqlQueryMapper sqlqueryMapper;

	@Override
	public Map<String, Object> queryPagingList(TSysTimerJobSqlQuery bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysTimerJobSqlQueryExample example = new TSysTimerJobSqlQueryExample();
		Criteria c = example.createCriteria();
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getRemark()!=null && !"".equals(bean.getRemark())) {
			c.andRemarkLike("%"+bean.getRemark()+"%");
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		if(bean.getCreatetime()!=null) {
			c.andCreatetimeGreaterThanOrEqualTo(bean.getCreatetime());
		}
		String username = SystemUserInfo.getSystemUser().getUser().getUsername();
		String groupname = SystemUserInfo.getSystemUser().getUser().getGroupName();
		if(!"".equals(bean.getGroupname()) && bean.getGroupname()!=null) {
			c.andGroupnameEqualTo(bean.getGroupname());
		}else {
			c.andGroupnameEqualTo(groupname);
		}
		if(bean.getCreateuser()!=null && !"".equals(bean.getCreateuser())){
			c.andCreateuserEqualTo(bean.getCreateuser());
		}else {
			List<TSysRole> roles = SystemUserInfo.getSystemUser().getRole();
			boolean flag = false;
			for (TSysRole tSysRole : roles) {
				if(tSysRole.getRoleSign().equals("sadmin") || tSysRole.getRoleSign().equals("admin")) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				c.andCreateuserEqualTo(username);
			}
		}
		example.setOrderByClause(" createTime desc ");
		int count = (int) sqlqueryMapper.countByExample(example);
		List<Map<String,Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		if(count>0) {
			Map<String, Object> params= new  HashMap<String, Object>();
			params.put("index", start);
			params.put("pageSize", number);
			params.put("createuser", username);
			params.put("groupname", groupname);
			String role = "user";
			for (TSysRole tSysRole : SystemUserInfo.getSystemUser().getRole()) {
				if(tSysRole.getRoleSign().equals("sadmin") || tSysRole.getRoleSign().equals("admin")) {
					role = "admin";
					break;
				}
			}
			params.put("role", role);
			list = sqlqueryMapper.querySqlpagingList(params);
		}else {
			list = new ArrayList<Map<String,Object>>(); 
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysTimerJobSqlQuery bean) {
		bean.setUpdatetime(DateUtil.getSysCurrentDate());
		bean.setGroupname(SystemUserInfo.getSystemUser().getUser().getGroupName());
		if(bean.getId()!=null) {
			bean.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			sqlqueryMapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			bean.setUpdatetime(DateUtil.getSysCurrentDate());
			sqlqueryMapper.insertSelective(bean);
			return true;
		}
	}

	@Override
	public boolean delete(int id) {
		return sqlqueryMapper.deleteByPrimaryKey((long)id)>0?true:false;
	}

	@Override
	public List<TSysTimerJobSqlQuery> selectByExample(TSysTimerJobSqlQueryExample sqlqueryExample) {
		return sqlqueryMapper.selectByExampleWithBLOBs(sqlqueryExample);
	}

	@Override
	public long countByExample(TSysTimerJobSqlQueryExample example) {
		return sqlqueryMapper.countByExample(example);
	}

}
