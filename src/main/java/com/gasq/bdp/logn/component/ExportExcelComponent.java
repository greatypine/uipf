/**
 * 
 */
package com.gasq.bdp.logn.component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.model.TLtnCustomer;
import com.gasq.bdp.logn.model.TWorkforcemanagement;
import com.gasq.bdp.logn.service.CustomerService;
import com.gasq.bdp.logn.service.TCompanyService;
import com.gasq.bdp.logn.service.WorkforceManagementService;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author Ju_weigang
 * @时间 2018年2月9日下午3:57:33
 * @项目路径 com.gasq.bdp.logn.component
 * @描述 
 */
@Component
@RequestMapping(value="/exportExcel")
public class ExportExcelComponent {
	private static final Logger logger =  LoggerFactory.getLogger(ExportExcelComponent.class); 
	@Autowired CustomerService customerService;
	@Autowired WorkforceManagementService workforceManagementService;
	@Autowired TCompanyService companyService;
    
	/**
	 * 导出客户信息
	 */
	@RequestMapping(value="/exportDataList")
	public void exportRMDataList(@Valid TLtnCustomer customer, BindingResult bindingResult,HttpServletRequest request, HttpServletResponse response){
		logger.info("......开始导出数据.....");
		List<Map<String,Object>> custlist = customerService.queryExportDataList(customer);
		try {
			CommonUtils.ExportExcel("客户消费信息", custlist, response);
			logger.info("......导出客户消费信息数据成功.....");
		} catch (WorkFlowJobException e) {
			logger.info("......导出客户消费信息数据失败.....错误信息:\n"+e.getMessage(),e);
		}
	}
	
	/**
	 * 导出排班信息
	 */
	@RequestMapping(value="/exportWorkforceDataList")
	public void exportWorkforceDataList(@Valid TWorkforcemanagement bean, BindingResult bindingResult,HttpServletRequest request, HttpServletResponse response){
		if(bean.getCompanyid()==null) bean.setCompanyid(SystemUserInfo.getSystemUser().getCompany().getId());
		TCompany company = companyService.selectByPrimaryKey(bean.getCompanyid());
		logger.info("......开始导出【"+company.getName()+"->"+bean.getCycle()+"】排班信息数据.....");
		try {
			String cycle = bean.getCycle();
			String sdate = cycle.substring(0, 4)+"-"+cycle.substring(4, cycle.length())+"-01";
			Date date = DateUtil.getEndDayOfMonth(sdate);
			LocalDate localDate = DateUtil.date2LocalDate(date);
			int day = localDate.getDayOfMonth();
			bean.setId(day);
			bean.setDay1(cycle+"01");
			bean.setDay2(cycle+"02");
			bean.setDay3(cycle+"03");
			bean.setDay4(cycle+"04");
			bean.setDay5(cycle+"05");
			bean.setDay6(cycle+"06");
			bean.setDay7(cycle+"07");
			bean.setDay8(cycle+"08");
			bean.setDay9(cycle+"09");
			bean.setDay10(cycle+"10");
			bean.setDay11(cycle+"11");
			bean.setDay12(cycle+"12");
			bean.setDay13(cycle+"13");
			bean.setDay14(cycle+"14");
			bean.setDay15(cycle+"15");
			bean.setDay16(cycle+"16");
			bean.setDay17(cycle+"17");
			bean.setDay18(cycle+"18");
			bean.setDay19(cycle+"19");
			bean.setDay20(cycle+"20");
			bean.setDay21(cycle+"21");
			bean.setDay22(cycle+"22");
			bean.setDay23(cycle+"23");
			bean.setDay24(cycle+"24");
			bean.setDay25(cycle+"25");
			bean.setDay26(cycle+"26");
			bean.setDay27(cycle+"27");
			bean.setDay28(cycle+"28");
			bean.setDay29(cycle+"29");
			bean.setDay30(cycle+"30");
			bean.setDay31(cycle+"31");
			List<Map<String,Object>> custlist = workforceManagementService.queryExportDataList(bean);
			CommonUtils.ExportExcel(company.getName()+"->"+bean.getCycle()+"排班信息", custlist, response);
			logger.info("......导出【"+company.getName()+"->"+bean.getCycle()+"】信息数据成功.....");
		} catch (WorkFlowJobException e) {
			logger.info("......导出排班信息数据失败.....错误信息:\n"+e.getMessage(),e);
		}
	}
}
