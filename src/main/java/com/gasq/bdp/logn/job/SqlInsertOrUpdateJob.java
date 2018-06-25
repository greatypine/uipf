/**
 * 
 */
package com.gasq.bdp.logn.job;

import java.sql.Connection;
import java.sql.SQLException;
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
import com.gasq.bdp.logn.model.TSysSqlUpdateColumns;
import com.gasq.bdp.logn.model.TSysSqlUpdateKeys;
import com.gasq.bdp.logn.model.TSysTimerJobSqlInsertUpdate;
import com.gasq.bdp.logn.service.TSysDataService;
import com.gasq.bdp.logn.service.TSysTimerJobSqlQueryService;
import com.gasq.bdp.logn.service.UpdateColumnsService;
import com.gasq.bdp.logn.service.UpdateKeysService;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

/**
 * @author 巨伟刚
 * @packageName MR_ElasticSearch.mrs
 * @creatTime 2017年8月30日上午11:44:57
 * @remark
 */
@Component
public class SqlInsertOrUpdateJob implements IJob{
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(SqlInsertOrUpdateJob.class);
	static Connection conn = null;
	@Autowired TSysTimerJobSqlQueryService jobSqlqueryService;
	@Autowired UpdateColumnsService columnsService;
	@Autowired UpdateKeysService keysService;
	@Autowired TSysDataService dataService;
	@Autowired MyDAO dao;
	
	public Object execute(ParamsObject paramsObject, Object inputObj, PagingBean pb) throws WorkFlowJobException {
		TSysTimerJobSqlInsertUpdate insertUpdate = (TSysTimerJobSqlInsertUpdate) paramsObject;
		logger.info("进入插入或更新任务调度.................");
		List<TSysSqlUpdateKeys> keys = collectKeys(insertUpdate);
		List<TSysSqlUpdateColumns> columns = collectColumns(insertUpdate);
		boolean b = false;
		try {
			b = handleSql(insertUpdate,inputObj,keys,columns);
			if(!b) {
				throw new WorkFlowJobException("插入或更新操作失败------事物回滚失败！");
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new WorkFlowJobException("插入或更新操作失败"+e+"------事物回滚失败！", e1);
			}
		}finally {
			dao.destory(conn,null);
		}
		if(b) {
			logger.info("插入或更新任务执行成功.................");
		}else {
			logger.info("插入或更新任务执行失败.................");
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean handleSql(TSysTimerJobSqlInsertUpdate insertUpdate, Object inputObj, List<TSysSqlUpdateKeys> keys, List<TSysSqlUpdateColumns> columns) throws WorkFlowJobException, SQLException {
		if(inputObj==null) {//传入的参数为null
			if(insertUpdate.getInputParams()!=null) throw new WorkFlowJobException("传入的参数为null！");
		}else {
			if(insertUpdate.getInputParams()==null) throw new WorkFlowJobException("配置的输入参数错误！");
			conn = dao.getConnection(insertUpdate.getDbCode());
			Integer update = insertUpdate.getIsbatch();
			String psSql = null;
			boolean b = false;
			Object[] vals = null;
			String ipcode = "";
			if(insertUpdate.getInputParams()!=null) {
				TSysData tdata = dataService.selectByPrimaryKey(insertUpdate.getInputParams());
				ipcode = tdata.getCode();
			}
			List<Map> listipobj = WorkFlowUtil.transformParams(ipcode,inputObj);
			if(listipobj.size()>0) {
				if(update==1) {//只插入--不检查数据是否存在  当条件为不更新时 条件不做任何操作 默认为批量插入 如果设定不批量插入则每条为一个事物
					int btsize = insertUpdate.getBatchsize();
					btsize = btsize<=0?1000:btsize;
					psSql = WorkFlowUtil.handlePSInsert(insertUpdate,columns,keys);
					dao.executeBatchInsertSql(conn, psSql,listipobj,columns,keys,btsize);
					b = true;
				}else if(update==2){//只更新--不检查数据是否存在 
					int btsize = insertUpdate.getBatchsize();
					btsize = btsize<=0?1000:btsize;
					psSql = WorkFlowUtil.handlePSUpdate(insertUpdate, listipobj, columns, keys);
					dao.executeBatchInsertSql(conn, psSql,listipobj,columns,keys,btsize);
					b = true;
				}else {//更新或插入--需要检查数据是否存在
					for (Map<String,Object> map : listipobj) {
						Set<Entry<String,Object>> inputObjSet = map.entrySet();
						String where = WorkFlowUtil.assemblePSWhereSql(keys);
						Object[] keyswhereval = WorkFlowUtil.collectWhereCondition(keys,inputObjSet);
						int count = dao.count(conn,insertUpdate.getTableName(), where,keyswhereval,insertUpdate.getDbCode());
						psSql = WorkFlowUtil.handleInsertOrUpdate(insertUpdate,inputObjSet,keys,columns,where,count);
						vals = WorkFlowUtil.collectPSValue(columns,keys,inputObjSet);
						dao.executeUpdateSql(conn, psSql, vals,insertUpdate.getDbCode());
					}
					b = true;
				}
			}
			return b;
		}
		return false;
	}
	
	/**
	 * 获取更新字段和对应的类型
	 * @param insertUpdate
	 * @return
	 */
	private List<TSysSqlUpdateColumns> collectColumns(TSysTimerJobSqlInsertUpdate insertUpdate) {
		TSysSqlUpdateColumns columns = new TSysSqlUpdateColumns();
		columns.setInsertUpdateJobId(insertUpdate.getId().intValue());
		List<TSysSqlUpdateColumns> sqlUpdateColumns = columnsService.queryBeanList(columns);
		if(sqlUpdateColumns.size()>0) return sqlUpdateColumns;
		return null;
	}
	
	/**
	 * 获取条件字段和对应的类型
	 * @param insertUpdate
	 * @return
	 */
	private List<TSysSqlUpdateKeys> collectKeys(TSysTimerJobSqlInsertUpdate insertUpdate) {
		TSysSqlUpdateKeys keys = new TSysSqlUpdateKeys();
		keys.setInsertUpdateJobId(insertUpdate.getId().intValue());
		List<TSysSqlUpdateKeys> sqlUpdateKeys = keysService.queryBeanList(keys);
		if(sqlUpdateKeys.size()>0) return sqlUpdateKeys;
		return null;
	}
	
	public void destory() throws WorkFlowJobException {
		try {
			if(conn !=null)  conn.close();
		} catch (SQLException e) {
			throw new WorkFlowJobException("关闭sql链接失败！",e);
		}
	}

	@Override
	public Object execute(ParamsObject params, Object inputObj) throws WorkFlowJobException {
		return null;
	}

}
