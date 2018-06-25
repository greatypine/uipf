/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.mapper.TSysTimerWorkflowMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysTimerWorkflow;
import com.gasq.bdp.logn.model.TSysTimerWorkflowExample;
import com.gasq.bdp.logn.model.TSysTimerWorkflowExample.Criteria;
import com.gasq.bdp.logn.service.TSysTimerWorkflowService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月13日下午4:04:28
 * @remark 
 */
@Service
public class TSysTimerWorkflowServiceImpl implements TSysTimerWorkflowService {
	@Autowired
	TSysTimerWorkflowMapper  wfmapper;

	@Override
	public long countByExample(TSysTimerWorkflowExample example) {
		return wfmapper.countByExample(example);
	}

	@Override
	public int deleteByExample(TSysTimerWorkflowExample example) {
		return wfmapper.deleteByExample(example);
	}

	@Override
	public int insert(TSysTimerWorkflow record) {
		return wfmapper.insertSelective(record);
	}

	@Override
	public int updateByExample(TSysTimerWorkflow record, TSysTimerWorkflowExample example) {
		return wfmapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(TSysTimerWorkflow record) {
		return wfmapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Map<String, Object> queryWorkFlowTree(Integer pid) {
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
		List<Map<String, Object>> list = wfmapper.queryWorkFlowTree(result);
		result.clear();
		result.put("id", -1);
		result.put("text", "工作流");
		result.put("state", "closed");
		result.put("children", list);
		if(list.size()<=0) {
			if(pid!=null && pid==-1) result = null;
		}
		return result;
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return wfmapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Map<String, Object>> getWorkflowJobList(Integer typeid,Integer jobid) {
		String username = SystemUserInfo.getSystemUser().getUser().getUsername();
		String groupname = SystemUserInfo.getSystemUser().getUser().getGroupName();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("type", typeid);
		params.put("jobid", jobid);
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
		return wfmapper.getWorkflowJobList(params);
	}

	@Override
	public List<Map<String, Object>> getWorkflowJobType(Integer id) {
		return wfmapper.getWorkflowJobType(id);
	}

	@Override
	public List<TSysTimerWorkflow> queryList(TSysTimerWorkflow bean) {
		TSysTimerWorkflowExample example = new TSysTimerWorkflowExample();
		Criteria c = example.createCriteria().andStatusEqualTo(true);
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameEqualTo(bean.getName());
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
		return wfmapper.selectByExample(example);
	}

	@Override
	public Boolean saveOrUpdate(TSysTimerWorkflow workflow) {
		workflow.setGroupname(SystemUserInfo.getSystemUser().getUser().getGroupName());
		workflow.setUpdatetime(DateUtil.getSysCurrentDate());
		if(workflow.getId()!=null) {
			workflow.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			wfmapper.updateByPrimaryKeySelective(workflow);
			return true;
		}else {
			workflow.setCreatetime(DateUtil.getSysCurrentDate());
			workflow.setCreateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			wfmapper.insertSelective(workflow);
			return true;
		}
	}

	@Override
	public Boolean delete(int id) {
		return wfmapper.deleteByPrimaryKey((long)id)>0?true:false;
	}

}
