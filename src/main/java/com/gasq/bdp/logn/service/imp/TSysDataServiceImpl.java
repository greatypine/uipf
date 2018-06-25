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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.mapper.TSysDataColumnsMapper;
import com.gasq.bdp.logn.mapper.TSysDataMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysData;
import com.gasq.bdp.logn.model.TSysDataColumnsExample;
import com.gasq.bdp.logn.model.TSysDataExample;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysDataExample.Criteria;
import com.gasq.bdp.logn.service.TSysDataService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年11月13日下午7:54:59
 * @remark 
 */
@Service
public class TSysDataServiceImpl implements TSysDataService {
	@Autowired TSysDataMapper dataMapper;
	@Autowired TSysDataColumnsMapper dataColumnsMapper;
	@Override
	public List<TSysData> queryList(TSysData bean) {
		TSysDataExample example = new TSysDataExample();
		Criteria c = example.createCriteria();
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
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
		return dataMapper.selectByExample(example);
	}

	@Override
	public TSysData selectByPrimaryKey(Integer id) {
		return dataMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TSysData bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysDataExample example = new TSysDataExample();
		Criteria c = example.createCriteria();
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
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
		int count = (int) dataMapper.countByExample(example);
		List<TSysData> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		RowBounds rowBounds = new RowBounds(start, bean.getRows());
		if(count>0) {
			list = dataMapper.selectByExampleWithRowbounds(example, rowBounds);
		}else {
			list = new ArrayList<TSysData>(); 
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysData bean) {
		bean.setUpdatetime(DateUtil.getSysCurrentDate());
		bean.setGroupname(SystemUserInfo.getSystemUser().getUser().getGroupName());
		bean.setStatus(true);
		if(bean.getId()!=null) {
			bean.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			dataMapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			bean.setCreateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			dataMapper.insertSelective(bean);
			return true;
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean delete(int id)  throws WorkFlowStateException{
		try {
			dataMapper.deleteByPrimaryKey(id);
			TSysDataColumnsExample example = new TSysDataColumnsExample();
			example.createCriteria().andDataIdEqualTo(id);
			dataColumnsMapper.deleteByExample(example);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> queryDataTree(TSysData bean) {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		Map<String, Object> result= new  HashMap<String, Object>();
		result.put("id", -1);
		result.put("text", "数据集");
		result.put("state", "closed");
		String username = SystemUserInfo.getSystemUser().getUser().getUsername();
		String groupname = SystemUserInfo.getSystemUser().getUser().getGroupName();
		if("".equals(bean.getGroupname()) || bean.getGroupname()==null) {
			bean.setGroupname(groupname);
		}
		if(bean.getCreateuser()==null || "".equals(bean.getCreateuser())){
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
		}
		List<Map<String, Object>> list = dataMapper.queryDataTree(bean);
		result.put("children", list);
		if(list.size()<=0) {
			if(bean!=null) {
				if(bean.getId()!=null && bean.getId()==-1) result = null;
			}
		}
		trees.add(result);
		return trees;
	}

	@Override
	public List<Map<String, Object>> getDateType() {
		return dataMapper.getDateType();
	}

	@Override
	public List<Map<String, Object>> queryDataColumnsTree(TSysData bean) {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		bean = dataMapper.selectByPrimaryKey(bean.getId());
		Map<String, Object> result= new  HashMap<String, Object>();
		String username = SystemUserInfo.getSystemUser().getUser().getUsername();
		String groupname = SystemUserInfo.getSystemUser().getUser().getGroupName();
		result.put("createuser", username);
		result.put("groupname", groupname);
		String role = "user";
		for (TSysRole tSysRole : SystemUserInfo.getSystemUser().getRole()) {
			if(tSysRole.getRoleSign().equals("sadmin") || tSysRole.getRoleSign().equals("admin")) {
				role = "admin";
				break;
			}
		}
		result.put("role", role);
		result.put("id", bean.getId());
		List<Map<String, Object>> list = dataColumnsMapper.queryDataColumnsTree(result);
		result.clear();
		result.put("id", bean.getId());
		result.put("text", bean.getName()+"("+bean.getCode()+")");
		result.put("state", "open");
		result.put("children", list);
		trees.add(result);
		return trees;
	}

}
