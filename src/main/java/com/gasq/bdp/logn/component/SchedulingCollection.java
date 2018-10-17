package com.gasq.bdp.logn.component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gasq.bdp.logn.mapper.TCompanyMapper;
import com.gasq.bdp.logn.mapper.TCustomerSubscribeMapper;
import com.gasq.bdp.logn.mapper.TLtnCustomerMapper;
import com.gasq.bdp.logn.mapper.TSysTimerSwitchMapper;
import com.gasq.bdp.logn.mapper.TSysUserMapper;
import com.gasq.bdp.logn.mapper.TTherapistTreatmentTimeQueryMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.model.TCompanyExample;
import com.gasq.bdp.logn.model.TCustomerSubscribe;
import com.gasq.bdp.logn.model.TCustomerSubscribeExample;
import com.gasq.bdp.logn.model.TLtnCustomer;
import com.gasq.bdp.logn.model.TLtnCustomerExample;
import com.gasq.bdp.logn.model.TSysTimerSwitchExample;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TTherapistTreatmentTimeQuery;
import com.gasq.bdp.logn.service.CommonService;
import com.gasq.bdp.logn.service.EmailManager;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * 定时任务配置类
 */
@Component
public class SchedulingCollection {
	@Autowired EmailManager emailService;
	@Autowired CommonService commonService;
	@Autowired TSysUserMapper userMapper;
	@Autowired TSysTimerSwitchMapper timmerSwitchMapper;
	@Autowired TLtnCustomerMapper customerMapper;
	@Autowired TCustomerSubscribeMapper customerSubscribeMapper;
	@Autowired TCompanyMapper companyMapper;
	@Autowired TTherapistTreatmentTimeQueryMapper treatmentTimeQueryMapper;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
	@Scheduled(cron = "10 50 23 * * ?") // 统计日渠道客户消费情况 10 50 23 * * ?
    public String doCountDayOrderChannelCost() {
    	logger.info("定时器【痘卫士-日客户消费统计】后台开始运行........................");
    	TSysTimerSwitchExample example = new TSysTimerSwitchExample();
    	example.createCriteria().andEkeyEqualTo("doCountDayOrderChannelCost").andStatusEqualTo(true);
    	if(timmerSwitchMapper.countByExample(example)>0) {
    		Map<String,Object> map = new HashMap<>();
    		map.put("roles", RoleSign.M_USER());
    		List<TSysUser> userlist = userMapper.queryQUserEmailAndPhone(map);
    		for (TSysUser tSysUser : userlist) {
    			String mess = getCountDayOrderChannelCostMess(tSysUser,"日客户渠道消费统计","3");
    			String mess2 = getCountProjectsOrderCostMess(tSysUser,"日客户项目消费统计","3");
    			String mess3 = queryCountProjectsOrderAmountCost(tSysUser,"日客户项目消费金额统计","3");
    			if(StringUtil.isBlank(mess)) mess = "";
    			mess += (StringUtil.isBlank(mess2)?"":"</br>"+mess2);
    			mess += (StringUtil.isBlank(mess3)?"":"</br>"+mess3);
    			if(StringUtil.isNotBlank(mess)) emailService.sendHtmlMail(tSysUser.getEmail(), "痘卫士-日客户渠道消费统计",mess);
    		}
    		logger.info("定时器【痘卫士-日客户消费统计】后台运行完毕");
    	}else {
    		logger.info("定时器【痘卫士-日客户消费统计】状态已关闭，后台运行完毕!");
    	}
    	return "";
    }
	
	@Scheduled(cron = "50 54 09 01 * ?") // 统计日渠道客户消费情况 10 10 00 01 * ?
    public String doCountMonthOrderChannelCost() {
    	logger.info("定时器【痘卫士-月客户渠道消费统计】后台开始运行........................");
    	TSysTimerSwitchExample example = new TSysTimerSwitchExample();
    	example.createCriteria().andEkeyEqualTo("doCountMonthOrderChannelCost").andStatusEqualTo(true);
    	if(timmerSwitchMapper.countByExample(example)>0) {
    		Map<String,Object> map = new HashMap<>();
    		map.put("roles", RoleSign.M_USER());
    		List<TSysUser> userlist = userMapper.queryQUserEmailAndPhone(map);
    		for (TSysUser tSysUser : userlist) {
    			String mess = getCountDayOrderChannelCostMess(tSysUser,"月客户渠道消费统计","2");
    			String mess2 = getCountProjectsOrderCostMess(tSysUser,"月客户项目消费次数统计","2");
    			String mess3 = queryCountProjectsOrderAmountCost(tSysUser,"月客户项目消费金额统计","2");
    			if(StringUtil.isBlank(mess)) mess = "";
    			mess += (StringUtil.isBlank(mess2)?"":"</br>"+mess2);
    			mess += (StringUtil.isBlank(mess3)?"":"</br>"+mess3);
    			if(StringUtil.isNotBlank(mess)) emailService.sendHtmlMail(tSysUser.getEmail(), "痘卫士-月客户渠道消费统计",mess);
    		}
    		logger.info("定时器【痘卫士-月客户渠道消费统计】后台运行完毕");
    	}else {
    		logger.info("定时器【痘卫士-月客户渠道消费统计】状态已关闭，后台运行完毕!");
    	}
    	return "";
    }
	
	/**
	 * 统计项目消费次数
	 */
	private String getCountProjectsOrderCostMess(TSysUser tSysUser,String title,String datatype) {
		try {
			StringBuffer sb  = new StringBuffer("<table style='font-family: verdana,arial,sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;width:80%;margin:auto;'>");
			sb.append("<tr style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;background-color: #dedede;'>");
			sb.append("<th colspan='10' style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: rgb(246, 193, 188); text-align:center;'><b>"+title+"</b></th>");
			sb.append("</tr>");
			sb.append("<tr style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;background-color: #dedede;'>");
			sb.append("<th style='text-align:center;'>日期</th>");
			sb.append("<th style='text-align:center;'>门店</th>");
			sb.append("<th style='text-align:center;'>体验</th>");
			sb.append("<th style='text-align:center;'>单次</th>");
			sb.append("<th style='text-align:center;'>疗程</th>");
			sb.append("<th style='text-align:center;'>包治</th>");
			sb.append("<th style='text-align:center;'>祛印</th>");
			sb.append("<th style='text-align:center;'>黑头套</th>");
			sb.append("<th style='text-align:center;'>水动力套</th>");
			sb.append("<th style='text-align:center;'>抑螨套</th>");
			sb.append("</tr>");
			List<Map<String, Object>> resultmaps = null;
			if(datatype.equals("2")) {
				resultmaps = commonService.queryCountProjectsOrderCost(tSysUser.getCompanyid(), datatype,DateUtil.getOtherMonth(-1, DateUtil.DATE_DEFAULT_FORMAT), DateUtil.getDiyStrDateTime(0, DateUtil.DATE_DEFAULT_FORMAT));
			}else if(datatype.equals("3")){
				resultmaps = commonService.queryCountProjectsOrderCost(tSysUser.getCompanyid(), datatype,DateUtil.getDiyStrDateTime(0, DateUtil.DATE_DEFAULT_FORMAT), DateUtil.getDiyStrDateTime(0, DateUtil.DATE_DEFAULT_FORMAT));
			}
			if(resultmaps!=null) {
				for (Map<String, Object> map2 : resultmaps) {
					String companyname = map2.get("companyname").toString();
					String datetime = map2.get("datetime").toString();
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+datetime+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+companyname+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn1").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn2").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn3").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn4").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn5").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn6").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn7").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn8").toString()+"</td>");
					sb.append("</tr>");
				}
				String mess = sb.append("</table>").toString();
	    		return mess;
			}
		} catch (Exception e) {
			logger.info("定时器【痘卫士-客户项目消费统计】后台运行失败........................"+e.getMessage(),e);
		}
		return null;
	}
	/**
	 * 统计项目消费金额
	 */
	private String queryCountProjectsOrderAmountCost(TSysUser tSysUser,String title,String datatype) {
		try {
			StringBuffer sb  = new StringBuffer("<table style='font-family: verdana,arial,sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;width:80%;margin:auto;'>");
			sb.append("<tr style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;background-color: #dedede;'>");
			sb.append("<th colspan='10' style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: rgb(246, 193, 188); text-align:center;'><b>"+title+"</b></th>");
			sb.append("</tr>");
			sb.append("<tr style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;background-color: #dedede;'>");
			sb.append("<th style='text-align:center;'>日期</th>");
			sb.append("<th style='text-align:center;'>门店</th>");
			sb.append("<th style='text-align:center;'>体验</th>");
			sb.append("<th style='text-align:center;'>单次</th>");
			sb.append("<th style='text-align:center;'>疗程</th>");
			sb.append("<th style='text-align:center;'>包治</th>");
			sb.append("<th style='text-align:center;'>祛印</th>");
			sb.append("<th style='text-align:center;'>黑头套</th>");
			sb.append("<th style='text-align:center;'>水动力套</th>");
			sb.append("<th style='text-align:center;'>抑螨套</th>");
			sb.append("</tr>");
			List<Map<String, Object>> resultmaps = null;
			if(datatype.equals("2")) {
				resultmaps = commonService.queryCountProjectsOrderAmountCost(tSysUser.getCompanyid(), datatype,DateUtil.getOtherMonth(-1, DateUtil.DATE_DEFAULT_FORMAT), DateUtil.getDiyStrDateTime(0, DateUtil.DATE_DEFAULT_FORMAT));
			}else if(datatype.equals("3")){
				resultmaps = commonService.queryCountProjectsOrderAmountCost(tSysUser.getCompanyid(), datatype,DateUtil.getDiyStrDateTime(0, DateUtil.DATE_DEFAULT_FORMAT), DateUtil.getDiyStrDateTime(0, DateUtil.DATE_DEFAULT_FORMAT));
			}
			if(resultmaps!=null) {
				for (Map<String, Object> map2 : resultmaps) {
					String companyname = map2.get("companyname").toString();
					String datetime = map2.get("datetime").toString();
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+datetime+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+companyname+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn1").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn2").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn3").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn4").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn5").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn6").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn7").toString()+"</td>");
					sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+map2.get("pn8").toString()+"</td>");
					sb.append("</tr>");
				}
				String mess = sb.append("</table>").toString();
	    		return mess;
			}
		} catch (Exception e) {
			logger.info("定时器【痘卫士-客户项目消费统计】后台运行失败........................"+e.getMessage(),e);
		}
		return null;
	}
	
    @SuppressWarnings("unchecked")
	private String getCountDayOrderChannelCostMess(TSysUser tSysUser,String title,String datatype) {
    	try {
    		StringBuffer sb  = new StringBuffer("<table style='font-family: verdana,arial,sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;width:80%;margin:auto;'>");
    		sb.append("<tr style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;background-color: #dedede;'>");
    		sb.append("<th colspan='7' style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: rgb(246, 193, 188); text-align:center;'><b>"+title+"</b></th>");
    		sb.append("</tr>");
    		sb.append("<tr style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;background-color: #dedede;'>");
    		sb.append("<th style='text-align:center;'>日期</th>");
    		sb.append("<th style='text-align:center;'>预约门店</th>");
    		sb.append("<th style='text-align:center;'>渠道</th>");
    		sb.append("<th style='text-align:center;'>职业</th>");
    		sb.append("<th style='text-align:center;'>就诊人数</th>");
    		sb.append("<th style='text-align:center;'>消费总金额</th>");
    		sb.append("<th style='text-align:center;'>人均消费金额</th>");
    		sb.append("</tr>");
    		Map<String, Object> resultmap = null;
    		if(datatype.equals("2")) {
    			resultmap = commonService.queryCountBusinessAnalysisDataDetail(1, tSysUser.getCompanyid(), datatype,DateUtil.getOtherMonth(-1, DateUtil.DATE_DEFAULT_FORMAT), DateUtil.getDiyStrDateTime(0, DateUtil.DATE_DEFAULT_FORMAT), 0, 0);
    		}else if(datatype.equals("3")){
    			resultmap = commonService.queryCountBusinessAnalysisDataDetail(1, tSysUser.getCompanyid(), datatype,DateUtil.getDiyStrDateTime(0, DateUtil.DATE_DEFAULT_FORMAT), DateUtil.getDiyStrDateTime(0, DateUtil.DATE_DEFAULT_FORMAT), 0, 0);
    		}
    		List<Map<String, Object>> listmaps = (List<Map<String, Object>>) resultmap.get("rows");
    		if(listmaps!=null) {
    			for (Map<String, Object> map2 : listmaps) {
    				sb.append("<tr style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;background-color: #dedede;'>");
    				String datetime = map2.get("datetime").toString();
    				sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+datetime+"</td>");
    				String companyName = map2.get("companyName").toString();
    				sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+companyName+"</td>");
    				String name = (map2.get("name")==null)?"":map2.get("name").toString();
    				sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+name+"</td>");
    				String text = (map2.get("text")==null)?"":map2.get("text").toString();
    				sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+text+"</td>");
    				String numbs = map2.get("numbs").toString();
    				sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+numbs+"</td>");
    				String total_amount = map2.get("total_amount").toString();
    				sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+total_amount+"</td>");
    				String total_amount_avg = map2.get("total_amount_avg").toString();
    				sb.append("<td style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #ffffff; text-align:center;'>"+total_amount_avg+"</td>");
    				sb.append("</tr>");
    			}
    			List<Map<String,Object>> footer = (List<Map<String, Object>>) resultmap.get("footer");
    			int total_nums = Integer.parseInt(footer.get(0).get("numbs").toString());
    			Double total_amount_all = Double.parseDouble(footer.get(0).get("total_amount").toString());
    			Double total_amount_avg_all = Double.parseDouble(footer.get(0).get("total_amount_avg").toString());
    			sb.append("<tr style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;background-color: #dedede;'>");
    			sb.append("<td colspan='7' style='border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #FFFF66; text-align:left;'>合计：总就诊人数【"+total_nums+"】，消费总金额【￥"+total_amount_all+"】， 人均消费金额【￥"+total_amount_avg_all+"】</td>");
    			sb.append("</tr>");
    		}
    		String mess = sb.append("</table>").toString();
    		return mess;
		} catch (Exception e) {
			logger.info("定时器【痘卫士-客户消费统计】后台运行失败........................"+e.getMessage(),e);
		}
    	return null;
    }
    
    @Scheduled(cron = "10 00 00 * * ?") // 定时关闭前台无效订单
    public String doChangeQOrderClose() {
    	logger.info("定时器【前台订单关闭】后台开始运行........................");
    	try {
    		TSysTimerSwitchExample example = new TSysTimerSwitchExample();
        	example.createCriteria().andEkeyEqualTo("doChangeQOrderClose").andStatusEqualTo(true);
        	if(timmerSwitchMapper.countByExample(example)>0) {
        		TLtnCustomer record = new TLtnCustomer();
        		record.setStatus(99);
        		TLtnCustomerExample example1 = new TLtnCustomerExample();
        		example1.createCriteria().andStatusEqualTo(0).andCreatetimeLessThanOrEqualTo(DateUtil.getSysCurrentDate());
        		customerMapper.updateByExampleSelective(record, example1);
        	}
		} catch (Exception e) {
			logger.info("定时器【前台订单关闭】运行失败,错误信息如下："+e.getMessage(),e);
		}
    	logger.info("定时器【前台订单关闭】后台运行完毕");
    	return "";
    }
    @Scheduled(cron = "10 10 01 * * ?") // 定时关闭后台无效订单
    public String doChangeHOrderClose() {
    	logger.info("定时器【预约订单关闭】后台开始运行........................");
    	try {
    		TSysTimerSwitchExample example = new TSysTimerSwitchExample();
        	example.createCriteria().andEkeyEqualTo("doChangeHOrderClose").andStatusEqualTo(true);
        	if(timmerSwitchMapper.countByExample(example)>0) {
        		TCustomerSubscribe record = new TCustomerSubscribe();
        		record.setStatus(99);
        		TCustomerSubscribeExample example1 = new TCustomerSubscribeExample();
        		example1.createCriteria().andStatusEqualTo(0).andSubscribeDateLessThanOrEqualTo(DateUtil.getDiyDateTime(DateUtil.getSysCurrentDate(), -4));
//        		example1.or().andSubscribeDateIsNull().andStatusEqualTo(0);
        		customerSubscribeMapper.updateByExampleSelective(record, example1);
        	}
		} catch (Exception e) {
			logger.info("定时器【预约订单关闭】运行失败,错误信息如下："+e.getMessage(),e);
		}
    	logger.info("定时器【预约订单关闭】后台运行完毕");
    	return "";
    }
    
    @Scheduled(cron = "30 24 12 * * ?") // 每天凌晨生成空的治疗师时间表
    public String doCreateTherapistTreatmentTime() {
    	logger.info("定时器【预约订单关闭】后台开始运行........................");
    	try {
    		TCompanyExample cmpexample = new TCompanyExample();
    		cmpexample.createCriteria().andStatusEqualTo(true);
    		List<TCompany> complist = companyMapper.selectByExample(cmpexample);
    		if(complist.size()>0) {
    			for (TCompany tCompany : complist) {
    				tCompany.setViewname("v_cosmetologist");
    				tCompany.setSortName("sort");
					List<Map<String, Object>> vcs = commonService.getView(tCompany);
					if(vcs.size()>0) {
						List<TTherapistTreatmentTimeQuery> tttqs = new ArrayList<TTherapistTreatmentTimeQuery>();
						for (Map<String, Object> map : vcs) {
							String nickname = map.get("nickname").toString();
//							Integer userid = Integer.parseInt(map.get("id").toString());
							Boolean disabled = Boolean.parseBoolean(map.get("disabled").toString());
							if(!disabled) {
//								for (int i = 0; i < 7; i++) {
									TTherapistTreatmentTimeQuery tttq = new TTherapistTreatmentTimeQuery();
									tttq.setCompanyid(tCompany.getId());
									tttq.setCycle(DateUtil.dateToString(DateUtil.getDiyDateTime(DateUtil.getSysCurrentDate(), 7)));
									tttq.setUsername(nickname);
									tttqs.add(tttq);
//								}
							}
						}
						treatmentTimeQueryMapper.insertBatch(tttqs);
					}
				}
    		}
		} catch (Exception e) {
			logger.info("定时器【预约订单关闭】运行失败,错误信息如下："+e.getMessage(),e);
		}
    	logger.info("定时器【预约订单关闭】后台运行完毕");
    	return "";
    }
}