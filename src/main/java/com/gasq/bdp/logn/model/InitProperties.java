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
	public static final String CACHE_NAME = "myCache";
	public static final String EMAIL_SENDER = "juwg@gacs.citic";
	public static final String DEFAULT_USERNAME="后台系统";
	/**
	 * 默认数据库
	 */
	public static final String DEFAULT_DB = "default";
	/**
	 * 默认事物名
	 */
	public static final String DEFAULT_TM = "gabdpTransactionManager";
	/**
	 * gabdp事物名
	 */
	public static final String GABDP_TM = "gabdpTransactionManager";
	/**
	 * 数据源配置，增加数据源需要维护这个常量数据
	 */
	public static final String DB_DEFAULT = "gabdp";
	public static final String DB_GABDP = "hadoop";

	/**
	 * 失败
	 */
	public static final int TimmerLog_FAIL = 0;
	/**
	 * 成功
	 */
	public static final int TIMMERLOG_SUCCESS = 1;
	
	/**
     * 创建hive日志表
    */
	public static final String LOG_HIVE_TABLE = "create external table IF NOT EXISTS t_log"
			+ "(request_time string,ip string,store_id string,customer_id string,apptypeplatform string,app_com string,task string,body string) PARTITIONED BY (request_date STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ' ' STORED AS TEXTFILE";
	
	
	/**
	 * 输出文件路径常量
	 */
	public static final String LOG_FILES_DIR = "E:/projects/logs";
	/**
	 * "任务运行中"
	 */
	public static final int  JOB_STATUS_RUNING = 2;
	/**
	 * "任务停止"
	 */
	public static final int  JOB_STATUS_STOP = 99;
	
	/**
	 * "工作流就绪"
	 */
	public static final int  WORKFLOW_STATUS_READY = 1;
	/**
	 * "工作流运行"
	 */
	public static final int  WORKFLOW_STATUS_RUNNING = 2;
	/**
	 * "工作流停止"
	 */
	public static final int  WORKFLOW_STATUS_STOP = 3;
	/**
	 * 监听用户
	 */
	public static final String Moniter_USER = "user_msg";
	/**
	 * 监听后台预约
	 */
	public static final String BACK_SUBSCRIBE_MSG = "back_subscribe_msg";
	/**
	 * 后台预约急诊信息
	 */
	public static final String SUBSCRIBE_RECEPTION_MSG = "subscribe_reception_msg";
	/**
	 * 当天后台预约信息
	 */
	public static final String BACK_SUBSCRIBE_DAY_ALL_MSG = "back_subscribe_day_all_msg";
	/**
	 * 监听前台接诊
	 */
	public static final String BEFORE_SUBSCRIBE_MSG = "before_subscribe_msg";
	
	public static final Integer CUSTOMER_CONSUMPTON_OPTION_ADD = 1;
	
	public static final Integer CUSTOMER_CONSUMPTON_OPTION_EDIT = 1;
	
	public static final Integer CUSTOMER_CONSUMPTON_OPTION_CLOSE = 1;
	
	
	/**
	 * 系统用户
	 */
	public static final String SYSTEM_USER = "user";
	
	public static final Integer INVENTORY_OPTION_ADD = 1;
	
	public static final Integer INVENTORY_OPTION_UPDATE = 2;
	
	public static final Integer INVENTORY_OPTION_DELETE = 3;
	
	public static final Integer INVENTORY_OPTION_APPEND = 4;
	
	public static final Integer INVENTORY_OPTION_DELIVERY = 5;
	
	public static final Integer INVENTORY_OPTION_ORDER_ADD = 6;
	
	public static final Integer INVENTORY_OPTION_ORDER_BACK = 7;
	
	/**
	 * 预约操作类型
	 */
	public static final Integer SUBSCRIBE_OPTION_TYPE_ADD = 1;
	public static final Integer SUBSCRIBE_OPTION_TYPE_EDIT = 2;
	public static final Integer SUBSCRIBE_OPTION_TYPE_CLOSE = 3;
	public static final Integer SUBSCRIBE_OPTION_TYPE_RECEPTION = 4;
	public static final Integer SUBSCRIBE_OPTION_TYPE_INFORM = 5;
	/**
	 * 客户项目类型
	 */
	public static final Integer CUSTOMER_PROJECT_OPTION_TYPE_ADD = 1;
	public static final Integer CUSTOMER_PROJECT_OPTION_TYPE_SUBTRACT = 2;
	
}
