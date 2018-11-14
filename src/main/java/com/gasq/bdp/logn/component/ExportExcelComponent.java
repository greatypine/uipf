/**
 * 
 */
package com.gasq.bdp.logn.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gasq.bdp.logn.service.TCompanyService;

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
	@Autowired TCompanyService companyService;
    
	/**
	 * 导出客户信息
	 */
//	@RequestMapping(value="/exportDataList")
//	public void exportRMDataList(@Valid ParamsObject customer, BindingResult bindingResult,HttpServletRequest request, HttpServletResponse response){
//		logger.info("......开始导出数据.....");
//		List<Map<String,Object>> custlist = customerService.queryExportDataList(customer);
//		try {
//			CommonUtils.ExportExcel("客户消费信息", custlist, response);
//			logger.info("......导出客户消费信息数据成功.....");
//		} catch (WorkFlowJobException e) {
//			logger.info("......导出客户消费信息数据失败.....错误信息:\n"+e.getMessage(),e);
//		}
//	}
	
}
