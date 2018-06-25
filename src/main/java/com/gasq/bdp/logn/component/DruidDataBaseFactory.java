/**
 * 
 */
package com.gasq.bdp.logn.component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.gasq.bdp.logn.model.DataBaseBean;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.TSysDatasource;
import com.gasq.bdp.logn.model.TSysDatasourceExample;
import com.gasq.bdp.logn.service.TSysDatasourceService;
import com.gasq.bdp.logn.utils.DBContextHolder;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.utils
 * @creatTime 2017年10月30日下午4:23:01
 * @remark 
 */
@Component
public class DruidDataBaseFactory  implements CommandLineRunner{
	private Logger logger = Logger.getLogger(this.getClass());
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.driver-class-name}")
	private String driver;
	@Value("${spring.datasource.username}")
	private String name;
	@Value("${spring.datasource.password}")
	private String password;
	@Autowired TSysDatasourceService basDatasourceService;
	/** 
	 * Druid 
	 * @return 
	 */  
	public synchronized DataSource getDataSource(DataBaseBean dataBean) {
	    try {
	    	if(dataBean==null) return null;
	        //通过Map方式设置Druid参数  
	        Map<String, String> druidMap=new HashMap<String, String>();  
	        druidMap.put(DruidDataSourceFactory.PROP_USERNAME, dataBean.getName());  
	        druidMap.put(DruidDataSourceFactory.PROP_PASSWORD, dataBean.getPassword());  
	        druidMap.put(DruidDataSourceFactory.PROP_URL, dataBean.getUrl());  
	        druidMap.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, dataBean.getDriver());  
	        //通过DruidDataSourceFactory获取DataSource实例  
	        DataSource dataSource=DruidDataSourceFactory.createDataSource(druidMap); 
	        return dataSource;
	    } catch(Exception e) {
	        logger.info(e.getMessage(),e);  
	    }
	    return null;
	}
	
    public synchronized void selectDataSource(String code) {
        Object sid = DBContextHolder.getDBType();
        if ("".equals(sid) || sid ==null) {
            DBContextHolder.setDBType("default");
            return;
        }
        Object obj = DBContextHolder.dbs.get(code);
        if (obj != null && sid.equals(code)) {
            return;
        } else {
        	DataSource dataSource = this.getDataSource(code);
            if (null != dataSource)
                this.setDataSource(code, dataSource);
        }
    }
	
	private synchronized void setDataSource(String code, DataSource dataBean) {
		DBContextHolder.dbs.put(code, dataBean);
        DBContextHolder.setDBType(code);
	}

	public synchronized DataSource getDataSource(String code) {
        DataSource ds = null;
    	if(code==null || "".equals(code)) return null;
    	if(DBContextHolder.dbs.containsKey(code)) {
    		ds = (DataSource) DBContextHolder.dbs.get(code);
    		return ds;
    	}
    	TSysDatasourceExample example = new TSysDatasourceExample();
		example.createCriteria().andCodeEqualTo(code).andStatusEqualTo(true);
		List<TSysDatasource> basDatasources = basDatasourceService.selectByExample(example);
		if(basDatasources.size()>0) {
			for (TSysDatasource TSysDatasource : basDatasources) {
				DataBaseBean dataBean = new DataBaseBean(TSysDatasource.getUrl(),TSysDatasource.getDriver(),TSysDatasource.getName(), TSysDatasource.getPassword());
				ds = this.getDataSource(dataBean);
				this.setDataSource(code, ds);
			}
		}
	    return ds;
	}

	@SuppressWarnings("unused")
	private synchronized Connection getConnection() {
		try {
			return this.getDataSource().getConnection();
		} catch (SQLException e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	}

	public synchronized void addTargetDataSource(String key, DataBaseBean dataBean) {
		DBContextHolder.dbs.put(key,this.getDataSource(dataBean));
		DBContextHolder.setDBType(key);
    }
	
	public synchronized DataSource getDataSource() {
		DataSource ds = this.getDataSource(InitProperties.DEFAULT_DB);
		return ds;
	}
	@Override
	public void run(String... arg0) throws Exception {
		initDefaultDB();
	}
	
	private void initDefaultDB() {
//		DataBaseBean dataBean = new DataBaseBean(url,driver,name,password);
//		DruidDataBaseFactory ddbf = new DruidDataBaseFactory();
//		ddbf.addTargetDataSource(InitProperties.DEFAULT_DB, dataBean);
//		log.info("执行数据《"+InitProperties.DEFAULT_DB+"》初始化操作完成！......");
	}
	
//	 public static void main(String[] args) {
//		 	DruidDataBaseFactory dds = new DruidDataBaseFactory();
//		 	String url = "jdbc:mysql://10.30.0.1:3306/hive-mysql?useUnicode=true&characterEncoding=utf8";
//		   String driver = "com.mysql.jdbc.Driver";
//		   String name = "sjyx";
//		   String pass = "Gnkjsjyx2017!";
//            DataBaseBean dataBean = new DataBaseBean(url,driver,name, pass);
//            dds.addTargetDataSource("default", dataBean);
//            DataSource ds = dds.getDataSource("mydb");
////            DataSource ds = dds.getDataSource(dataBean);
//            Connection conn = null;
//			try {
//				conn = ds.getConnection();
//			} catch (SQLException e) {
//			}
// 		   String sql = "select a.`code`,a.`view`,a.text,a.state from t_sys_menu a order by a.code ";
//// 		   String[] fields = {"code","view","text","state"};
// 		   MyDAO dao = new MyDAO();
// 		   List<Map<String, Object>> datas =dao.query(conn, sql, null);
// 		   System.out.println(CommonUtils.BeanToJSON(datas));
////	    	DataSource bds = dds.getDataSource("mydb");
////	    	try {
////	    		PreparedStatement ps = bds.getConnection().prepareStatement("select * from t_bas_datasource ");
////	    		ps.executeQuery();
////			} catch (SQLException e) {
////			}
//		}
}
