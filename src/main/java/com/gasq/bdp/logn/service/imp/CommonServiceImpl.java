/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.mapper.TCompanyMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.service.CommonService;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

/**
 * @author Ju_weigang
 * @时间 2018年6月1日下午12:52:18
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述 
 */
@Service
public class CommonServiceImpl implements CommonService {
	@Autowired TCompanyMapper companyMapper;

	@Override
	public List<Map<String, Object>> queryRootIn(TCompany bean) {
		return companyMapper.queryRootIn(bean);
	}

	@Override
	public List<Map<String, Object>> querySex(TCompany bean) {
		return companyMapper.querySex(bean);
	}

	@Override
	public List<Map<String, Object>> queryUserStatus(TCompany bean) {
		return companyMapper.queryUserStatus(bean);
	}

	@Override
	public List<Map<String, Object>> queryCosmetologist(TCompany bean) {
		return companyMapper.queryCosmetologist(bean);
	}

	@Override
	public List<Map<String, Object>> queryCounsoler(TCompany bean) {
		return companyMapper.queryCounsoler(bean);
	}

	@Override
	public List<Map<String, Object>> queryProjectInventory(TCompany bean) {
		return companyMapper.queryProjectInventory(bean);
	}

	@Override
	public List<Map<String, Object>> getView(TCompany bean) {
		return companyMapper.getView(bean);
	}

	@Override
	public Map<String, Object> queryEmployeeTreatOrderReport(Integer type, Integer companyid, String datetype,String starttime, String endtime) {
		Map<String, Object> map = new  HashMap<String, Object>();
		List<String> years = new ArrayList<String>();
		List<String> nickname = new ArrayList<String>();
		List<Double> camount = new ArrayList<Double>();
		List<Double> famount = new ArrayList<Double>();
		List<Object> total_amont = new ArrayList<Object>();
		List<Object> employeeTotalOrder = new ArrayList<Object>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("datetype", datetype);
		map.put("type", type);
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
		List<Map<String, Object>> listmaps = companyMapper.countConsumptionReport(map);
		if(listmaps!=null && listmaps.size()>0) {
			for (Map<String, Object> lm : listmaps) {
				years.add(lm.get("datetime").toString());
				nickname.add(lm.get("nickname").toString());
				camount.add(new BigDecimal(lm.get("c_total_amount").toString()).doubleValue());
				famount.add(new BigDecimal(lm.get("f_total_amount").toString()).doubleValue());
				employeeTotalOrder.add(new BigDecimal(lm.get("employeeTotalOrder").toString()).intValue());
				total_amont.add(new BigDecimal(lm.get("total_amont").toString()).doubleValue());
			}
		}
		double maxchuzhen = (employeeTotalOrder.size()<=0)?0.0:Integer.parseInt(CommonUtils.getArrayMax(employeeTotalOrder).toString())*1.25;
		double maxfuzhen = (total_amont.size()<=0)?0.0:Double.parseDouble(CommonUtils.getArrayMax(total_amont).toString())*1.25;
		map.clear();
		map.put("years", years);
		map.put("nickname", nickname);
		map.put("camount", camount);
		map.put("famount", famount);
		map.put("employeeTotalOrder", employeeTotalOrder);
		map.put("total_amont", total_amont);
		map.put("max", Math.round(maxchuzhen));
		map.put("interval", Math.round(maxfuzhen));
		map.put("series_name", companyname+"员工接诊统计");
		return map;
	}

	@Override
	public Map<String, Object> queryEmployeeTreatOrderDataDetail(Integer type, Integer companyid, String datetype,
			String starttime, String endtime,Integer page,Integer rows) {
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("datetype", datetype);
		map.put("type", type);
		int start = 0;
		int intPage = (page==0) ? 1 : page;
		int number = (rows==0) ? 10 : rows;
		start = (intPage - 1) * number;
		map.put("index", start);
		map.put("pageSize", number);
		if(companyid!=null) {
			map.put("companyid", companyid);
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		List<Map<String, Object>> listmaps = companyMapper.countConsumptionReport(map);
		if(listmaps==null || listmaps.size()<=0)listmaps = new ArrayList<Map<String,Object>>();
		Integer count = companyMapper.countConsumptionReportByBean(map);
		map.clear();
		map.put("rows",listmaps);
		map.put("total",count);
		return map;
	}

	@Override
	public Map<String, Object> queryBackEmployeeOrderDataDetail(Integer type, Integer companyid, String datetype,
			String starttime, String endtime,Integer page,Integer rows) {
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("datetype", datetype);
		map.put("type", type);
		int start = 0;
		int intPage = (page==0) ? 1 : page;
		int number = (rows==0) ? 10 : rows;
		start = (intPage - 1) * number;
		map.put("index", start);
		map.put("pageSize", number);
		if(companyid!=null) {
			map.put("companyid", companyid);
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.H_OPTION)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		List<Map<String, Object>> listmaps = companyMapper.countSubscribeReport(map);
		if(listmaps==null || listmaps.size()<=0)listmaps = new ArrayList<Map<String,Object>>();
		Integer count = companyMapper.countSubscribeReportByBean(map);
		map.clear();
		map.put("rows",listmaps);
		map.put("total",count);
		return map;
	}
	@Override
	public Map<String, Object> queryBackEmployeeOrderReport(Integer type, Integer companyid, String datetype,
			String starttime, String endtime) {
		Map<String, Object> map = new  HashMap<String, Object>();
		List<String> years = new ArrayList<String>();
		List<String> nickname = new ArrayList<String>();
		List<Object> totalOrder = new ArrayList<Object>();
		List<Integer> secuessOrder = new ArrayList<Integer>();
		List<Integer> wastageOrder = new ArrayList<Integer>();
		List<Integer> unfinishedOrder = new ArrayList<Integer>();
		List<Integer> actual_subscribe_enable_arrive = new ArrayList<Integer>();
		List<Integer> actual_jiezhen = new ArrayList<Integer>();
		List<Integer> all_totalOrder = new ArrayList<Integer>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("datetype", datetype);
		map.put("type", type);
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
		List<Map<String, Object>> listmaps = companyMapper.countSubscribeReport(map);
		if(listmaps!=null && listmaps.size()>0) {
			for (Map<String, Object> lm : listmaps) {
				years.add(lm.get("datetime").toString());
				nickname.add(lm.get("nickname").toString());
				totalOrder.add(new BigDecimal(lm.get("totalOrder").toString()).intValue());
				secuessOrder.add(new BigDecimal(lm.get("secuessOrder").toString()).intValue());
				wastageOrder.add(new BigDecimal(lm.get("wastageOrder").toString()).intValue());
				unfinishedOrder.add(new BigDecimal(lm.get("unfinishedOrder").toString()).intValue());
				actual_subscribe_enable_arrive.add(new BigDecimal(lm.get("actual_subscribe_enable_arrive").toString()).intValue());
				actual_jiezhen.add(new BigDecimal(lm.get("actual_jiezhen").toString()).intValue());
				all_totalOrder.add(new BigDecimal(lm.get("all_totalOrder").toString()).intValue());
			}
		}
		double max = (totalOrder.size()<=0)?0.0:Integer.parseInt(CommonUtils.getArrayMax(totalOrder).toString())*1.25;
		map.clear();
		map.put("years", years);
		map.put("nickname", nickname);
		map.put("totalOrder", totalOrder);
		map.put("secuessOrder", secuessOrder);
		map.put("wastageOrder", wastageOrder);
		map.put("unfinishedOrder", unfinishedOrder);
		map.put("actual_subscribe_enable_arrive", actual_subscribe_enable_arrive);
		map.put("actual_jiezhen", actual_jiezhen);
		map.put("all_totalOrder", all_totalOrder);
		map.put("max", Math.round(max));
		map.put("interval", Math.round(max));
		map.put("series_name", companyname+"员工预约统计");
		return map;
	}

	@Override
	public Map<String, Object> queryCountInventory(Integer companyid, String datetype, Integer year, Integer month,Integer page,Integer rows) {
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("year", year);
		if(month!=null) map.put("month", (month>0&&month<10)?"0"+month:month);
		map.put("datetype", datetype);
		int start = 0;
		int intPage = (page==0) ? 1 : page;
		int number = (rows==0) ? 10 : rows;
		start = (intPage - 1) * number;
		map.put("index", start);
		map.put("pageSize", number);
		if(companyid!=null) {
			map.put("companyid", companyid);
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		List<Map<String, Object>> listmaps = companyMapper.queryCountInventory(map);
		if(listmaps==null || listmaps.size()<=0)listmaps = new ArrayList<Map<String,Object>>();
		Integer count = companyMapper.countByBean(map);
		map.clear();
		map.put("rows",listmaps);
		map.put("total",count);
		return map;
	}
	
	@Override
	public Map<String, Object> queryCountInventoryPie(Integer companyid, String datetype, Integer year, Integer month) {
		Map<String, Object> map = new  HashMap<String, Object>();
		List<String> productName = new ArrayList<String>();
		List<Double> totalInputMonery = new ArrayList<Double>();
		map.put("year", year);
		if(month!=null) map.put("month", (month>0&&month<10)?"0"+month:month);
		map.put("datetype", datetype);
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
		List<Map<String, Object>> listmaps = companyMapper.queryCountInventoryPie(map);
		if(listmaps!=null && listmaps.size()>0) {
			for (Map<String, Object> lm : listmaps) {
				productName.add(lm.get("name").toString());
				totalInputMonery.add(new BigDecimal(lm.get("value").toString()).doubleValue());	
			}
		}
		double max = (totalInputMonery.size()<=0)?0.0:totalInputMonery.get(0)*1.25;
		map.clear();
		map.put("productName", productName);
		map.put("data", listmaps);
		map.put("max", Math.round(max));
		map.put("interval", Math.round(max));
		map.put("series_name", companyname+"消费金额占比");
		return map;
	}

	@Override
	public Map<String, Object> queryCountBusinessAnalysisDataDetail(Integer type, Integer companyid, String datetype,
			String starttime, String endtime,Integer page,Integer rows) {
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("datetype", datetype);
		map.put("type", type);
		int start = 0;
		int intPage = (page==0) ? 1 : page;
		int number = (rows==0) ? 10 : rows;
		start = (intPage - 1) * number;
		map.put("index", start);
		map.put("pageSize", number);
		if(companyid!=null) {
			map.put("companyid", companyid);
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		int total_nums = 0;
		double total_amount_all = 0.0;
		double total_amount_avg_all = 0.0;
		List<Map<String, Object>> listmaps = companyMapper.queryCountBusinessAnalysisDataDetail(map);
		if(listmaps==null || listmaps.size()<=0)listmaps = new ArrayList<Map<String,Object>>();
		total_nums = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("numbs").toString())).sum();
		total_amount_all = listmaps.stream().mapToDouble(f->Double.parseDouble(f.get("total_amount").toString())).sum();
		total_amount_avg_all = listmaps.stream().mapToDouble(f->Double.parseDouble(f.get("total_amount_avg").toString())).sum();
		Integer count = companyMapper.countBusinessAnalysisDataDetailByBean(map);
		List<Map<String,Object>> footer =  new ArrayList<>();
		Map<String,Object> footerp = new HashMap<>();
		footerp.put("datetime", "合计：");
		footerp.put("numbs", total_nums);
		footerp.put("total_amount", total_amount_all);
		footerp.put("total_amount_avg", total_amount_avg_all);
		footer.add(footerp);
		map.clear();
		map.put("rows",listmaps);
		map.put("total",count);
		map.put("footer",footer);
		return map;
	}
}
