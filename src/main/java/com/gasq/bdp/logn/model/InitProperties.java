package com.gasq.bdp.logn.model;

/**
 * 
 * @author 巨伟刚
 * @packageName com.hadoop.model
 * @creatTime 2017年8月30日下午4:51:02
 * @remark
 */
public class InitProperties{
	/**
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
    */
	public static final String EMAIL_SENDER = "juwg@gacs.citic";
	public static final String DEFAULT_USERNAME="后台系统";
	
	/**
	 * 监听用户
	 */
	public static final String Moniter_USER = "user_msg";
	/**
	 * 默认数据库
	 */
	public static final String DEFAULT_DB = "default";
	/**
	 * gabdp事物名
	 */
	/**
	 * 数据源配置，增加数据源需要维护这个常量数据
	 */
	public static final String DB_DEFAULT = "gabdp";
	public static final String DB_GABDP = "hadoop";
	/**
	   *  缓存名称
	 */
	public static final String UIPF_CACHE = "uipfCache";

	/**
	 * 失败
	 */
	public static final int TimmerLog_FAIL = 0;
	/**
	 * 成功
	 */
	public static final int TIMMERLOG_SUCCESS = 1;
	
	public static String getCacheZHName(String type) {
		String name = "";
		switch (type) {
		case UIPF_CACHE:
			name = "用户信息平台";
			break;
		default:
			break;
		}
		return name;
	}
	
}
