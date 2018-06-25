package com.gasq.bdp.logn.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author justin 
 * @2016年12月15日 下午5:51:38
 * @TODO 注释：
 */
@Component
public class SpringApplicationUtils implements ApplicationContextAware {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static SpringApplicationUtils applicationUtils=null;
	@SuppressWarnings("unused")
	private DataSourceTransactionManager transactionManager = null;
	private DefaultTransactionDefinition def = null;
	private TransactionStatus status = null;
	private static ApplicationContext applicationContext = null;
	private SpringApplicationUtils(){
		
	}
	/**
	 * 获取事物处理实例
	 * @return
	 */
	public synchronized static SpringApplicationUtils getInstance(){
		if(applicationUtils == null) {
			applicationUtils = new SpringApplicationUtils();
		}
		return applicationUtils;
	}

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (this.applicationContext == null) {
			this.applicationContext = applicationContext;
		}
	}

	    //获取applicationContext
	    public static ApplicationContext getApplicationContext() {
	        return applicationContext;
	    }

	    //通过name获取 Bean.
	    public static Object getBean(String name){
	        return getApplicationContext().getBean(name);
	    }

	    //通过class获取Bean.
	    public static <T> T getBean(Class<T> clazz){
	        return getApplicationContext().getBean(clazz);
	    }

	    //通过name,以及Clazz返回指定的Bean
	    public static <T> T getBean(String name,Class<T> clazz){
	        return getApplicationContext().getBean(name, clazz);
	    }
	
	/**
	 * 获取事物状态
	 * @param tm
	 * @return
	 */
	public TransactionStatus getTransactionStatus(DataSourceTransactionManager tm,String tmname) {
		if(tm==null){
			tm = getTransaction(tmname);
		}
		//4.获得事务状态
		if(def==null){
			def = getDefaultTransactionDefinition();
		}
		status = tm.getTransaction(def);
		logger.info("成功获取事物状态！");
		return status;
	}
	/**
	 * 获取处理事务类
	 * @return
	 */
	public DataSourceTransactionManager getTransaction(String tmkey){
		//获取Spring容器的对象
		WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
		//1.获取事务控制管理器
		DataSourceTransactionManager transactionManager = contextLoader.getBean(tmkey, DataSourceTransactionManager.class);
		//2.获取事务定义
		def = new DefaultTransactionDefinition();
		//3.设置事务隔离级别，开启新事务
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		logger.info("成功获取事物处理类！");
		return transactionManager;
	}
	/**
	 * 获取事物级别
	 * @return
	 */
	public DefaultTransactionDefinition getDefaultTransactionDefinition(){
		if(def==null){
			def = new DefaultTransactionDefinition();
		}
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		return def;
	}

}
