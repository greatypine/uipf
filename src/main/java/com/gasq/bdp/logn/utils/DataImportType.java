/**
 * 
 */
package com.gasq.bdp.logn.utils;

import com.gasq.bdp.logn.iexception.InterfaceTypeException;
import com.gasq.bdp.logn.service.DataImport;
import com.gasq.bdp.logn.service.imp.CVSImport;
import com.gasq.bdp.logn.service.imp.ExcelImport;
import com.gasq.bdp.logn.service.imp.JsonImport;
import com.gasq.bdp.logn.service.imp.TxtImport;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.utils
 * @creatTime 2017年12月20日上午10:30:15
 * @remark 
 */
public enum DataImportType {

	Excel(1),Json(2),Txt(3),Cvs(4);
	
	private final int value;
	
    private DataImportType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    public static String getValueName(int code) {
    	String result = null;
    	switch (code) {
		case 1:
			result = "excel导入";
			break;
		case 2:
			result = "json导入";
			break;
		case 3:
			result = "txt导入";
			break;
		case 4:
			result = "cvs导入";
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
    public DataImport getDataImportTypeService() {
    	DataImport c = null;
    	switch(value) {
	    	case 1: c = SpringApplicationUtils.getBean(ExcelImport.class);
	    	break;
	    	case 2: c = SpringApplicationUtils.getBean(JsonImport.class);
	    	break;
	    	case 3: c = SpringApplicationUtils.getBean(TxtImport.class);
	    	break;
	    	case 4: c = SpringApplicationUtils.getBean(CVSImport.class);
	    	break;
		}
    	return c;
    }
    
    public static DataImportType valueOf(int v) throws InterfaceTypeException {
    	switch(v) {
    		case 1: return Excel;
	    	case 2: return Json;
	    	case 3: return Txt;
	    	case 4: return Cvs;
    		default : throw new InterfaceTypeException(" is wrong InterfaceType:'"+v+"'! ");
    	}
    }
}
