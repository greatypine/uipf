/**
 * 
 */
package com.gasq.bdp.logn.utils;

import com.gasq.bdp.logn.iexception.InterfaceTypeException;
import com.gasq.bdp.logn.service.DataExport;
import com.gasq.bdp.logn.service.imp.CSVExport;
import com.gasq.bdp.logn.service.imp.ExcelExport;
import com.gasq.bdp.logn.service.imp.JsonExport;
import com.gasq.bdp.logn.service.imp.TxtExport;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.utils
 * @creatTime 2017年12月20日上午10:30:15
 * @remark 
 */
public enum DataExportType {

	Excel(1),Json(2),Txt(3),Csv(4);
	
	private final int value;
	
    private DataExportType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    public static String getValueName(int code) {
    	String result = null;
    	switch (code) {
		case 1:
			result = "excel导出";
			break;
		case 2:
			result = "json导出";
			break;
		case 3:
			result = "txt导出";
			break;
		case 4:
			result = "csv导出";
			break;
		default:
			break;
		}
    	return result;
    }
    public String getInterfaceClass() {
    	return String.valueOf(value/100);
    }
    
    
    /**
     * 获取对应的服务对象
     * @return
     */
    public DataExport getDataExportTypeService() {
    	DataExport c = null;
    	switch(value) {
	    	case 1: c = SpringApplicationUtils.getBean(ExcelExport.class);
	    	break;
	    	case 2: c = SpringApplicationUtils.getBean(JsonExport.class);
	    	break;
	    	case 3: c = SpringApplicationUtils.getBean(TxtExport.class);
	    	break;
	    	case 4: c = SpringApplicationUtils.getBean(CSVExport.class);
	    	break;
		}
    	return c;
    }
    
    public static DataExportType valueOf(int v) throws InterfaceTypeException {
    	switch(v) {
    		case 1: return Excel;
	    	case 2: return Json;
	    	case 3: return Txt;
	    	case 4: return Csv;
    		default : throw new InterfaceTypeException(" is wrong InterfaceType:'"+v+"'! ");
    	}
    }
}
