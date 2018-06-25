package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;

/**
 * @author justin 
 * @2017年6月15日 下午2:35:34
 * @TODO 注释：
 */
public interface ETLBaseTemplate {
	
	/**
	 * 执行函数语句 
	 * @param sql
	 * @param sqlbean
	 * @return 执行结果 一个值obj （手动转换相应的类型）
	 * @throws Exception
	 */
	public Object exeFunctionSQL(String sql) throws Exception;
	
	/**
	 * for test
	 * @return
	 * @throws Exception 
	 */
	public int queryCountMCustomer() throws Exception;
	/**
	 * for test
	 * @return
	 */
	public int queryCountSCustomer();
	/**
	 * 查询状态
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	public boolean isOpen(String key) throws Exception;
	/**
	 * 执行sql
	 * @param sql
	 * @return
	 * @throws Exception 
	 */
	public int exeSQL(String sql) throws Exception;
	
	public DataSourceTransactionManager getTransctionManger(String key);
	
	public TransactionStatus getTransactionStatus(DataSourceTransactionManager dataSourceTransactionManager,String key);
	/**
	 * 根据sql语句查询数据集
	 * @param sql mysql sql语句
	 * @param db 数据库对象 如果为null 则为默认数据库：36-gabdp
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryList(String sql,String db) throws Exception;
}
