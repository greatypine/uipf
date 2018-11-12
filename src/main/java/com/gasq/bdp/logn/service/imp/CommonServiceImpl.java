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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.mapper.TCompanyMapper;
import com.gasq.bdp.logn.mapper.TTherapistTreatmentTimeQueryMapper;
import com.gasq.bdp.logn.mapper.TWorkforcemanagementMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.model.TCompanyExample;
import com.gasq.bdp.logn.model.TTherapistTreatmentTimeQuery;
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
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired TCompanyMapper companyMapper;
	@Autowired TWorkforcemanagementMapper workforcemanagementMapper;
	@Autowired TTherapistTreatmentTimeQueryMapper treatmentTimeQueryMapper;
	
	@Override
	public List<Map<String, Object>> queryRootIn(TCompany bean) {
		bean.setId(SystemUserInfo.getSystemUser().getCompany().getId());
		return companyMapper.queryRootIn(bean);
	}

	@Override
	public List<Map<String, Object>> querySex(TCompany bean) {
		bean.setId(SystemUserInfo.getSystemUser().getCompany().getId());
		return companyMapper.querySex(bean);
	}

	@Override
	public List<Map<String, Object>> queryUserStatus(TCompany bean) {
		bean.setId(SystemUserInfo.getSystemUser().getCompany().getId());
		return companyMapper.queryUserStatus(bean);
	}

	@Override
	public List<Map<String, Object>> queryCosmetologist(TCompany bean) {
		bean.setId(SystemUserInfo.getSystemUser().getCompany().getId());
		return companyMapper.queryCosmetologist(bean);
	}

	@Override
	public List<Map<String, Object>> queryCounsoler(TCompany bean) {
		bean.setId(SystemUserInfo.getSystemUser().getCompany().getId());
		return companyMapper.queryCounsoler(bean);
	}

	@Override
	public List<Map<String, Object>> queryProjectInventory(TCompany bean) {
		if(bean.getQ().equals(" ")) {
			bean.setQ(null);
		}
		bean.setId(SystemUserInfo.getSystemUser().getCompany().getId());
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
				if(lengthOfMonth-dayOfMonth==0) {//月末
					handleNextMonthWF(list1, list, dayOfMonth, lengthOfMonth, bean.getCycle(), params,1,6);
//					sday = dayOfMonth;
//					eday = lengthOfMonth;
//					for (Map<String, Object> map : list) {
//						Map<String, Object> map1 = new HashMap<>();
//						Set<Entry<String, Object>> entrySet = map.entrySet();
//						for (Entry<String, Object> entry : entrySet) {
//							String key = entry.getKey();
//							if(key.startsWith("day")) {
//								Integer ik = Integer.parseInt(key.replace("day", ""));
//								if(ik>=sday && ik<=eday) {
//									map1.put(entry.getKey(), entry.getValue());
//								}
//							}else {
//								map1.put(entry.getKey(), entry.getValue());
//							}
//						}
//						list1.add(map1);
//					}
//					
//					String cycle = bean.getCycle();
//					String year = cycle.substring(0, 4);
//					String month = cycle.substring(4,cycle.length());
//					if(Integer.parseInt(month)<12) month = Integer.parseInt(month)+1+"";
//					else {
//						month="01";
//						year = Integer.parseInt(year)+1+"";
//					}
//					String nextdate = year+month;
//					params.put("cycle", nextdate);
//					List<Map<String, Object>> listnextmonth = workforcemanagementMapper.queryPagingList(params);
//					sday = 1;
//					eday = 6;
//					for (Map<String, Object> map : listnextmonth) {
//						String username = map.get("username").toString();
//						int companyid = Integer.parseInt(map.get("companyid").toString());
//						for (Map<String, Object> bmap : list1) {
//							String busername = bmap.get("username").toString();
//							int bcompanyid = Integer.parseInt(bmap.get("companyid").toString());
//							if(busername.equals(username) && bcompanyid==companyid) {
//								Set<Entry<String, Object>> entrySet = map.entrySet();
//								for (Entry<String, Object> entry : entrySet) {
//									String key = entry.getKey();
//									if(key.startsWith("day")) {
//										Integer ik = Integer.parseInt(key.replace("day", ""));
//										if(ik>=sday && ik<=eday) {
//											bmap.put(entry.getKey(), entry.getValue());
//										}
//									}
//								}
//							}
//						}
//					}
				}else if(lengthOfMonth-dayOfMonth==1) {
					handleNextMonthWF(list1, list, dayOfMonth, lengthOfMonth, bean.getCycle(), params,1,5);
				}else if(lengthOfMonth-dayOfMonth==2) {
					handleNextMonthWF(list1, list, dayOfMonth, lengthOfMonth, bean.getCycle(), params,1,4);
				}else if(lengthOfMonth-dayOfMonth==3) {
					handleNextMonthWF(list1, list, dayOfMonth, lengthOfMonth, bean.getCycle(), params,1,3);
				}else if(lengthOfMonth-dayOfMonth==4) {
					handleNextMonthWF(list1, list, dayOfMonth, lengthOfMonth, bean.getCycle(), params,1,2);
				}else if(lengthOfMonth-dayOfMonth==5) {
					handleNextMonthWF(list1, list, dayOfMonth, lengthOfMonth, bean.getCycle(), params,1,1);
				}else if(lengthOfMonth-dayOfMonth==6) {
					handleNextMonthWF(list1, list, dayOfMonth, lengthOfMonth, bean.getCycle(), params,1,0);
				}
			}else {
				sday = dayOfMonth;
				eday = dayOfMonth+7;
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
		}
		result.put("rows",list1);
		result.put("total",0);
		return result;
	}
	
	private void handleNextMonthWF(List<Map<String, Object>> list1,List<Map<String, Object>> list,int dayOfMonth,int lengthOfMonth,String cycle,Map<String,Object> params,int nextday,int beforeday) {
		int sday = dayOfMonth;
		int eday = lengthOfMonth;
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
		String year = cycle.substring(0, 4);
		String month = cycle.substring(4,cycle.length());
		if(Integer.parseInt(month)<12) month = Integer.parseInt(month)+1+"";
		else {
			month="01";
			year = Integer.parseInt(year)+1+"";
		}
		String nextdate = year+month;
		params.put("cycle", nextdate);
		List<Map<String, Object>> listnextmonth = workforcemanagementMapper.queryPagingList(params);
		sday = nextday;
		eday = beforeday;
		for (Map<String, Object> map : listnextmonth) {
			String username = map.get("username").toString();
			int companyid = Integer.parseInt(map.get("companyid").toString());
			for (Map<String, Object> bmap : list1) {
				String busername = bmap.get("username").toString();
				int bcompanyid = Integer.parseInt(bmap.get("companyid").toString());
				if(busername.equals(username) && bcompanyid==companyid) {
					Set<Entry<String, Object>> entrySet = map.entrySet();
					for (Entry<String, Object> entry : entrySet) {
						String key = entry.getKey();
						if(key.startsWith("day")) {
							Integer ik = Integer.parseInt(key.replace("day", ""));
							if(ik>=sday && ik<=eday) {
								bmap.put(entry.getKey(), entry.getValue());
							}
						}
					}
				}
			}
		}
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
	@Override
	public Map<String, Object> queryStoreReport(Integer companyid,String endtime, Integer page,Integer rows) {
		Map<String, Object> map = new  HashMap<String, Object>();
		String[] ets = endtime.split("-");
		String starttime = ets[0]+"-"+ets[1]+"-01";
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		if(companyid!=null) {
			map.put("companyid", companyid);
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				map.put("companyid",SystemUserInfo.getSystemUser().getCompany().getId());
			}else {
				map.put("companyid", null);
			}
		}
		PageHelper.startPage(page, rows);
		List<Map<String, Object>> listmaps = companyMapper.queryStoreReport(map);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		if(listmaps==null || listmaps.size()<=0) return null;
		int t_CHUZHEN_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("t_CHUZHEN")==null?"0":f.get("t_CHUZHEN").toString())).sum();
		int t_liushi_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("t_LIUSHI")==null?"0":f.get("t_LIUSHI").toString())).sum();
		int t_FUZHEN_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("t_FUZHEN")==null?"0":f.get("t_FUZHEN").toString())).sum();
		double t_c_total_amount_count = new BigDecimal(listmaps.stream().mapToDouble(f->Double.parseDouble(f.get("t_c_total_amount")==null?"0":f.get("t_c_total_amount").toString())).sum()).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
		double t_f_total_amount_count = new BigDecimal(listmaps.stream().mapToDouble(f->Double.parseDouble(f.get("t_f_total_amount")==null?"0":f.get("t_f_total_amount").toString())).sum()).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
		double t_total_amount_count = new BigDecimal(listmaps.stream().mapToDouble(f->Double.parseDouble(f.get("t_total_amount")==null?"0":f.get("t_total_amount").toString())).sum()).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
		
		int y_CHUZHEN_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("y_CHUZHEN")==null?"0":f.get("y_CHUZHEN").toString())).sum();
		int y_LIUSHI_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("y_LIUSHI")==null?"0":f.get("y_LIUSHI").toString())).sum();
		int y_FUZHEN_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("y_FUZHEN")==null?"0":f.get("y_FUZHEN").toString())).sum();
		double y_c_total_amount_count = new BigDecimal(listmaps.stream().mapToDouble(f->Double.parseDouble(f.get("y_c_total_amount")==null?"0":f.get("y_c_total_amount").toString())).sum()).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
		double y_f_total_amount_count = new BigDecimal(listmaps.stream().mapToDouble(f->Double.parseDouble(f.get("y_f_total_amount")==null?"0":f.get("y_f_total_amount").toString())).sum()).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
		double y_total_amount_count = new BigDecimal(listmaps.stream().mapToDouble(f->Double.parseDouble(f.get("y_total_amount")==null?"0":f.get("y_total_amount").toString())).sum()).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
		double y_avg_total_amount = new BigDecimal(listmaps.stream().mapToDouble(f->Double.parseDouble(f.get("y_avg_total_amount")==null?"0":f.get("y_avg_total_amount").toString())).sum()).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
		
		int ziranlaikequdao_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("ziranlaikequdao")==null?"0":f.get("ziranlaikequdao").toString())).sum();
		int laogukejieshao_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("laogukejieshao")==null?"0":f.get("laogukejieshao").toString())).sum();
		int meituan_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("meituan")==null?"0":f.get("meituan").toString())).sum();
		int dazhong_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("dazhong")==null?"0":f.get("dazhong").toString())).sum();
		int douyin_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("douyin")==null?"0":f.get("douyin").toString())).sum();
		int xinyang_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("xinyang")==null?"0":f.get("xinyang").toString())).sum();
		int xinlangweibo_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("xinlangweibo")==null?"0":f.get("xinlangweibo").toString())).sum();
		int pengyoujieshao_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("pengyoujieshao")==null?"0":f.get("pengyoujieshao").toString())).sum();
		int luguo_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("luguo")==null?"0":f.get("luguo").toString())).sum();
		int zhuandian_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("zhuandian")==null?"0":f.get("zhuandian").toString())).sum();
		int other_count = listmaps.stream().mapToInt(f->Integer.parseInt(f.get("other")==null?"0":f.get("other").toString())).sum();
		map.clear();
		List<Map<String,Object>> footer =  new ArrayList<>();
		Map<String,Object> footerp = new HashMap<>();
		footerp.put("companyName", "合计：");
		footerp.put("t_CHUZHEN", t_CHUZHEN_count);
		footerp.put("t_LIUSHI", t_liushi_count);
		footerp.put("t_FUZHEN", t_FUZHEN_count);
		footerp.put("t_c_total_amount", t_c_total_amount_count);
		footerp.put("t_f_total_amount", t_f_total_amount_count);
		footerp.put("t_total_amount", t_total_amount_count);
		footerp.put("y_CHUZHEN", y_CHUZHEN_count);
		footerp.put("y_LIUSHI", y_LIUSHI_count);
		footerp.put("y_FUZHEN", y_FUZHEN_count);
		footerp.put("y_c_total_amount", y_c_total_amount_count);
		footerp.put("y_f_total_amount", y_f_total_amount_count);
		footerp.put("y_total_amount", y_total_amount_count);
		footerp.put("y_avg_total_amount", y_avg_total_amount);
		footerp.put("ziranlaikequdao", ziranlaikequdao_count);
		footerp.put("laogukejieshao", laogukejieshao_count);
		footerp.put("meituan", meituan_count);
		footerp.put("dazhong", dazhong_count);
		footerp.put("douyin", douyin_count);
		footerp.put("xinyang", xinyang_count);
		footerp.put("xinlangweibo", xinlangweibo_count);
		footerp.put("pengyoujieshao", pengyoujieshao_count);
		footerp.put("luguo", luguo_count);
		footerp.put("zhuandian", zhuandian_count);
		footerp.put("other", other_count);
		footer.add(footerp);
		map.clear();
		map.put("footer",footer);
		map.put("rows",listmaps);
		map.put("total",pageinfo.getTotal());
		return map;
	}
	@Override
	public void doCreateTherapistTreatmentTime() {
    	logger.info("定时器【预约订单关闭】后台开始运行........................");
    	try {
    		TCompanyExample cmpexample = new TCompanyExample();
    		cmpexample.createCriteria().andStatusEqualTo(true);
    		List<TCompany> complist = companyMapper.selectByExample(cmpexample);
    		int day = 30;
    		if(complist.size()>0) {
    			for (TCompany tCompany : complist) {
    				String cycleMax = treatmentTimeQueryMapper.selectTTQCycleMax(tCompany.getId());
    				long diffday = 0;
    				if(StringUtils.isBlank(cycleMax)) {
    					diffday = 0;
    				}else {
    					diffday = DateUtil.datesub(DateUtil.parseStr(cycleMax, DateUtil.DATE_DEFAULT_FORMAT), DateUtil.getSysCurrentDate());
    				}
    				if(diffday-day>0) continue;
    				tCompany.setViewname("v_cosmetologist");
    				tCompany.setSortName("sort");
					List<Map<String, Object>> vcs = this.getView(tCompany);
					if(vcs.size()>=0) {
						List<TTherapistTreatmentTimeQuery> tttqs = new ArrayList<TTherapistTreatmentTimeQuery>();
						for (Map<String, Object> map : vcs) {
							String nickname = map.get("nickname").toString();
//    							Integer userid = Integer.parseInt(map.get("id").toString());
							Boolean disabled = Boolean.parseBoolean(map.get("disabled").toString());
							if(!disabled) {
								for (int i = 0; i <= day; i++) {
									TTherapistTreatmentTimeQuery tttq = new TTherapistTreatmentTimeQuery();
									tttq.setCompanyid(tCompany.getId());
									tttq.setCycle(DateUtil.dateToString(DateUtil.getDiyDateTime(DateUtil.getSysCurrentDate(), i)));
									tttq.setUsername(nickname);
									tttqs.add(tttq);
								}
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
    }
}
