/**
 * 
 */
package com.gasq.bdp.logn.component;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.TLtnCustomer;
import com.gasq.bdp.logn.service.CustomerService;
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
    
	@RequestMapping(value="/exportDataList")
	public void importRMDataList(@Valid TLtnCustomer customer, BindingResult bindingResult,HttpServletRequest request, HttpServletResponse response){
		logger.info("......开始导出数据.....");
		List<Map<String,Object>> custlist = customerService.queryExportDataList(customer);
		try {
			CommonUtils.ExportExcel("客户消费信息", custlist, response);
			logger.info("......导出客户消费信息数据成功.....");
		} catch (WorkFlowJobException e) {
			logger.info("......导出客户消费信息数据失败.....错误信息:\n"+e.getMessage(),e);
		}
	}
}
