/**
 * 
 */
package com.gasq.bdp.logn.job;

import com.gasq.bdp.logn.iexception.InterfaceTypeException;
import com.gasq.bdp.logn.state.EndWorkFlow;
import com.gasq.bdp.logn.state.DataExportWorkFlow;
import com.gasq.bdp.logn.state.DataImportWorkFlow;
import com.gasq.bdp.logn.state.SqlInsertOrUpdateWorkFlow;
import com.gasq.bdp.logn.state.SqlOptionWorkFlow;
import com.gasq.bdp.logn.state.SqlQueryOptionWorkFlow;
import com.gasq.bdp.logn.state.StartWorkFlow;
import com.gasq.bdp.logn.state.WorkFlowState;
import com.gasq.bdp.logn.utils.SpringApplicationUtils;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.job
 * @creatTime 2017年9月12日下午5:40:11
 * @remark 
 */
public enum InterfaceType {
	start(0),SqlQuery(1),InsertOrUpdate(7),Sql(8),DataExport(9),DataImport(10),end(99);
	
	private final int value;
	
    private InterfaceType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    public static String getValueName(int code) {
    	String result = null;
    	switch (code) {
    	case 0:
			result = "开始节点";
			break;
		case 1:
			result = "数据库sql查询节点";
			break;
		case 2:
			result = "ElasticSeach节点";
			break;
		case 3:
			result = "脚本节点";
			break;
		case 4:
			result = "hive节点";
			break;
		case 5:
			result = "sqoop节点";
			break;
		case 6:
			result = "sftp节点";
			break;
		case 7:
			result = "InsertOrUpdate节点";
			break;
		case 8:
			result = "Sql节点";
			break;
		case 9:
			result = "数据导出节点";
			break;
		case 10:
			result = "数据导入节点";
			break;
		case 11:
			result = "mongo查询节点";
			break;
		case 99:
			result = "结束节点";
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
    public WorkFlowState getIntfaceService() {
    	WorkFlowState c = null;
    	switch(value) {
    		case 0: c = SpringApplicationUtils.getBean(StartWorkFlow.class);
    		break;
	    	case 1: c = SpringApplicationUtils.getBean(SqlQueryOptionWorkFlow.class);
	    	break;
	    	case 7: c = SpringApplicationUtils.getBean(SqlInsertOrUpdateWorkFlow.class);
	    	break;
	    	case 8: c = SpringApplicationUtils.getBean(SqlOptionWorkFlow.class);
	    	break;
	    	case 9: c = SpringApplicationUtils.getBean(DataExportWorkFlow.class);
	    	break;
	    	case 10: c = SpringApplicationUtils.getBean(DataImportWorkFlow.class);
	    	break;
	    	case 99: c = SpringApplicationUtils.getBean(EndWorkFlow.class);
	    	break;
		}
    	return c;
    }
    
    public static InterfaceType valueOf(int v) throws InterfaceTypeException {
    	switch(v) {
    		case 0: return start;
    		case 1: return SqlQuery;
	    	case 7: return InsertOrUpdate;
	    	case 8: return Sql;
	    	case 9: return DataExport;
	    	case 10: return DataImport;
	    	case 99: return end;
    		default : throw new InterfaceTypeException(" is wrong InterfaceType:'"+v+"'! ");
    	}
    }
}
