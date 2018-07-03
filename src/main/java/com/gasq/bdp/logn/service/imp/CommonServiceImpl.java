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
		map.put("series_name", companyname+"客户消费统计");
		return map;
	}

	@Override
	public Map<String, Object> queryEmployeeTreatOrderDataDetail(Integer type, Integer companyid, String datetype,
			String starttime, String endtime) {
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
		List<Map<String, Object>> listmaps = companyMapper.countConsumptionReport(map);
		if(listmaps==null || listmaps.size()<=0)listmaps = new ArrayList<Map<String,Object>>();
		map.clear();
		map.put("rows",listmaps);
		map.put("total",listmaps.size());
		return map;
	}

}
