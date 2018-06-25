/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.mapper.TSysTimerScheduleWorkflowMapper;
import com.gasq.bdp.logn.mapper.TSysTimerScheduleconfigMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflow;
import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflowExample;
import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflowExample.Criteria;
import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflowKey;
import com.gasq.bdp.logn.service.TSysTimerScheduleWorkflowService;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月20日上午11:30:43
 * @remark 
 */
@Service
public class TSysTimerScheduleWorkflowServiceImpl implements TSysTimerScheduleWorkflowService{

	@Autowired
	TSysTimerScheduleWorkflowMapper scheduleWorkflowMapper;
	@Autowired
	TSysTimerScheduleconfigMapper scheduleconfigMapper;
	
	@Override
	public long countByExample(TSysTimerScheduleWorkflowExample example) {
		return scheduleWorkflowMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(TSysTimerScheduleWorkflowExample example) {
		return scheduleWorkflowMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(TSysTimerScheduleWorkflowKey key) {
		return scheduleWorkflowMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(TSysTimerScheduleWorkflow bean) {
		TSysTimerScheduleWorkflowExample scheduleWorkflowExample = new TSysTimerScheduleWorkflowExample();
		Criteria c = scheduleWorkflowExample.createCriteria().andScheduleIdEqualTo(bean.getScheduleId()).andWorkflowIdEqualTo(bean.getWorkflowId());
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
		int count = (int)scheduleWorkflowMapper.countByExample(scheduleWorkflowExample);
		if(count<=0) {
			bean.setStatus(true);
			bean.setCreateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			return scheduleWorkflowMapper.insertSelective(bean);
		}else {
			bean.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			return scheduleWorkflowMapper.updateByPrimaryKeySelective(bean);
		}
	}

	@Override
	public List<TSysTimerScheduleWorkflow> selectByExample(TSysTimerScheduleWorkflowExample example) {
		return scheduleWorkflowMapper.selectByExample(example);
	}

	@Override
	public TSysTimerScheduleWorkflow selectByPrimaryKey(TSysTimerScheduleWorkflowKey key) {
		return scheduleWorkflowMapper.selectByPrimaryKey(key);
	}

	@Override
	public int updateByExampleSelective(TSysTimerScheduleWorkflow record, TSysTimerScheduleWorkflowExample example) {
		return scheduleWorkflowMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(TSysTimerScheduleWorkflow record, TSysTimerScheduleWorkflowExample example) {
		return scheduleWorkflowMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(TSysTimerScheduleWorkflow record) {
		return scheduleWorkflowMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TSysTimerScheduleWorkflow record) {
		return scheduleWorkflowMapper.updateByPrimaryKey(record);
	}

	@Override
	public Map<String, Object> getScheduleWorkflowById(TSysTimerScheduleWorkflowKey key) {
		List<Map<String, Object>> map = scheduleWorkflowMapper.getScheduleWorkflowById(key);
		return map.get(0);
	}

	@Override
	public int changeWorkflowStatus(TSysTimerScheduleWorkflow scheduleWorkflow) {
		return scheduleWorkflowMapper.updateByPrimaryKeySelective(scheduleWorkflow);
	}

	@Override
	@Transactional(value="gabdpTransactionManager",rollbackFor=Exception.class)
	public int changeWorkflowSort(TSysTimerScheduleWorkflow scheduleWorkflow) {
		int result = 0;
		try {
			TSysTimerScheduleWorkflowExample scheduleWorkflowExample = new TSysTimerScheduleWorkflowExample();
			scheduleWorkflowExample.createCriteria().andScheduleIdEqualTo(scheduleWorkflow.getScheduleId());
			scheduleWorkflowExample.setOrderByClause("sort asc");
			List<TSysTimerScheduleWorkflow> listswf = scheduleWorkflowMapper.selectByExample(scheduleWorkflowExample);
			int i = 0;
			for (Iterator<TSysTimerScheduleWorkflow> iterator = listswf.iterator(); iterator.hasNext();) {
				TSysTimerScheduleWorkflow tSysTimerScheduleWorkflow = (TSysTimerScheduleWorkflow) iterator.next();
				if(tSysTimerScheduleWorkflow.getWorkflowId().equals(scheduleWorkflow.getWorkflowId()) && tSysTimerScheduleWorkflow.getScheduleId().equals(scheduleWorkflow.getScheduleId())) {
					if(scheduleWorkflow.getSort()>0) {
						TSysTimerScheduleWorkflow scheduleWorkflow2 = listswf.get(i-1);
						int upsort = scheduleWorkflow2.getSort();
						if(upsort == tSysTimerScheduleWorkflow.getSort()) { upsort--; }
						scheduleWorkflow2.setSort(tSysTimerScheduleWorkflow.getSort());
						tSysTimerScheduleWorkflow.setSort(upsort);
						result = scheduleWorkflowMapper.updateByPrimaryKeySelective(scheduleWorkflow2);
						result = scheduleWorkflowMapper.updateByPrimaryKeySelective(tSysTimerScheduleWorkflow);
					}else {
						if(iterator.hasNext()) {
							TSysTimerScheduleWorkflow scheduleWorkflow2 = iterator.next();
							int downsort = scheduleWorkflow2.getSort();
							if(downsort==tSysTimerScheduleWorkflow.getSort()) {downsort++;}
							scheduleWorkflow2.setSort(tSysTimerScheduleWorkflow.getSort());
							tSysTimerScheduleWorkflow.setSort(downsort);
							result = scheduleWorkflowMapper.updateByPrimaryKeySelective(scheduleWorkflow2);
							result = scheduleWorkflowMapper.updateByPrimaryKeySelective(tSysTimerScheduleWorkflow);
						}
					}
				}
				i++;
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return result;
	}

}
