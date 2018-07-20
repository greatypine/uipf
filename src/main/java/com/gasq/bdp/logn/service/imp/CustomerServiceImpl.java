/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.component.MyScheduler;
import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.mapper.TCompanyMapper;
import com.gasq.bdp.logn.mapper.TConsumptonProjectMapper;
import com.gasq.bdp.logn.mapper.TCustomerCommentMapper;
import com.gasq.bdp.logn.mapper.TCustomerConsumptonLogMapper;
import com.gasq.bdp.logn.mapper.TCustomerPorjectMapper;
import com.gasq.bdp.logn.mapper.TCustomerProjectLogMapper;
import com.gasq.bdp.logn.mapper.TCustomerSubscribeMapper;
import com.gasq.bdp.logn.mapper.TInventoryMapper;
import com.gasq.bdp.logn.mapper.TLtnCustomerConsumptonAmountMapper;
import com.gasq.bdp.logn.mapper.TLtnCustomerMapper;
import com.gasq.bdp.logn.mapper.TSysTimerScheduleconfigMapper;
import com.gasq.bdp.logn.mapper.TVipCustomerMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TConsumptonProject;
import com.gasq.bdp.logn.model.TConsumptonProjectExample;
import com.gasq.bdp.logn.model.TCustomerComment;
import com.gasq.bdp.logn.model.TCustomerCommentExample;
import com.gasq.bdp.logn.model.TCustomerConsumptonLog;
import com.gasq.bdp.logn.model.TCustomerPorject;
import com.gasq.bdp.logn.model.TCustomerPorjectExample;
import com.gasq.bdp.logn.model.TCustomerProjectLogExample;
import com.gasq.bdp.logn.model.TCustomerSubscribe;
import com.gasq.bdp.logn.model.TInventory;
import com.gasq.bdp.logn.model.TLtnCustomer;
import com.gasq.bdp.logn.model.TLtnCustomerConsumptonAmount;
import com.gasq.bdp.logn.model.TLtnCustomerConsumptonAmountExample;
import com.gasq.bdp.logn.model.TLtnCustomerExample;
import com.gasq.bdp.logn.model.TProject;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TVipCustomer;
import com.gasq.bdp.logn.model.TVipCustomerExample;
import com.gasq.bdp.logn.service.CustomerService;
import com.gasq.bdp.logn.service.TSysProjectService;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

/**
 * @author Ju_weigang
 * @时间 2018年1月22日下午3:59:06
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述 
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired TLtnCustomerMapper customerMapper;
	@Autowired TLtnCustomerConsumptonAmountMapper consumptonAmountService;
	@Autowired MyScheduler scheduler;
	@Autowired TSysTimerScheduleconfigMapper scheduleconfigMapper;
	@Autowired TCustomerSubscribeMapper customerSubscribeMapper;
	@Autowired TCustomerConsumptonLogMapper customerConsumptonLogMapper;
	@Autowired TVipCustomerMapper vipCustomerMapper;
	@Autowired TCompanyMapper companyMapper;
	@Autowired TInventoryMapper inventoryMapper;
	@Autowired TConsumptonProjectMapper consumptonProjectMapper;
	@Autowired TCustomerPorjectMapper customerProjectService;
	@Autowired TCustomerProjectLogMapper customerProjectLogService;
	@Autowired TCustomerCommentMapper customerCommentService;
	@Autowired TSysProjectService projectService;
//	@Autowired ActiveManager activeManager;
	
	@Value("${wf.serverUrlPrefix}")
	private String wfServerUrlPrefix;
	@Override
	public long countByExample(TLtnCustomerExample example) {
		return customerMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(TLtnCustomerExample example) {
		return customerMapper.deleteByExample(example);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean delete(int id)throws WorkFlowStateException {
		try {
			TLtnCustomer customer = customerMapper.selectByPrimaryKey(id);
			customer.setStatus(99);
			customerMapper.updateByPrimaryKeySelective(customer);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】更新订单信息成功！更新状态为【关闭】");
			TLtnCustomerConsumptonAmountExample example = new TLtnCustomerConsumptonAmountExample();
			example.createCriteria().andCustomerIdEqualTo(id);
			List<TLtnCustomerConsumptonAmount> ccalists = consumptonAmountService.selectByExample(example);
			for(TLtnCustomerConsumptonAmount cca : ccalists) {
				TConsumptonProjectExample example1 = new TConsumptonProjectExample();
				example1.createCriteria().andConsumptonAmountIdEqualTo(cca.getId());
				List<TConsumptonProject> cplist = consumptonProjectMapper.selectByExample(example1);
				for(TConsumptonProject cp : cplist) {
					Integer projectId = cp.getProjectId();
					TInventory inventory = inventoryMapper.selectByPrimaryKey(projectId);
					inventory.setInventory(inventory.getInventory().add(cp.getNumbs()));
					inventoryMapper.updateByPrimaryKeySelective(inventory);
					logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】更新库存信息成功！已将删除的商品【"+inventory.getName()+"】数量【"+cp.getNumbs()+"】返回到库存中去！");
					TCustomerPorjectExample cpexample = new TCustomerPorjectExample();
					cpexample.createCriteria().andOrderIdEqualTo(id).andProjectIdEqualTo(projectId);
					List<TCustomerPorject> list = customerProjectService.selectByExample(cpexample);
					if(list.size()>0) {
						for (TCustomerPorject tCustomerPorject : list) {
							TCustomerProjectLogExample cpl = new TCustomerProjectLogExample();
							cpl.createCriteria().andCpIdEqualTo(tCustomerPorject.getId());
							customerProjectLogService.deleteByExample(cpl);
						}
						logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】删除客户项目消费日志信息成功！");
					}
					customerProjectService.deleteByExample(cpexample);
					logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】删除客户项目信息成功！");
				}
			}
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】删除消费订单操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
		}
		return false;
	}
	
	@Override
	public int insertSelective(TLtnCustomer record) {
		return customerMapper.insertSelective(record);
	}

	@Override
	public List<TLtnCustomer> selectByExample(TLtnCustomerExample example) {
		return customerMapper.selectByExample(example);
	}

	@Override
	public TLtnCustomer selectByPrimaryKey(Integer id) {
		return customerMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TLtnCustomer bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		List<Map<String,Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		bean.setPage(start);
		bean.setRows(number);
		if(bean.getCompanyId()==null) {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.H_OPTION)) {
				bean.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		if(bean.getStatus()==null) {
			if(WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.Q_ADMIN)) {
				String statuss = String.join(",","0","1","98","99");
				bean.setStatuss(statuss.split(","));
			}else {
				String statuss = String.join(",","0","1");
				bean.setStatuss(statuss.split(","));
			}
		}else {
			String statuss = String.join(",",bean.getStatus().toString());
			bean.setStatuss(statuss.split(","));
		}
		list = customerMapper.queryPagingList(bean);
		if(list==null || list.size()<=0)list = new ArrayList<Map<String,Object>>();
		Integer count = customerMapper.countByBean(bean);
		result.put("rows",list);
		result.put("total",count);
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】查询消费订单信息列表完成！查询条数："+count+",查询参数："+CommonUtils.bean2Json(bean));
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> saveOrUpdate(TLtnCustomer bean) throws SchedulerException {
		Map<String, Object> result= new  HashMap<String, Object>();
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			Integer type = bean.getType();
			List<TVipCustomer> list2 = null;
			BigDecimal ttamount = null;
			double totalAmount = 0;
			if(type!=null) {
				if(bean.getStatus()==1) {
					TLtnCustomerConsumptonAmountExample example = new TLtnCustomerConsumptonAmountExample();
					example.createCriteria().andCustomerIdEqualTo(bean.getId());
					List<TLtnCustomerConsumptonAmount> list = consumptonAmountService.selectByExample(example);
					if(list!=null && list.size()>0) {
						totalAmount = list.stream().mapToDouble(b -> b.getTotalAmount().doubleValue()).sum();
						logger.info("用户："+bean.getCustomername()+"，手机:"+bean.getPhonenumb()+",消费总金额为："+totalAmount);
					}
					if(type==3) {//会员结算
						TVipCustomerExample example1 = new TVipCustomerExample();
						example1.createCriteria().andCustomerPhoneEqualTo(bean.getPhonenumb());
						list2 = vipCustomerMapper.selectByExample(example1);
						if(list2!=null && list2.size()>0) {
							ttamount = list2.get(0).getActualAmount().add(list2.get(0).getGiveAmount());
							if(ttamount.doubleValue()<totalAmount) {//会员用户余额不足
								String mess = "用户："+bean.getCustomername()+"，手机:"+bean.getPhonenumb()+"账号余额不足，请充值后再进行操作！";
								result.put("status", false);
								result.put("mess", mess);
								return result;
							}
						}else {
							result.put("status", false);
							result.put("mess", "用户："+bean.getCustomername()+"，手机:"+bean.getPhonenumb()+"不是会员用户，请先注册为会员用户再进行操作！");
							return result;
						}
					}
				}
			}
			bean.setUpdatetime(DateUtil.getSysCurrentDate());
			bean.setCompanyId(user.getCompanyid());
			if(bean.getId()!=null) {
				bean.setUpdateuser(user.getUsername());
				customerMapper.updateByPrimaryKeySelective(bean);
				logger.info("更改修改用户："+bean.getCustomername()+"，手机:"+bean.getPhonenumb()+",消费记录！");
			}else {
				bean.setCreateuser(user.getUsername());
				bean.setCreatetime(DateUtil.getSysCurrentDate());
				customerMapper.insertSelective(bean);
				logger.info("成功添加用户："+bean.getCustomername()+"，手机:"+bean.getPhonenumb()+"消费记录！");
			}
			if(type!=null) {
				if(type<3 && bean.getStatus()==1) {//普通线下（移动）支付
					customerConsumptonLogMapper.insertSelective(new TCustomerConsumptonLog(bean.getId(),bean.getCustomername(),bean.getPhonenumb(),bean.getSex(),new BigDecimal(totalAmount),null,type,user.getCompanyid(),user.getUsername()));
					logger.info("用户："+bean.getCustomername()+"，手机:"+bean.getPhonenumb()+"，生成支付记录！");
				}else if(type==3 && bean.getStatus()==1){
					TVipCustomer tVipCustomer = list2.get(0);
					BigDecimal actualAmount = tVipCustomer.getActualAmount();
					if(actualAmount.doubleValue()>totalAmount) {//实际充值金额大于消费金额 （先减去实际充值金额）
						tVipCustomer.setActualAmount(actualAmount.subtract(new BigDecimal(totalAmount)));
					}else {
						BigDecimal giveAmount = tVipCustomer.getGiveAmount();
						BigDecimal add = actualAmount.add(giveAmount);
						tVipCustomer.setActualAmount(null);
						tVipCustomer.setGiveAmount(add.subtract(new BigDecimal(totalAmount)));
					}
					vipCustomerMapper.updateByPrimaryKeySelective(tVipCustomer);
					customerConsumptonLogMapper.insertSelective(new TCustomerConsumptonLog(bean.getId(),bean.getCustomername(),bean.getPhonenumb(),bean.getSex(),new BigDecimal(totalAmount),ttamount.subtract(new BigDecimal(totalAmount)),type,user.getCompanyid(),user.getUsername()));
					logger.info("用户："+bean.getCustomername()+"，手机:"+bean.getPhonenumb()+"，生成支付记录！");
				}
				if(bean.getStatus()==1) {
					TVipCustomerExample vcexample = new TVipCustomerExample();
					vcexample.createCriteria().andCustomerPhoneEqualTo(bean.getPhonenumb());
					List<TVipCustomer> list = vipCustomerMapper.selectByExample(vcexample);
					if(list.size()>0) {
						TVipCustomer customer = list.get(0);
						if(StringUtils.isNotBlank(bean.getCustomername()))customer.setCustomerName(bean.getCustomername());
						if(StringUtils.isNotBlank(bean.getPhonenumb()))customer.setCustomerPhone(bean.getPhonenumb());
						if(StringUtils.isNotBlank(bean.getCardId()))customer.setEmail(bean.getCardId());
						if(bean.getProfession()!=null)customer.setProfession(bean.getProfession());
						if(bean.getSex()!=null)customer.setSex(bean.getSex());
						vipCustomerMapper.updateByPrimaryKeySelective(customer);
						logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】更新客户信息成功！");
						TLtnCustomerConsumptonAmountExample tccaexample = new TLtnCustomerConsumptonAmountExample();
						tccaexample.createCriteria().andCustomerIdEqualTo(bean.getId());
						List<TLtnCustomerConsumptonAmount> listssa = consumptonAmountService.selectByExample(tccaexample);
						if(listssa.size()>0) {
							for(TLtnCustomerConsumptonAmount cca : listssa) {
								TProject tProject = projectService.selectByPrimaryKey((long)cca.getProjectId());
								Integer deadline = tProject.getDeadline();
								Date dl = null;
								if(deadline!=null)dl = DateUtil.getDiyDateMonth(DateUtil.getSysCurrentDate(), deadline);
								customerProjectService.insertSelective(new TCustomerPorject(customer.getId(),bean.getId(),cca.getProjectId(),tProject.getProjectType(),tProject.getProjectNums(),tProject.getProjectNums(),dl,user.getUsername(),DateUtil.getSysCurrentDate()));
							}
							logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加客户项目信息成功！添加条数【"+listssa.size()+"】");
						}
						if(StringUtils.isNotBlank(bean.getRemark())) {
							TCustomerCommentExample tccexample = new TCustomerCommentExample();
							tccexample.createCriteria().andRemarkEqualTo(bean.getRemark()).andVipIdEqualTo(customer.getId());
							long l = customerCommentService.countByExample(tccexample);
							if(l<=0) {
								TCustomerComment cc = new TCustomerComment();
								cc.setRemark(bean.getRemark());
								cc.setVipId(customer.getId());
								cc.setCreateUser(SystemUserInfo.getSystemUser().getUser().getNickname());
								cc.setCreateTime(DateUtil.getSysCurrentDate());
								customerCommentService.insertSelective(cc);
							}
							logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加客户留言信息成功！添加条数【"+l+"】");
						}
					}else {
						TVipCustomer vip = new TVipCustomer();
						vip.setCompanyId(bean.getCompanyId());
						vip.setCustomerName(bean.getCustomername());
						vip.setCustomerPhone(bean.getPhonenumb());
						vip.setSex(bean.getSex());
						vip.setStatus(1);
						vip.setProfession(bean.getProfession());
						vip.setEmail(bean.getCardId());
						vip.setCreateTime(DateUtil.getSysCurrentDate());
						vip.setCreateUser(SystemUserInfo.getSystemUser().getUser().getUsername());
						vipCustomerMapper.insertSelective(vip);
						logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加客户信息成功！");
						if(StringUtils.isNotBlank(bean.getRemark())) {
							TCustomerCommentExample tccexample = new TCustomerCommentExample();
							tccexample.createCriteria().andRemarkEqualTo(bean.getRemark()).andVipIdEqualTo(vip.getId());
							long l = customerCommentService.countByExample(tccexample);
							if(l<=0) {
								TCustomerComment cc = new TCustomerComment();
								cc.setRemark(bean.getRemark());
								cc.setVipId(vip.getId());
								cc.setCreateUser(SystemUserInfo.getSystemUser().getUser().getNickname());
								cc.setCreateTime(DateUtil.getSysCurrentDate());
								customerCommentService.insertSelective(cc);
							}
							logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加客户留言信息成功！添加条数【"+l+"】");
						}
						TLtnCustomerConsumptonAmountExample tccaexample = new TLtnCustomerConsumptonAmountExample();
						tccaexample.createCriteria().andCustomerIdEqualTo(bean.getId());
						List<TLtnCustomerConsumptonAmount> listssa = consumptonAmountService.selectByExample(tccaexample);
						if(listssa.size()>0) {
							for(TLtnCustomerConsumptonAmount cca : listssa) {
								TProject tProject = projectService.selectByPrimaryKey((long)cca.getProjectId());
								Integer deadline = tProject.getDeadline();
								Date dl = null;
								if(deadline!=null)dl = DateUtil.getDiyDateMonth(DateUtil.getSysCurrentDate(), deadline);
								customerProjectService.insertSelective(new TCustomerPorject(vip.getId(),bean.getId(),cca.getProjectId(),tProject.getProjectType(),tProject.getProjectNums(),tProject.getProjectNums(),dl,user.getUsername(),DateUtil.getSysCurrentDate()));
							}
							logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加客户项目信息成功！添加条数【"+listssa.size()+"】");
						}
					}
				}
			}
			result.put("status", true);
		} catch (Exception e) {
			result.put("status", false);
			result.put("mess", "用户："+bean.getCustomername()+"，手机:"+bean.getPhonenumb()+"，操作失败！请稍后再进行尝试！");
			logger.error("用户："+bean.getCustomername()+"，手机:"+bean.getPhonenumb()+"，操作失败！请稍后再进行尝试！"+e.getMessage(),e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
//		activeManager.sendBack(ActiveMQUtil.getTopicDestination(bean.getCompanyId()+InitProperties.BEFORE_SUBSCRIBE_MSG),"");
		return result;
	}

	@Override
	public Map<String, Object> queryAmountSum(TLtnCustomer bean) {
		if(bean.getCompanyId()==null) {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.H_OPTION)) {
				bean.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		if(bean.getStatus()==null) {
			if(WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.Q_ADMIN)) {
				String statuss = String.join(",","0","1","98","99");
				bean.setStatuss(statuss.split(","));
			}else {
				String statuss = String.join(",","0","1");
				bean.setStatuss(statuss.split(","));
			}
		}else {
			String statuss = String.join(",",bean.getStatus().toString());
			bean.setStatuss(statuss.split(","));
		}
		return customerMapper.queryAmountSum(bean);
	}

	@Override
	public List<Map<String, Object>> queryExportDataList(TLtnCustomer customer) {
		customer.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
		return customerMapper.queryExportDataList(customer);
	}

	@Override
	public Map<String, Object> countConsumptionReport(Integer companyid,String datetype,String starttime, String endtime) {
		Map<String, Object> map = new  HashMap<String, Object>();
		List<String> years = new ArrayList<String>();
		List<Double> camount = new ArrayList<Double>();
		List<Double> famount = new ArrayList<Double>();
		List<Object> chuzhen = new ArrayList<Object>();
		List<Object> fuzhen = new ArrayList<Object>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("datetype", datetype);
		map.put("companyid", SystemUserInfo.getSystemUser().getUser().getCompanyid());
		String companyname = "";
		if(companyid!=null) {
			map.put("companyid", companyid);
			companyname = companyMapper.selectByPrimaryKey(companyid).getName();
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
				companyname = SystemUserInfo.getSystemUser().getCompany().getName();
			}else {
				companyname = "所有门店";
			}
		}
		List<Map<String, Object>> listmaps = customerMapper.countConsumptionReport(map);
		if(listmaps!=null && listmaps.size()>0) {
			for (Map<String, Object> lm : listmaps) {
				years.add(lm.get("datetime").toString());
				camount.add(new BigDecimal(lm.get("c_total_amount").toString()).doubleValue());
				famount.add(new BigDecimal(lm.get("f_total_amount").toString()).doubleValue());
				chuzhen.add(new BigDecimal(lm.get("CHUZHEN").toString()).intValue());
				fuzhen.add(new BigDecimal(lm.get("FUZHEN").toString()).intValue());
			}
		}
		double maxchuzhen = (chuzhen.size()<=0)?0.0:Integer.parseInt(CommonUtils.getArrayMax(chuzhen).toString())*1.25;
		double maxfuzhen = (fuzhen.size()<=0)?0.0:Integer.parseInt(CommonUtils.getArrayMax(fuzhen).toString())*1.25;
		map.clear();
		map.put("years", years);
		map.put("camount", camount);
		map.put("famount", famount);
		map.put("chuzhen", chuzhen);
		map.put("fuzhen", fuzhen);
		map.put("max", Math.round(maxchuzhen>=maxfuzhen?maxchuzhen:maxfuzhen));
		map.put("interval", Math.round((maxchuzhen>=maxfuzhen?maxchuzhen:maxfuzhen)/fuzhen.size()));
		map.put("series_name", companyname+"客户消费统计");
		return map;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveOrUpdateTFMCust(TLtnCustomer bean) throws SchedulerException {
		try {
			TCustomerSubscribe record = new TCustomerSubscribe();
			record.setId(bean.getId());
			record.setStatus(1);
			record.setUpdateTime(DateUtil.getSysCurrentDate());
			record.setUpdateUser(SystemUserInfo.getSystemUser().getUser().getUsername());
			customerSubscribeMapper.updateByPrimaryKeySelective(record);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】更新预约信息为【已接诊】状态！");
			bean.setId(null);
			bean.setUpdatetime(DateUtil.getSysCurrentDate());
			bean.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
			bean.setCreateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			customerMapper.insertSelective(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加客户消费订单信息成功！");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】前台接诊操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
		}
		return true;
	}

	@Override
	public Map<String, Object> queryCountConsumptionReportList(Integer companyid,String datetype, String starttime, String endtime) {
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("datetype", datetype);
		if(companyid!=null) {
			map.put("companyid", companyid);
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		List<Map<String, Object>> listmaps = customerMapper.countConsumptionReport(map);
		if(listmaps==null || listmaps.size()<=0)listmaps = new ArrayList<Map<String,Object>>();
		map.clear();
		map.put("rows",listmaps);
		map.put("total",listmaps.size());
		return map;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean refundCust(int id) throws WorkFlowStateException {
		try {
			TLtnCustomer customer = customerMapper.selectByPrimaryKey(id);
			customer.setStatus(98);
			customerMapper.updateByPrimaryKeySelective(customer);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】客户消费信息退款成功！");
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】消费订单退款操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
		}
		return false;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> orderBack(TLtnCustomer bean) throws WorkFlowStateException {
		Map<String, Object> result= new  HashMap<String, Object>();
		result.put("status", false);
		try {
			TLtnCustomer customer = customerMapper.selectByPrimaryKey(bean.getId());
			customer.setStatus(0);
   			customer.setType(0);
   			TLtnCustomerConsumptonAmountExample example = new TLtnCustomerConsumptonAmountExample();
			example.createCriteria().andCustomerIdEqualTo(bean.getId());
			List<TLtnCustomerConsumptonAmount> ccalists = consumptonAmountService.selectByExample(example);
			for(TLtnCustomerConsumptonAmount cca : ccalists) {
				//删除订单消费库存日志信息
				TConsumptonProjectExample example1 = new TConsumptonProjectExample();
				example1.createCriteria().andConsumptonAmountIdEqualTo(cca.getId());
				consumptonProjectMapper.deleteByExample(example1);
				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】删除订单消费库存日志信息成功！");
				//删除用户套餐信息
				TCustomerPorjectExample cpexample = new TCustomerPorjectExample();
				cpexample.createCriteria().andOrderIdEqualTo(bean.getId()).andProjectIdEqualTo(cca.getProjectId());
				List<TCustomerPorject> list = customerProjectService.selectByExample(cpexample);
				if(list.size()>0) {
					for (TCustomerPorject tCustomerPorject : list) {
						TCustomerProjectLogExample cple = new TCustomerProjectLogExample();
						cple.createCriteria().andCpIdEqualTo(tCustomerPorject.getId());
						customerProjectLogService.deleteByExample(cple);
					}
					logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】删除客户项目信息成功！");
				}
				customerProjectService.deleteByExample(cpexample);
				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】删除客户项目信息成功！");
			}
			customerMapper.updateByPrimaryKeySelective(customer);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】客户消费信息回退成功！");
			result.put("status", true);
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】消费订单回退操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
		}
		return result;
	}

}
