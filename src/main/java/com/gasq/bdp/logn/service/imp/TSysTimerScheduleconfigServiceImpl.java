/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.component.MyDAO;
import com.gasq.bdp.logn.component.MyScheduler;
import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.job.InterfaceType;
import com.gasq.bdp.logn.job.SqlQueryOptionLogJob;
import com.gasq.bdp.logn.mapper.TSysTimerJobSqlQueryMapper;
import com.gasq.bdp.logn.mapper.TSysTimerJobconfigMapper;
import com.gasq.bdp.logn.mapper.TSysTimerScheduleWorkflowMapper;
import com.gasq.bdp.logn.mapper.TSysTimerScheduleconfigMapper;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.PagingBean;
import com.gasq.bdp.logn.model.SystemUser;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysTimerJobSqlQuery;
import com.gasq.bdp.logn.model.TSysTimerJobconfig;
import com.gasq.bdp.logn.model.TSysTimerJobconfigExample;
import com.gasq.bdp.logn.model.TSysTimerLog;
import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflow;
import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflowExample;
import com.gasq.bdp.logn.model.TSysTimerScheduleconfig;
import com.gasq.bdp.logn.model.TSysTimerScheduleconfigExample;
import com.gasq.bdp.logn.model.TSysTimerScheduleconfigExample.Criteria;
import com.gasq.bdp.logn.model.TSysTimerWfExeInfo;
import com.gasq.bdp.logn.service.EmailManager;
import com.gasq.bdp.logn.service.TSysTimerLogService;
import com.gasq.bdp.logn.service.TSysTimerScheduleconfigService;
import com.gasq.bdp.logn.service.TSysTimerWfExeInfoService;
import com.gasq.bdp.logn.state.StateContext;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月13日下午1:37:35
 * @remark 
 */
@Service
public class TSysTimerScheduleconfigServiceImpl implements TSysTimerScheduleconfigService {
	private Logger Log = Logger.getLogger(this.getClass());
	@Autowired TSysTimerScheduleconfigMapper scheduleconfigMapper;
	@Autowired TSysTimerScheduleWorkflowMapper scheduleWorkflowMapper;
	@Autowired TSysTimerJobconfigMapper jobconfigMapper;
	@Autowired TSysTimerJobSqlQueryMapper sqlQueryMapper;
	@Autowired MyDAO dao;
	@Autowired StateContext stateContext;
	@Autowired SqlQueryOptionLogJob sqlQueryOptionLogJob;
	@Autowired TSysTimerLogService logService;
	@Autowired EmailManager emailService;
	@Autowired TSysTimerWfExeInfoService exeInfoService;
	@Autowired MyScheduler scheduler;
	int logid = 0;
	@Override
	public long selectScheduleCount(TSysTimerScheduleconfig bean) throws Exception {
		TSysTimerScheduleconfigExample example = new TSysTimerScheduleconfigExample();
		Criteria c = example.createCriteria();
		String cron = bean.getCron();
		if(!"".equals(cron) && cron!=null) {
			c.andCronEqualTo(bean.getCron());
		}
		if(!"".equals(bean.getGroupname()) && bean.getGroupname()!=null) {
			c.andGroupnameEqualTo(bean.getGroupname());
		}
		if(!"".equals(bean.getName()) && bean.getName()!=null) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		if(bean.getCreateuser()!=null){
			c.andCreateuserEqualTo(bean.getCreateuser());
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
		return scheduleconfigMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(TSysTimerScheduleconfigExample example) throws Exception {
		return scheduleconfigMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) throws Exception {
		return scheduleconfigMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TSysTimerScheduleconfig> selectByExample(TSysTimerScheduleconfig bean) throws Exception {
		TSysTimerScheduleconfigExample example = new TSysTimerScheduleconfigExample();
		Criteria c = example.createCriteria();
		String cron = bean.getCron();
		if(!"".equals(cron) && cron!=null) {
			c.andCronEqualTo(bean.getCron());
		}
		if(!"".equals(bean.getGroupname()) && bean.getGroupname()!=null) {
			c.andGroupnameEqualTo(bean.getGroupname());
		}
		if(!"".equals(bean.getName()) && bean.getName()!=null) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		if(bean.getCreateuser()!=null && !"".equals(bean.getCreateuser())){
			c.andCreateuserEqualTo(bean.getCreateuser());
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
		return scheduleconfigMapper.selectByExample(example);
	}

	@Override
	public TSysTimerScheduleconfig selectByPrimaryKey(Long id) throws Exception {
		return scheduleconfigMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<TSysTimerScheduleconfig> selectByExamplePaging(TSysTimerScheduleconfig bean, int offset,int limit) throws Exception {
		TSysTimerScheduleconfigExample example = new TSysTimerScheduleconfigExample();
		Criteria c = example.createCriteria();
		if(!"".equals(bean.getCron()) && bean.getCron()!=null) {
			c.andCronEqualTo(bean.getCron());
		}
		if(!"".equals(bean.getName()) && bean.getName()!=null) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
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
		RowBounds rowBounds = new RowBounds(offset, limit);
		return scheduleconfigMapper.selectByExampleWithRowbounds(example,rowBounds);
	}

	@Override
	public int saveOrUpdate(TSysTimerScheduleconfig scheduleconfig) throws Exception {
		SystemUser user = SystemUserInfo.getSystemUser();
		int result = 0;
		scheduleconfig.setUpdatetime(DateUtil.getSysCurrentDate());
		scheduleconfig.setGroupname(SystemUserInfo.getSystemUser().getUser().getGroupName());
		if(scheduleconfig.getId()!=null) {//update
			scheduleconfig.setUpdateuser(user.getUser().getUsername());
			result = scheduleconfigMapper.updateByPrimaryKeySelective(scheduleconfig);
			scheduler.removeJob(scheduleconfig);
			scheduler.addJob(scheduleconfig);
		}else {
			scheduleconfig.setCreateuser(user.getUser().getUsername());
			result = scheduleconfigMapper.insertSelective(scheduleconfig);
			scheduler.addJob(scheduleconfig);
		}
		return result;
	}

	@Override
	public boolean manualExecute(int id) throws Exception {
			SystemUser user = SystemUserInfo.getSystemUser();
			stateContext.setStatus(InitProperties.WORKFLOW_STATUS_READY);
			TSysTimerScheduleWorkflowExample scheduleWorkflowExample = new TSysTimerScheduleWorkflowExample();
			scheduleWorkflowExample.createCriteria().andScheduleIdEqualTo(id).andStatusEqualTo(true);
			scheduleWorkflowExample.setOrderByClause("sort asc");
			List<TSysTimerScheduleWorkflow> listwfs = scheduleWorkflowMapper.selectByExample(scheduleWorkflowExample);
			if (listwfs.size() > 0) {
				for (TSysTimerScheduleWorkflow scheduleWorkflow : listwfs) {
					logid = logService.getMaxNewId();
					TSysTimerJobconfig stjonc = getNextExeNode(scheduleWorkflow,null);
					TSysTimerJobconfig stjonc_each = null;
					String code = stjonc.getCode();
					boolean flag = true;
					Map<String,Object> map = null;
					Object inputObj = null;
					PagingBean pb = null;
					if(stjonc!=null) {
						while(flag) {
							map = execute(stjonc,scheduleWorkflow,inputObj,pb);
							inputObj = map.get("result");
							flag = (boolean) map.get("state");
							if(stjonc.getNodeType()==99) {
								flag = false;
								break;
							}
							code = stjonc.getCode();
							stjonc = getNextExeNode(scheduleWorkflow,code);
							int type = stjonc.getType();
							int excute_id = stjonc.getExcuteId();
							if(type==1) {
								Map<String,Object> map_each = null;
								stjonc_each = stjonc;
								Object inputObj_each = inputObj;
								boolean flag_each = true;
								String code_each = stjonc_each.getCode();
								TSysTimerJobSqlQuery sqlquery = sqlQueryMapper.selectByPrimaryKey((long)excute_id);
								if(sqlquery.getIslimit()) {
									int pagenum = sqlquery.getPagenum();//分页条数
									pagenum = (pagenum==0) ? 1000 : pagenum;
									Connection conn = dao.getConnection(sqlquery.getDbCode());
									String sql = sqlQueryOptionLogJob.handleSql(sqlquery, inputObj_each);
									String countsql = "select count(1) from ("+sql+") temp ";
									int count = dao.queryCount(conn,countsql,sqlquery.getDbCode());
									int eachSize = count/pagenum==0?1:count/pagenum;
									pb = new PagingBean();
									boolean b_each = true;
									int index = 0;
									while(b_each&&index<eachSize) {
										int pagesize = (index==0) ? 1 : index;
										index = (pagesize - 1) * pagenum;
										pb.setIndex(index);
										pb.setSize(pagenum);
										map_each = execute(stjonc_each,scheduleWorkflow,inputObj_each,pb);
										code_each = stjonc_each.getCode();
										inputObj_each = map_each.get("result");
										flag_each = (boolean) map_each.get("state");
										if(flag_each) {
											stjonc_each = getNextExeNode(scheduleWorkflow,code_each);
											if(stjonc_each==null) break;
											if(stjonc_each.getNodeType()==99) {
												index++;
												if(index==eachSize) {
													stjonc = stjonc_each;
												}
											}
											b_each = true;
										}else {
											b_each = false;
											String mess = "定时器：【" + scheduleWorkflow.getScheduleId() + "-》" + scheduleWorkflow.getName() + "】运行失败！错误信息如下：" ;
											Log.info(mess);
											logService.add(new TSysTimerLog(logid,scheduleWorkflow.getName()+"->"+stjonc_each.getName(),InitProperties.TimmerLog_FAIL,excute_id,scheduleWorkflow.getWorkflowId(),scheduleWorkflow.getScheduleId(),1,DateUtil.getSysCurrentDate(),user.getUser().getUsername(),mess));
											// 任务失败不再往下进行
											throw new WorkFlowStateException(InterfaceType.getValueName(stateContext.getExetype())+"运行失败！"+mess);
										}
									}
								}
							}else {
								inputObj = map.get("result");
								flag = (boolean) map.get("state");
								if(stjonc.getNodeType()==99) {
									flag = false;
									execute(stjonc,scheduleWorkflow,null,pb);
								}
								code = stjonc.getCode();
							}
						}
					}
				}
			}else {
				Log.info("JOB [" +id+"] 没有要执行的定时任务！");
				logService.add(new TSysTimerLog(logid,null,InitProperties.TimmerLog_FAIL,null,null,id,1,DateUtil.getSysCurrentDate(),user.getUser().getUsername(),"JOB [" +id+"] 没有要执行的定时任务！"));
				return false;
			}
		logService.add(new TSysTimerLog(logid,listwfs.get(0).getName(),InitProperties.TIMMERLOG_SUCCESS,null,listwfs.get(0).getWorkflowId(),listwfs.get(0).getScheduleId(),1,DateUtil.getSysCurrentDate(),user.getUser().getUsername(),listwfs.get(0).getName()+"执行成功！"));
		return true;
	}
	
	private Map<String,Object> execute(TSysTimerJobconfig tSysTimerJobconfig,TSysTimerScheduleWorkflow scheduleWorkflow, Object inputObj, PagingBean pb) throws WorkFlowStateException {
		SystemUser user = SystemUserInfo.getSystemUser();
		Map<String,Object> result = new HashMap<String,Object>();
		boolean b = true;
		Object obj = null;
		int excute_id = 0;
		Date sdate = DateUtil.getSysCurrentDate();
		Date edate = null;
		Integer scId = scheduleWorkflow.getScheduleId();
		Integer wfid = scheduleWorkflow.getWorkflowId();
		if(tSysTimerJobconfig!=null) {
			int type = tSysTimerJobconfig.getType();
			excute_id = tSysTimerJobconfig.getExcuteId();
			try {
				stateContext.setWorkFlowState(InterfaceType.valueOf(type).getIntfaceService());
				obj = stateContext.run(excute_id,inputObj,pb);
				edate = DateUtil.getSysCurrentDate();
			} catch (WorkFlowStateException e) {
				if(edate==null) edate = DateUtil.getSysCurrentDate();
				if(scheduleWorkflow.getIgnoreerror()) {
					exeInfoService.saveOrUpdate(new TSysTimerWfExeInfo(logid,scheduleWorkflow.getScheduleId(),scheduleWorkflow.getWorkflowId(),excute_id,DateUtil.differentDaysByMillisecond(sdate, edate),true,DateUtil.getSysCurrentDate(),null));
					result.put("state", b);
					result.put("result", obj);
					return result;
				}else {
					String mess = "定时器：【" + scheduleWorkflow.getScheduleId() + "-》" + scheduleWorkflow.getName() + "】运行失败！错误信息如下：" + e.getMessage();
					Log.info(mess, e);
					logService.add(new TSysTimerLog(logid,scheduleWorkflow.getName(),InitProperties.TimmerLog_FAIL,excute_id,scheduleWorkflow.getWorkflowId(),scheduleWorkflow.getScheduleId(),1,DateUtil.getSysCurrentDate(),user.getUser().getUsername(),e.getMessage()));
					// 任务失败不再往下进行
					throw new WorkFlowStateException(mess);
				}
			}
		}
		if(edate==null) edate = DateUtil.getSysCurrentDate();
		exeInfoService.saveOrUpdate(new TSysTimerWfExeInfo(logid,scId,wfid,excute_id,DateUtil.differentDaysByMillisecond(sdate, edate),true,DateUtil.getSysCurrentDate(),null));
		result.put("state", b);
		result.put("result", obj);
		return result;
	}
	private TSysTimerJobconfig getNextExeNode(TSysTimerScheduleWorkflow scheduleWorkflow,String startcode) {
		if(startcode == null ) {//获取开始节点
			TSysTimerJobconfigExample jobexample = new TSysTimerJobconfigExample();
			jobexample.createCriteria().andWorkflowIdEqualTo(scheduleWorkflow.getWorkflowId()).andStatusEqualTo(true).andModelEqualTo(0).andNodeTypeEqualTo(0);
			jobexample.setOrderByClause("sort asc");
			List<TSysTimerJobconfig> joblist = jobconfigMapper.selectByExample(jobexample);
			if(joblist.size()>0) {
				return joblist.get(0);
			}
		}else {
			return getNextTSysTimerJobconfig(scheduleWorkflow,startcode);
		}
		return null;
	}
	private TSysTimerJobconfig getNextTSysTimerJobconfig(TSysTimerScheduleWorkflow scheduleWorkflow,String startcode) {
		TSysTimerJobconfigExample jobexample = new TSysTimerJobconfigExample();
		jobexample.createCriteria().andWorkflowIdEqualTo(scheduleWorkflow.getWorkflowId()).andModelEqualTo(1).andStartEqualTo(startcode);
		List<TSysTimerJobconfig> joblist = jobconfigMapper.selectByExample(jobexample);
		if(joblist.size()>0) {
			TSysTimerJobconfig jobc = joblist.get(0);
			TSysTimerJobconfigExample jobexample1 = new TSysTimerJobconfigExample();
			jobexample1.createCriteria().andWorkflowIdEqualTo(scheduleWorkflow.getWorkflowId()).andModelEqualTo(0).andCodeEqualTo(jobc.getEnd());
			List<TSysTimerJobconfig> joblist1 = jobconfigMapper.selectByExample(jobexample1);
			if(joblist1.size()>0) {
				if(joblist1.get(0).getStatus()) {
					return joblist1.get(0);
				}else {
					exeInfoService.saveOrUpdate(new TSysTimerWfExeInfo(logid,scheduleWorkflow.getScheduleId(),scheduleWorkflow.getWorkflowId(),joblist1.get(0).getExcuteId(),0.0,true,DateUtil.getSysCurrentDate(),null));
					return getNextTSysTimerJobconfig(scheduleWorkflow,joblist1.get(0).getCode());
				}
			}
		}
		return null;
	}

	@Override
	public List<Map<String,Object>> queryScheduleTree(TSysTimerScheduleconfig bean) {
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
		List<Map<String, Object>> list = scheduleconfigMapper.queryScheduleTree(bean);
		return list;
	}
}
