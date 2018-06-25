/**
 * 
 */
package com.gasq.bdp.logn.job;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.gasq.bdp.logn.model.TSysTimerJobSql;
import com.gasq.bdp.logn.service.TSysDataColumnsService;
import com.gasq.bdp.logn.service.TSysDataService;
import com.gasq.bdp.logn.service.TSysTimerJobSqlService;
import com.gasq.bdp.logn.utils.CommonUtils;

/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.job
 * @creatTime 2017年11月30日上午11:13:13
 * @remark
 */
@Component
public class SqlOptionLogJob implements IJob{
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(SqlOptionLogJob.class);
	static Connection conn = null;
	static Statement stmt = null;
	@Autowired
	TSysTimerJobSqlService jobSqlqueryService;
	@Autowired
	TSysDataService dataService;
	@Autowired
	TSysDataColumnsService dataColumnsService;
	@Autowired 
	MyDAO dao;
	
	public Object execute(ParamsObject paramsObject, Object inputObj, PagingBean pb) throws WorkFlowJobException{
		TSysTimerJobSql sqlbean = (TSysTimerJobSql) paramsObject;
		logger.info("进入Sql任务调度.................");
		conn = dao.getConnection(sqlbean.getDbCode());
		int result = 0;
		List<Map<String, Object>> list = null;
		if(sqlbean.getIsmultiple()) {//多条
			String[] sqls = sqlbean.getDiySql().split(";");
			for (String sql : sqls) {
				sqlbean.setDiySql(sql);
				String sqlr = handleSql(sqlbean,inputObj);
				result = dao.executeUpdateSql(conn, sqlr,sqlbean.getDbCode());
				if(result<=0) {
					logger.info("更新语句没有更新到数据，请检查语句.................\n+sql:"+sqlr);
				}
			}
		}else {
			String sql = handleSql(sqlbean,inputObj);
			result = dao.executeUpdateSql(conn, sql,sqlbean.getDbCode());
			if(result<=0) {
				logger.info("更新语句没有更新到数据，请检查语句.................\n+sql:"+sql);
			}
		}
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "null" })
	public String handleSql(TSysTimerJobSql sqlquery, Object inputObj) throws WorkFlowJobException {
		String sql = sqlquery.getDiySql();
		if(sql.indexOf("${")!=-1) {
			if(inputObj!=null) throw new WorkFlowJobException(sqlquery.getName()+"运行失败！输入对象为null");
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
							sql = replaceSql(sql,inputMap);
						}
					}else {
						inputMap = (Map<String, Object>) CommonUtils.JsonToBean(CommonUtils.BeanToJSON(inputObj), Map.class);
						Map<String, Object> columns = (Map<String, Object>) inputMap.get(data.getCode());
						sql = replaceSql(sql,columns);
					}
				}
			}
		}
		return sql;
	}
	private String replaceSql(String sql,Map<String,Object> inputMap) {
		Set<Entry<String, Object>> inputset = inputMap.entrySet();
		for (Entry<String, Object> entry : inputset) {
			String key = entry.getKey();
			if(sql.indexOf("${"+key+"}")!=-1) {
				sql = CommonUtils.parse0(sql, entry.getValue());
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
