/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.component.MyScheduler;
import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.mapper.TCustomerConsumptonLogMapper;
import com.gasq.bdp.logn.mapper.TCustomerSubscribeMapper;
import com.gasq.bdp.logn.mapper.TLtnCustomerMapper;
import com.gasq.bdp.logn.mapper.TSysTimerScheduleconfigMapper;
import com.gasq.bdp.logn.mapper.TVipCustomerMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCustomerConsumptonLog;
import com.gasq.bdp.logn.model.TCustomerSubscribe;
import com.gasq.bdp.logn.model.TLtnCustomer;
import com.gasq.bdp.logn.model.TLtnCustomerConsumptonAmount;
import com.gasq.bdp.logn.model.TLtnCustomerConsumptonAmountExample;
import com.gasq.bdp.logn.model.TLtnCustomerExample;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TVipCustomer;
import com.gasq.bdp.logn.model.TVipCustomerExample;
import com.gasq.bdp.logn.service.CustomerConsumptonAmountService;
import com.gasq.bdp.logn.service.CustomerService;
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
	@Autowired CustomerConsumptonAmountService consumptonAmountService;
	@Autowired MyScheduler scheduler;
	@Autowired TSysTimerScheduleconfigMapper scheduleconfigMapper;
	@Autowired TCustomerSubscribeMapper customerSubscribeMapper;
	@Autowired TCustomerConsumptonLogMapper customerConsumptonLogMapper;
	@Autowired TVipCustomerMapper vipCustomerMapper;
	
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
		TLtnCustomer customer = customerMapper.selectByPrimaryKey(id);
		customer.setStatus(99);
		customerMapper.updateByPrimaryKeySelective(customer);
		return true;
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
		if(bean.getCompanyId()!=null) {
			bean.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				bean.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		if(bean.getStatus()==null) {
			if(WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.Q_ADMIN)) {
				String statuss = String.join(",","0","1","99");
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
		result.put("rows",list);
		result.put("total",list.size());
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
								result.put("status", false);
								result.put("mess", "用户："+bean.getCustomername()+"，手机:"+bean.getPhonenumb()+"账号余额不足，请充值后再进行操作！");
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
			}
			result.put("status", true);
		} catch (Exception e) {
			result.put("status", false);
			result.put("mess", "用户："+bean.getCustomername()+"，手机:"+bean.getPhonenumb()+"，操作失败！请稍后再进行尝试！");
			logger.info("用户："+bean.getCustomername()+"，手机:"+bean.getPhonenumb()+"，操作失败！请稍后再进行尝试！"+e.getMessage(),e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	@Override
	public Map<String, Object> queryAmountSum(TLtnCustomer bean) {
		bean.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
		return customerMapper.queryAmountSum(bean);
	}

	@Override
	public List<Map<String, Object>> queryExportDataList(TLtnCustomer customer) {
		customer.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
		return customerMapper.queryExportDataList(customer);
	}

	@Override
	public Map<String, Object> countConsumptionReport(String datetype,String starttime, String endtime) {
		Map<String, Object> map = new  HashMap<String, Object>();
		List<String> years = new ArrayList<String>();
		List<Double> camount = new ArrayList<Double>();
		List<Double> famount = new ArrayList<Double>();
//		List<Integer> chufuzhen = new ArrayList<Integer>();
		List<Object> chuzhen = new ArrayList<Object>();
		List<Object> fuzhen = new ArrayList<Object>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("datetype", datetype);
		map.put("companyid", SystemUserInfo.getSystemUser().getUser().getCompanyid());
		List<Map<String, Object>> listmaps = customerMapper.countConsumptionReport(map);
		if(listmaps!=null && listmaps.size()>0) {
			for (Map<String, Object> lm : listmaps) {
				years.add(lm.get("datetime").toString());
				camount.add(new BigDecimal(lm.get("c_total_amount").toString()).doubleValue());
				famount.add(new BigDecimal(lm.get("f_total_amount").toString()).doubleValue());
				chuzhen.add(new BigDecimal(lm.get("CHUZHEN").toString()).intValue());
				fuzhen.add(new BigDecimal(lm.get("FUZHEN").toString()).intValue());
//				chufuzhen.add(Integer.parseInt(lm.get("chu_fu_zhen").toString()));
			}
		}
		double maxchuzhen = Integer.parseInt(CommonUtils.getArrayMax(chuzhen).toString())*1.25;
		double maxfuzhen = Integer.parseInt(CommonUtils.getArrayMax(fuzhen).toString())*1.25;
		map.clear();
		map.put("years", years);
		map.put("camount", camount);
		map.put("famount", famount);
		map.put("chuzhen", chuzhen);
		map.put("fuzhen", fuzhen);
//		map.put("chufuzhen", chufuzhen);
		map.put("max", Math.round(maxchuzhen>=maxfuzhen?maxchuzhen:maxfuzhen));
		map.put("interval", Math.round((maxchuzhen>=maxfuzhen?maxchuzhen:maxfuzhen)/fuzhen.size()));
		map.put("series_name", "客户消费统计");
		return map;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveOrUpdateTFMCust(TLtnCustomer bean) throws SchedulerException {
		try {
			TCustomerSubscribe record = new TCustomerSubscribe();
			record.setId(bean.getId());
			record.setStatus(1);
			customerSubscribeMapper.updateByPrimaryKeySelective(record);
			bean.setId(null);
			bean.setUpdatetime(DateUtil.getSysCurrentDate());
			bean.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
			if(bean.getId()!=null) {
				bean.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
				customerMapper.updateByPrimaryKeySelective(bean);
				bean = customerMapper.selectByPrimaryKey(bean.getId());
			}else {
				bean.setCreateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
				bean.setCreatetime(DateUtil.getSysCurrentDate());
				customerMapper.insertSelective(bean);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;
	}

	@Override
	public Map<String, Object> queryCountConsumptionReportList(String datetype, String starttime, String endtime) {
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("datetype", datetype);
		if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
			map.put("companyid", SystemUserInfo.getSystemUser().getUser().getCompanyid());
		}
		List<Map<String, Object>> listmaps = customerMapper.countConsumptionReport(map);
		if(listmaps==null || listmaps.size()<=0)listmaps = new ArrayList<Map<String,Object>>();
		map.clear();
		map.put("rows",listmaps);
		map.put("total",listmaps.size());
		return map;
	}

}