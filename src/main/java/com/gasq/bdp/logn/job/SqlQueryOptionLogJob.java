/**
 * 
 */
package com.gasq.bdp.logn.job;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.component.MyDAO;
import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.PagingBean;
import com.gasq.bdp.logn.model.ParamsObject;
import com.gasq.bdp.logn.model.TSysData;
import com.gasq.bdp.logn.model.TSysDataColumns;
import com.gasq.bdp.logn.model.TSysDataColumnsExample;
import com.gasq.bdp.logn.model.TSysTimerJobSqlQuery;
import com.gasq.bdp.logn.service.TSysDataColumnsService;
import com.gasq.bdp.logn.service.TSysDataService;
import com.gasq.bdp.logn.service.TSysTimerJobSqlQueryService;
import com.gasq.bdp.logn.utils.CommonUtils;

/**
 * @author 巨伟刚
 * @packageName MR_ElasticSearch.mrs
 * @creatTime 2017年8月30日上午11:44:57
 * @remark
 */
@Component
public class SqlQueryOptionLogJob implements IJob{
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(SqlQueryOptionLogJob.class);
	static Connection conn = null;
	static Statement stmt = null;
	@Autowired
	TSysTimerJobSqlQueryService jobSqlqueryService;
	@Autowired
	TSysDataService dataService;
	@Autowired
	TSysDataColumnsService dataColumnsService;
	@Autowired 
	MyDAO dao;
	
	public Object execute(ParamsObject paramsObject, Object inputObj, PagingBean pb) throws WorkFlowJobException {
		TSysTimerJobSqlQuery sqlquery = (TSysTimerJobSqlQuery) paramsObject;
		logger.info("进入SqlQuery任务调度.................");
		conn = dao.getConnection(sqlquery.getDbCode());
		String sql = handleSql(sqlquery,inputObj);
		List<Map<String, Object>> list = null;
		if(sqlquery.getIslimit()&& pb!=null) {//分页
			sql += " limit "+pb.getIndex()+","+pb.getSize() ;
			list = dao.query(conn, sql);
			destory();
		}else {
			list = dao.query(conn, sql);
			destory();
		}
		if(sqlquery.getOutParams()!=null) {
			return handleOutPutParams(sqlquery,list);
		}
		return list;
	}
	private Object handleOutPutParams(TSysTimerJobSqlQuery sqlquery,List<Map<String, Object>> list) {
		Map<String,Object> resultobjmap = new HashMap<String,Object>();
		if(list!=null && list.size()>0) {
			TSysData data = dataService.selectByPrimaryKey(sqlquery.getOutParams());
			TSysDataColumnsExample dcexample = new TSysDataColumnsExample();
			dcexample.createCriteria().andDataIdEqualTo(data.getId()).andStatusEqualTo(true);
			List<TSysDataColumns> dclist = dataColumnsService.selectByExample(dcexample);
			Map<String,Object> objclsmap = null;
			List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : list) {
				objclsmap = new HashMap<String,Object>();
				for (TSysDataColumns tSysDataColumns : dclist) {
					Object value = map.get(tSysDataColumns.getCode());
					objclsmap.put(tSysDataColumns.getCode(), value);
//					if(value == null || value =="") {objclsmap.put(tSysDataColumns.getCode(),null);continue;};
//					if(tSysDataColumns.getType()==1) {
//						objclsmap.put(tSysDataColumns.getCode(), Integer.parseInt(value.toString()));
//					}else if(tSysDataColumns.getType()==2) {
//						objclsmap.put(tSysDataColumns.getCode(), Double.parseDouble(value.toString()));
//					}else if(tSysDataColumns.getType()==3) {
//						objclsmap.put(tSysDataColumns.getCode(), value.toString());
//					}else if(tSysDataColumns.getType()==4) {
//						objclsmap.put(tSysDataColumns.getCode(),DateUtil.parseStr(value.toString(),DateUtil.DATE_DEFAULT_FORMAT));
//					}else if(tSysDataColumns.getType()==5) {
//						objclsmap.put(tSysDataColumns.getCode(),DateUtil.getDateTimeStr(value));
//					}
				}
				list1.add(objclsmap);
			}
			resultobjmap.put(data.getCode(), list1);
		}
		return resultobjmap;
	}
	
	@SuppressWarnings("unchecked")
	public String handleSql(TSysTimerJobSqlQuery sqlquery, Object inputObj) {
		String sql = sqlquery.getDiySql();
		if(sql.indexOf("${")!=-1) {
			TSysData data = dataService.selectByPrimaryKey(sqlquery.getInputParams());
			if(data!=null) {
				TSysDataColumnsExample dcexample = new TSysDataColumnsExample();
				dcexample.createCriteria().andDataIdEqualTo(data.getId()).andStatusEqualTo(true);
				List<TSysDataColumns> dclist = dataColumnsService.selectByExample(dcexample);
				if(dclist.size()>0) {
					Map<String,Object> inputMap = null;
					if(inputObj.getClass().isArray()) {
						Object[] obj = (Object[]) inputObj;
						for (Object object : obj) {
							inputMap = (Map<String, Object>) CommonUtils.JsonToBean(CommonUtils.BeanToJSON(object), Map.class);
							List<Map<String, Object>> columns = (List<Map<String, Object>>) inputMap.get(data.getCode());
							sql = replaceSql(sql,columns);
						}
					}else {
						inputMap = (Map<String, Object>) CommonUtils.JsonToBean(CommonUtils.BeanToJSON(inputObj), Map.class);
						List<Map<String, Object>> columns = (List<Map<String, Object>>) inputMap.get(data.getCode());
						sql = replaceSql(sql,columns);
					}
				}
			}
		}
		return sql;
	}
	private String replaceSql(String sql,List<Map<String,Object>> inputMap) {
		for (Map<String, Object> map : inputMap) {
			Set<Entry<String, Object>> inputset = map.entrySet();
			for (Entry<String, Object> entry : inputset) {
				String key = entry.getKey();
				if(sql.indexOf("${"+key+"}")!=-1) {
					sql = CommonUtils.parse0(sql, entry.getValue());
				}
			}
		}
		return sql;
	}

	public void destory() {
		if(stmt !=null) {try {
			stmt.close();
			if(conn !=null) {conn.close();}
		} catch (SQLException e) {
			logger.info(e.getMessage(),e);
		}}
	}

	@Override
	public Object execute(ParamsObject params, Object inputObj) throws WorkFlowJobException {
		return null;
	}

}
