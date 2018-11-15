/**
 * 
 */
package com.gasq.bdp.logn.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageInfo;

/**
 * 操作工作流impala节点job任务
 * @author Ju_weigang
 * @时间 2018年5月25日上午11:29:10
 * @项目路径 com.gasq.bdp.logn.utils
 * @描述 
 */
@Component
public class ImpalaOptions {
	static Logger logger = Logger.getLogger(ImpalaOptions.class);
	@Autowired
    @Qualifier("impalaJdbcTemplate")
    JdbcTemplate impalaJdbcTemplate;
	
	/**
	 * impala统计
	 */
	public int queryImpalaCount(String sql) throws Exception {
		Long result = 0L;
		try {
			List<Map<String, Object>> queryForList = impalaJdbcTemplate.queryForList(sql);
			result = (Long) queryForList.get(0).values().toArray()[0];
			logger.info("impala统计结果-->"+result+"条数据");
		} catch (Exception e) {
			throw new Exception("执行impala统计失败！sql->"+sql,e);
		}
		return result.intValue();
	}
	/**
	 * 执行impala语句
	 */
	public List<Map<String, Object>> exeImpalaOption(String sql) throws Exception {
		try {
			logger.info("impala链接成功....");
			/** 第一步:不存在就创建/执行hql语句 **/
			logger.info("开始执行Impala语句--》"+sql);
			impalaJdbcTemplate.execute(sql);
		} catch (Exception e) {
			throw new Exception("执行Impala语句失败！《"+sql+"》错误信息:"+e.getMessage(),e);
		}
		return null;
	}
	/**
	 * impala查询
	 */
	public List<Map<String, Object>> queryImpalaList(String sql) throws Exception {
		return queryImpalaList(sql, false, null);
	}
	
	/**
	 * impala查询
	 */
	public List<Map<String, Object>> queryImpalaList(String sql,boolean isinvalidate,List<String> tablenames) throws Exception {
		try {
			/** 第一步:不存在就创建/执行hql语句 **/
			if(isinvalidate) {
				logger.info("impala链接成功....");
				logger.info("刷新impala数据库表....");
				for (String tablename : tablenames) {
					impalaJdbcTemplate.execute("refresh " + tablename);
				}
				logger.info("impala数据库表刷新成功！");
				logger.info("开始执行Impala语句--》"+sql);
				List<Map<String, Object>> queryForList = impalaJdbcTemplate.queryForList(sql);
				logger.info("impala查询成功！");
				return queryForList;
			}else {
				logger.info("impala数据库表刷新成功！");
				logger.info("开始执行Impala语句--》"+sql);
				List<Map<String, Object>> queryForList = impalaJdbcTemplate.queryForList(sql);
				logger.info("impala查询成功！");
				return queryForList;
			}
		} catch (Exception e) {
			throw new Exception("执行Impala语句失败！《"+sql+"》错误信息:"+e.getMessage(),e);
		}
	}
	/**
	 * impala分页查询 
	 * @param sql : 要查询的sql语句
	 * @param pageInfo : 分页对象
	 * @param isinvalidate : 是否刷新表    注：同步impala的metadata数据和hdfs中数据同步
	 * @param tablenames : 如果isinvalidate为true需传入要刷新的表明
	 */
	public Map<String,Object> queryImpalaByPage(String sql, PageInfo pageInfo,boolean isinvalidate,List<String> tablenames){
		Map<String,Object> map_result = new HashMap<String,Object>();
		try {
			if(pageInfo!=null){
//				String sql_count = "SELECT COUNT(1) as total FROM ("+sql+") T";
//				int impalaCount = this.queryImpalaCount(sql_count);
//				pageInfo.setTotalRecords(impalaCount);
//				sql += " limit "+ pageInfo.getRecordsPerPage()+" offset "+ (pageInfo.getRecordsPerPage()*(pageInfo.getCurrentPage() - 1));
//				List<Map<String, Object>> list = this.queryImpalaList(sql,isinvalidate,tablenames);
//				Integer total_pages = (pageInfo.getTotalRecords()-1)/pageInfo.getRecordsPerPage()+1;
//				map_result.put("pageinfo",pageInfo);
//				map_result.put("total_pages", total_pages);
//				map_result.put("totalRecords", pageInfo.getTotalRecords());
//				map_result.put("data", list);
//				map_result.put("user_active",list);
			}else{
				List<Map<String, Object>> list = this.queryImpalaList(sql);
				map_result.put("user_active",list);
				map_result.put("data", list);
				map_result.put("pageinfo",null);
				map_result.put("total_pages", 0);
			}
			logger.info("== End Query Results =======================\n\n");
		} catch (Exception e) {
			logger.error("分页查询impala失败！错误信息："+e.getMessage(),e);
		}
	    return map_result;
	}
	/**
	 * impala分页查询
	 */
	public Map<String,Object> queryImpalaByPage(String sql, PageInfo pageInfo){
		return queryImpalaByPage(sql, pageInfo, false, null);
	}
}
