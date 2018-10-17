/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.mapper.TCompanyMapper;
import com.gasq.bdp.logn.mapper.TWorkforcemanagementMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.model.TWorkforcemanagement;
import com.gasq.bdp.logn.service.CommonService;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Ju_weigang
 * @时间 2018年6月1日下午12:52:18
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述 
 */
@Service
public class CommonServiceImpl implements CommonService {
	@Autowired TCompanyMapper companyMapper;
	@Autowired TWorkforcemanagementMapper workforcemanagementMapper;

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
		if(bean.getQ().equals(" ")) {
			bean.setQ(null);
		}
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
				Object nn = lm.get("nickname");
				if(nn==null) continue;
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
		if(companyid!=null) {
			map.put("companyid", companyid);
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		PageHelper.startPage(page,rows);
		List<Map<String, Object>> listmaps = companyMapper.countConsumptionReport(map);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		map.clear();
		map.put("rows",listmaps);
		map.put("total",pageinfo.getTotal());
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
		if(companyid!=null) {
			map.put("companyid", companyid);
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.H_OPTION)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		PageHelper.startPage(page, rows);
		List<Map<String, Object>> listmaps = companyMapper.countSubscribeReport(map);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		map.clear();
		map.put("rows",listmaps);
		map.put("total",pageinfo.getTotal());
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
		if(companyid!=null) {
			map.put("companyid", companyid);
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		PageHelper.startPage(page, rows);
		List<Map<String, Object>> listmaps = companyMapper.queryCountInventory(map);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		map.clear();
		map.put("rows",listmaps);
		map.put("total",pageinfo.getTotal());
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
		if(companyid!=null) {
			map.put("companyid", companyid);
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}else {
				map.put("companyid", null);
			}
		}
		int total_nums = 0;
		double total_amount_all = 0.0;
		double total_amount_avg_all = 0.0;
		PageHelper.startPage(page, rows);
		List<Map<String, Object>> listmaps = companyMapper.queryCountBusinessAnalysisDataDetail(map);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		if(listmaps==null || listmaps.size()<=0) return null;
		total_nums = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("numbs").toString())).sum();
		total_amount_all = new BigDecimal(listmaps.stream().mapToDouble(f->Double.parseDouble(f.get("total_amount").toString())).sum()).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
		total_amount_avg_all = new BigDecimal(listmaps.stream().mapToDouble(f->Double.parseDouble(f.get("total_amount_avg").toString())).average().getAsDouble()).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
		map.clear();
		List<Map<String,Object>> footer =  new ArrayList<>();
		Map<String,Object> footerp = new HashMap<>();
		footerp.put("datetime", "合计：");
		footerp.put("numbs", total_nums);
		footerp.put("total_amount", total_amount_all);
		footerp.put("total_amount_avg", total_amount_avg_all);
		footer.add(footerp);
		map.clear();
		map.put("footer",footer);
		map.put("rows",listmaps);
		map.put("total",pageinfo.getTotal());
		return map;
	}

	@Override
	public List<Map<String, Object>> queryCountProjectsOrderCost(Integer companyid, String datatype, String starttime,String endtime) {
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("datetype", datatype);
		if(companyid!=null) {
			map.put("companyid", companyid);
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}else {
				map.put("companyid", null);
			}
		}
		List<Map<String, Object>> listmaps = companyMapper.queryCountProjectsOrderCost(map);
		return listmaps;
	}

	@Override
	public List<Map<String, Object>> queryCountProjectsOrderAmountCost(Integer companyid, String datatype,
			String starttime, String endtime) {
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("datetype", datatype);
		if(companyid!=null) {
			map.put("companyid", companyid);
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}else {
				map.put("companyid", null);
			}
		}
		List<Map<String, Object>> listmaps = companyMapper.queryCountProjectsOrderAmountCost(map);
		return listmaps;
	}

	@Override
	public Map<String, Object> queryIndexUserCount() {
		Map<String, Object> map = new  HashMap<String, Object>();
		Integer companyid = SystemUserInfo.getSystemUser().getCompany().getId();
		if(WorkFlowUtil.hasAnyRoles(RoleSign.Q_AREA_SHOPMANAGER)) companyid = null;
		List<Map<String, Object>> listmaps = companyMapper.queryIndexUserCount(companyid);
		List<Map<String, Object>> counsolers = listmaps.stream().filter(f->f.get("rtype").toString().equals("counsoler")).collect(Collectors.toList());
		List<Map<String, Object>> therapeutists = listmaps.stream().filter(f->f.get("rtype").toString().equals("therapeutist")).collect(Collectors.toList());
		map.put("counsoler", counsolers);
		map.put("therapeutist", therapeutists);
		return map;
	}
	
	@Override
	public Map<String, Object> queryIndexCompanyCount() {
		Map<String, Object> map = new  HashMap<String, Object>();
		Integer companyid = SystemUserInfo.getSystemUser().getCompany().getId();
		if(WorkFlowUtil.hasAnyRoles(RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER)) companyid = null;
		String companyname = "";
		if(companyid==null) {
			companyname = "所有门店统计";
		}else {
			TCompany tCompany = companyMapper.selectByPrimaryKey(companyid);
			companyname = tCompany.getName()+"统计";
		}
		List<Map<String, Object>> listmaps = companyMapper.queryIndexCompanyCount(companyid);
		if(listmaps!=null) {
			List<String> datetime = listmaps.stream().map(f->f.get("datetime").toString()).collect(Collectors.toList());
			List<Object> total_amounts = listmaps.stream().map(f->Double.parseDouble(f.get("total_amount").toString())).collect(Collectors.toList());
			List<Object> newnumbs = listmaps.stream().map(f->Integer.parseInt(f.get("newnumbs").toString())).collect(Collectors.toList());
			map.put("datetime", datetime);
			map.put("total_amounts", total_amounts);
			map.put("newnumbs", newnumbs);
			double maxnewnumb = (newnumbs.size()<=0)?0.0:Integer.parseInt(CommonUtils.getArrayMax(newnumbs).toString())*1.25;
			double maxtotal_amounts = (total_amounts.size()<=0)?0.0:Double.parseDouble(CommonUtils.getArrayMax(total_amounts).toString())*1.25;
			map.put("maxnewnumb", maxnewnumb);
			map.put("maxtotal_amounts", maxtotal_amounts);
		}
		map.put("title", companyname);
		return map;
	}
	
	@Override
	public Map<String, Object> queryWorkforceList(TWorkforcemanagement bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		Map<String, Object> params= new  HashMap<String, Object>();
		if(bean.getCycle()!=null) {
			params.put("cycle", bean.getCycle());
		}
		if(bean.getCompanyid()!=null) {
			params.put("companyid", bean.getCompanyid());
		}else {
			params.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
		}
		list = workforcemanagementMapper.queryPagingList(params);
		if(list==null) list = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
		if(!list.isEmpty()) {
			LocalDate localDate = LocalDate.now();
			int dayOfMonth = localDate.getDayOfMonth();
			int lengthOfMonth = localDate.lengthOfMonth();
			int sday = 0;
			int eday = 0;
			if(lengthOfMonth-dayOfMonth<=7) {
				sday = lengthOfMonth-7;
				eday = lengthOfMonth;
			}else {
				sday = dayOfMonth;
				eday = dayOfMonth+7;
			}
			for (Map<String, Object> map : list) {
				Map<String, Object> map1 = new HashMap<>();
				Set<Entry<String, Object>> entrySet = map.entrySet();
				for (Entry<String, Object> entry : entrySet) {
					String key = entry.getKey();
					if(key.startsWith("day")) {
						Integer ik = Integer.parseInt(key.replace("day", ""));
						if(ik>=sday && ik<=eday) {
							map1.put(entry.getKey(), entry.getValue());
						}
					}else {
						map1.put(entry.getKey(), entry.getValue());
					}
				}
				list1.add(map1);
			}
		}
		result.put("rows",list1);
		result.put("total",0);
		return result;
	}

	@Override
	public Map<String, Object> queryProjectTypeReport(Integer companyid, String starttime, String endtime) {
		Map<String, Object> map = new  HashMap<String, Object>();
//		Integer companyid = SystemUserInfo.getSystemUser().getCompany().getId();
		if(WorkFlowUtil.hasAnyRoles(RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER)) companyid = null;
		String companyname = "";
		if(companyid==null) {
			companyname = "所有门店统计";
		}else {
			TCompany tCompany = companyMapper.selectByPrimaryKey(companyid);
			companyname = tCompany.getName()+"统计";
		}
		map.put("companyid", companyid);
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		List<Map<String,Object>> lists = companyMapper.queryProjectTypeReport(map);
		map.clear();
		if(lists!=null) {
			List<String> names = lists.stream().map(f->f.get("name").toString()).collect(Collectors.toList());
			List<Object> procounts = lists.stream().map(f->Integer.parseInt(f.get("procounts").toString())).collect(Collectors.toList());
			map.put("xAxis", names!=null?names:null);
			map.put("data", procounts!=null?procounts:null);
		}
		map.put("titile", companyname);
		return map;
	}

	@Override
	public Map<String, Object> queryProjectTypeChangeReport(Integer companyid, String starttime, String endtime) {
		Map<String, Object> map = new  HashMap<String, Object>();
		if(companyid!=null) {
			map.put("companyid", companyid);
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		map.put("starttime", starttime);
		map.put("endtime", endtime==null?DateUtil.getCurrentDate():endtime);
		PageHelper.startPage(1, 1);
		List<Map<String, Object>> listmaps = companyMapper.queryProjectTypeChangeReport(map);
		map.clear();
		if(listmaps.size()<=0) {
			Map<String, Object> map1 = new  HashMap<String, Object>();
			map1.put("p1", 0);
			map1.put("p2", 0);
			map1.put("p3", 0);
			map1.put("p4", 0);
			map1.put("p5", 0);
			map1.put("p6", 0);
			map1.put("p7", 0);
			listmaps.add(map1);
		}
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		map.put("rows",listmaps);
		map.put("total",pageinfo.getTotal());
		return map;
	}
}
