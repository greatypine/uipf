/**
 * 
 */
package com.gasq.bdp.logn.component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.DataBaseBean;
import com.gasq.bdp.logn.model.TSysDatasource;
import com.gasq.bdp.logn.model.TSysSqlUpdateColumns;
import com.gasq.bdp.logn.model.TSysSqlUpdateKeys;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.component
 * @creatTime 2017年10月30日下午6:31:34
 * @remark 
 */
@Component
public class MyDAO{
	static Logger logger = Logger.getLogger(MyDAO.class);
	@Autowired DruidDataBaseFactory dataBaseFactory;
	private PreparedStatement ps = null;
	/**
	 * 获取数据库链接：默认为当前程序链接
	 * @return
	 */
	public Connection getConnection() throws WorkFlowJobException{
		Connection conn = null;
		try {
			DataSource ds = dataBaseFactory.getDataSource();
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new WorkFlowJobException("获取数据库链接出错！",e);
		}
		return conn;
	}
	/**
	 * 获取数据库链接：默认为当前程序链接
	 * @return
	 */
	public Connection getConnection(TSysDatasource bean) throws WorkFlowJobException{
		Connection conn = null;
		try {
			DataBaseBean dataBean = new DataBaseBean(bean.getUrl(),bean.getDriver(),bean.getName(),bean.getPassword());
			DataSource ds = dataBaseFactory.getDataSource(dataBean);
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new WorkFlowJobException("获取数据库链接出错！",e);
		}
		return conn;
	}
	/**
	 * 获取数据库链接
	 * @param code 数据库链接编码 对应数据库中的code
	 * @return
	 */
	public Connection getConnection(String code) throws WorkFlowJobException{
		Connection conn = null;
		try {
			DataSource ds = dataBaseFactory.getDataSource(code);
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new WorkFlowJobException("获取数据库链接出错！",e);
		}
		return conn;
	}
	/**
	 * 查询
	 * @param conn 数据库链接
	 * @param sql 查询语句
	 * @param vals 预编译字段
	 * @return
	 */
	public List<Map<String, Object>> query(Connection conn,String sql,Object[] vals) throws WorkFlowJobException {
		PreparedStatement ps = null;
		try {
			if(conn==null) conn = this.getConnection();
			ps = conn.prepareStatement(sql);
			if(vals!=null && vals.length>0 && sql.indexOf("?")!=-1) {
				for (int i = 1; i <= vals.length; i++) {
					ps.setString(i, vals[i-1].toString());
				}
			}
			ResultSet rs = ps.executeQuery();
			return getDatas(rs);
		} catch (SQLException e) {
			throw new WorkFlowJobException("执行sql查询出错！",e);
		}finally {
			destory(conn,ps);
		}
	}
	/**
	 * 更新操作
	 * @param conn 数据库链接
	 * @param sql 操作语句
	 * @param vals 预编译字段
	 * @return
	 */
	public int executeUpdateSql(Connection conn,String sql,Object[] vals,String dbcode) throws WorkFlowJobException {
		PreparedStatement ps = null;
		try {
			if(conn==null || conn.isClosed()) conn = this.getConnection(dbcode);
			ps = conn.prepareStatement(sql);
			if(vals!=null && vals.length>0 && sql.indexOf("?")!=-1) {
				for (int i = 1; i <= vals.length; i++) {
					Object val = vals[i-1];
					if(val==null) {
						ps.setObject(i,null);
					}else {
						if(val instanceof String) {
							ps.setString(i, vals[i-1].toString());
						}else if(val instanceof Integer) {
							ps.setInt(i, Integer.parseInt(vals[i-1].toString()));
						}else if(val instanceof Double) {
							ps.setDouble(i, Double.parseDouble(vals[i-1].toString()));
						}else if(val instanceof Float) {
							ps.setFloat(i, Float.parseFloat(vals[i-1].toString()));
						}else if(val instanceof Boolean) {
							ps.setBoolean(i,Boolean.parseBoolean(vals[i-1].toString()));
						}else if(val instanceof Timestamp){
							ps.setObject(i, vals[i-1]);
						}else if(val instanceof Date) {
							Date date = (Date) vals[i-1];
							java.sql.Date sqldate=new java.sql.Date(date.getTime());
							ps.setDate(i,sqldate);
						}else {
							ps.setObject(i, vals[i-1]);
						}
					}
				}
			}
			logger.info("执行更新或插入SQL:"+sql);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new WorkFlowJobException("执行sql更新出错！",e);
		}finally {
			destory(conn,ps);
		}
	}
	public static void batchInsert() throws ClassNotFoundException, SQLException{
        long start = System.currentTimeMillis();
//
//        connection.setAutoCommit(false);
//        PreparedStatement cmd = connection
//                .prepareStatement("insert into test1 values(?,?)");
//        
//        for (int i = 0; i < 1000000; i++) {//100万条数据
//            cmd.setInt(1, i);
//            cmd.setString(2, "test");
//            cmd.addBatch();
//            if(i%1000==0){
//                cmd.executeBatch();
//            }
//        }
//        cmd.executeBatch();
//        connection.commit();
        
        long end = System.currentTimeMillis();
        System.out.println("批量插入需要时间:"+(end - start)); //批量插入需要时间:24675
    }
	/**
	 * 更新操作
	 * @param sql
	 * @param vals
	 * @return
	 */
	public int executeUpdateSql(String sql,Object[] vals,String dbcode)  throws WorkFlowJobException {
		return this.executeUpdateSql(getConnection(), sql, vals,null);
	}
	/**
	 * 更新操作
	 * @param sql
	 * @return
	 */
	public int executeUpdateSql(String sql) throws WorkFlowJobException  {
		return this.executeUpdateSql(getConnection(), sql, null,null);
	}
	public Statement getStatement(Connection conn) throws WorkFlowJobException {
		try {
			if(conn==null) conn = this.getConnection();
			return conn.createStatement();
		} catch (SQLException e) {
			throw new WorkFlowJobException("执行sql更新出错！",e);
		}
	}
	
	private List<Map<String,Object>> getDatas(ResultSet rs) throws WorkFlowJobException {
		List<Map<String,Object>> resultmap = new ArrayList<Map<String,Object>>();
		if(rs!=null) {
			try {
				ResultSetMetaData rsmd = null;
				Map<String,Object> map = null;
				while(rs.next()) {
					rsmd = rs.getMetaData();
					map = new HashMap<String,Object>();
					for (int j = 1; j <= rsmd.getColumnCount(); j++) {
						Object obj = rs.getObject(j);
						String label = rsmd.getColumnLabel(j);
						if(!StringUtils.isNotBlank(label)) {
							label = rsmd.getColumnName(j).toLowerCase();
						}
						map.put(label,obj);
					}
					resultmap.add(map);
				}
			} catch (SQLException e) {
				throw new WorkFlowJobException("执行sql查询获取对象数据时出错！",e);
			}
		}
		return resultmap;
	}
	
	//get string datatype from int datatype
	   @SuppressWarnings("unused")
	private static String getDataType(int type,int scale){
	        String dataType="";
	        switch(type){
	            case Types.LONGVARCHAR: //-1
	                dataType="Long";
	                break;
	            case Types.CHAR:    //1
	                dataType="Character";
	                break;
	            case Types.NUMERIC: //2
	                switch(scale){
	                    case 0:
	                        dataType="Number";
	                        break;
	                    case -127:
	                        dataType="Float";
	                        break;
	                    default:
	                        dataType="Number";
	                }
	                break;
	            case Types.VARCHAR:  //12
	                dataType="String";
	                break;
	            case Types.DATE:  //91
	                dataType="Date";
	                break;
	            case Types.TIMESTAMP: //93
	                dataType="Date";
	                break;
	            case Types.BLOB :
	                dataType="Blob";
	                break;
	            default:
	                dataType="String";
	        }
	        return dataType;
	   }
	   /**
	    * 查询
	    * @param sql
	    * @return
	    */
	public List<Map<String, Object>> query(String sql) throws WorkFlowJobException {
		return this.query(getConnection(), sql, null);
	}
	/**
	 * 查询
	 * @param sql 执行语句
	 * @param vals 语句中的预设字段eg name=?
	 * @return
	 */
	public List<Map<String, Object>> query(String sql,Object[] vals) throws WorkFlowJobException  {
		return this.query(getConnection(), sql, vals);
	}
	/**
	 * 查询
	 * @param conn 数据库链接
	 * @param sql 查询语句
	 * @return
	 */
	public List<Map<String, Object>> query(Connection conn, String sql) throws WorkFlowJobException  {
		return this.query(conn, sql, null);
	}
	
	public int queryCount(String sql) throws WorkFlowJobException {
		Connection conn = getConnection();
		return queryCount(conn,sql,null);
	}
	public int queryCount(Connection conn, String sql,String dbcode)throws WorkFlowJobException  {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			if(conn==null || conn.isClosed()) conn = this.getConnection(dbcode);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
				logger.info("统计SQL:"+sql+"-->结果:"+result+"条数据");
			}
		} catch (SQLException e) {
			throw new WorkFlowJobException("执行sql统计错误！sql=》"+sql,e);
		}finally {
			destory(conn,ps);
		}
		return result;
	}
	public int count(Connection conn,String tablename,String wheresql,String dbcode)throws WorkFlowJobException  {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select count(1) from "+tablename+" "+wheresql;
		try {
			if(conn==null || conn.isClosed()) conn = this.getConnection(dbcode);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
				logger.info("统计SQL:"+sql+"-->结果:"+result+"条数据");
			}
		} catch (SQLException e) {
			throw new WorkFlowJobException("执行sql统计错误！sql=》"+sql,e);
		}finally {
			destory(conn,ps);
		}
		return result;
	}
	public void destory(Connection conn,PreparedStatement ps)throws WorkFlowJobException  {
		try {
			if(ps !=null)  ps.close();
			if(conn !=null)  conn.close();
		} catch (SQLException e) {
			throw new WorkFlowJobException("关闭sql链接失败！",e);
		}
	}
	public int count(Connection conn, String tableName, String where, Object[] keyswhereval,String dbcode) throws WorkFlowJobException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select count(1) from "+tableName+" "+where;
		try {
			if(conn==null || conn.isClosed()) conn = this.getConnection(dbcode);
			ps = conn.prepareStatement(sql);
			if(keyswhereval!=null && keyswhereval.length>0 && sql.indexOf("?")!=-1) {
				for (int i = 1; i <= keyswhereval.length; i++) {
					ps.setString(i, keyswhereval[i-1].toString());
				}
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
				logger.info("统计SQL:"+sql+"-->结果:"+result+"条数据");
			}
		} catch (SQLException e) {
			throw new WorkFlowJobException("执行sql统计错误！sql=》"+sql,e);
		}finally {
			destory(conn,ps);
		}
		return result;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PreparedStatement executeBatchInsertSql(Connection conn, String psSql, List<Map> listipobj,List<TSysSqlUpdateColumns> columns, List<TSysSqlUpdateKeys> keys, int size) throws WorkFlowJobException {
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(psSql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			if(listipobj!=null && listipobj.size()>0 && psSql.indexOf("?")!=-1) {
				int batchCommit = 0;
				for (Map<String,Object> map : listipobj) {
					Set<Entry<String,Object>> inputObjSet = map.entrySet();
					Object[] vals = WorkFlowUtil.collectPSValue(columns,keys,inputObjSet);
					int i = 1;
					for (Object val : vals) {
						if(val==null) {
							ps.setObject(i,null);
						}else {
							if(val instanceof String) {
								ps.setString(i, vals[i-1].toString());
							}else if(val instanceof Integer) {
								ps.setInt(i, Integer.parseInt(vals[i-1].toString()));
							}else if(val instanceof Double) {
								ps.setDouble(i, Double.parseDouble(vals[i-1].toString()));
							}else if(val instanceof Float) {
								ps.setFloat(i, Float.parseFloat(vals[i-1].toString()));
							}else if(val instanceof Boolean) {
								ps.setBoolean(i,Boolean.parseBoolean(vals[i-1].toString()));
							}else if(val instanceof Timestamp){
								ps.setObject(i, vals[i-1]);
							}else if(val instanceof Date) {
								Date date = (Date) vals[i-1];
								java.sql.Date sqldate=new java.sql.Date(date.getTime());
								ps.setDate(i,sqldate);
							}else {
								ps.setObject(i, vals[i-1]);
							}
						}
						i++;
					}
					ps.addBatch();
					if(batchCommit>0 && batchCommit%size==0) {
						ps.executeBatch();
					}
					batchCommit++;
				}
			}
			ps.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			throw new WorkFlowJobException("执行批量插入错误！sql=》"+psSql+" 错误信息："+e.getMessage(),e);
		}
		return ps;
	}
	
	public void executeBatch(PreparedStatement ps) throws SQLException {
		if(ps!=null)ps.executeBatch();
	}
	
	public void commit(Connection conn) throws SQLException {
		if(conn!=null)conn.commit();
	}
	public int executeUpdateSql(Connection conn, String sql,String dbcode) throws WorkFlowJobException {
		return executeUpdateSql(conn,sql,null,dbcode);
	}

//	   public static void main(String[] args) {
//		   String url = "jdbc:mysql://10.30.0.1:3306/hive-mysql?useUnicode=true&characterEncoding=utf8";
//		   String driver = "com.mysql.jdbc.Driver";
//		   String name = "sjyx";
//		   String pass = "Gnkjsjyx2017!";
//		   DataBaseBean db = new DataBaseBean(url,driver,name,pass);
//		   DruidDataBaseFactory ddbf = new DruidDataBaseFactory();
//		   ddbf.getConnection(db);
//		   String sql = "select a.`code`,a.`view`,a.text,a.state from t_sys_menu a order by a.code ";
//		   String[] fields = {"code","view","text","state"};
//		   List<Map<String, Object>> datas = ddbf.query(sql, fields);
//		   System.out.println(CommonUtils.BeanToJSON(datas));
//	}
}
