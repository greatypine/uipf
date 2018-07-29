/**
 * 
 */
package com.gasq.bdp.logn.utils;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.mapper.TCustomerSubscribeMapper;
import com.gasq.bdp.logn.mapper.TLtnCustomerMapper;
import com.gasq.bdp.logn.mapper.TSysUserMapper;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCustomerSubscribe;
import com.gasq.bdp.logn.model.TCustomerSubscribeExample;
import com.gasq.bdp.logn.model.TLtnCustomer;
import com.gasq.bdp.logn.model.TLtnCustomerExample;
import com.gasq.bdp.logn.model.TSysSqlUpdateColumns;
import com.gasq.bdp.logn.model.TSysSqlUpdateKeys;
import com.gasq.bdp.logn.model.TSysTimerJobSqlInsertUpdate;
import com.gasq.bdp.logn.model.TSysTimerLog;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TSysUserExample;
import com.gasq.bdp.logn.service.EmailManager;
import com.gasq.bdp.logn.service.TSysTimerLogService;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.utils
 * @creatTime 2017年11月27日下午1:33:02
 * @remark 
 */
public class WorkFlowUtil {
	
	static Logger logger = LoggerFactory.getLogger(WorkFlowUtil.class);
	/**
	 * 获取预编译语句值
	 * @param columns
	 * @param keys 
	 * @param value
	 * @return
	 * @throws WorkFlowJobException
	 */
	public static Object[] collectPSValue(List<TSysSqlUpdateColumns> columns,List<TSysSqlUpdateKeys> keys, Set<Entry<String,Object>> inputObjSet) throws WorkFlowJobException {
		Object[] objs = new Object[columns.size()+keys.size()];
		int i = 0;
		for (TSysSqlUpdateColumns updateColumns : columns) {
			for (Entry<String, Object> entry : inputObjSet) {
				String code = updateColumns.getDataCode();
				String key = entry.getKey();
				if(code.equals(key)) {
					Object column = parseSqlType(updateColumns.getType(),entry.getValue());
					objs[i]=column;
					break;
				}
			}
			i++;
		}
		if(keys.size()>0) {
			for (TSysSqlUpdateKeys key : keys) {
				for (Entry<String, Object> entry : inputObjSet) {
					String code = key.getDataCode1();
					String mkey = entry.getKey();
					if(code.equals(mkey)) {
						Object column = parseSqlType(key.getType(),entry.getValue());
						objs[i]=column;
						break;
					}
				}
				i++;
			}
		}
		return objs;
	}
	/**
	 * 处理sql类型
	 * @param tSysSqlUpdateColumns
	 * @param value
	 * @return
	 * @throws WorkFlowJobException
	 */
	public static Object parseSqlType(int type,Object value) throws WorkFlowJobException {
		try {
			switch (type) {
			case 1://整型
				return Integer.parseInt(value.toString());
			case 2://浮点型
				return Float.parseFloat(value.toString());
			case 3://字符串
				if(value==null) return null;
				return value.toString();
			case 4://日期
				if(value instanceof Date) {
					Date d = (Date)value;
//					return DateUtil.getDateStr(d, DateUtil.TIME_DEFAULT_FORMAT);
					return d;
				}else if(value instanceof String) {
					String s = (String)value;
					if(s.indexOf("-")!=-1 || s.indexOf("/")!=-1) {
//						return s;
						return DateUtil.parseStr(s);
					}
				}
			case 5://时间戳
				if(value instanceof String) {
					Long dv = Long.parseLong(value.toString());
					Date d = new Date(dv);
//					return DateUtil.getDateStr(d, DateUtil.TIME_DEFAULT_FORMAT);
					return new Timestamp(d.getTime());
				}else if(value instanceof Timestamp) {
					Timestamp ts = (Timestamp) value;
//					return DateUtil.getDateStr(DateUtil.timestamp2Date(ts), DateUtil.TIME_DEFAULT_FORMAT);
					return ts;
				}else if(value instanceof Long) {
//					Date d = new Date((long) value);
//					return DateUtil.getDateStr(d, DateUtil.TIME_DEFAULT_FORMAT);
					return new Timestamp((long) value);
				}
			default:
				return value.toString();
			}
		} catch (Exception e) {
			throw new WorkFlowJobException("转换对象类型失败 类型："+type+"  ----转换的值："+value, e);
		}
	}
	/**
	 * 根据类型获取条件语句
	 * @param type
	 * @param val
	 * @return
	 */
	public static String getParamsTypeSql(int type,Object val) {
		StringBuffer sb = new StringBuffer();
		switch (type) {
		case 1:
			sb.append(val);
			break;
		case 2:
			sb.append(val);
			break;
		case 3:
			sb.append(" '").append(val).append("' ");
			break;
		case 4:
			sb.append(" '").append(val).append("' ");
			break;
		case 5:
			sb.append(" '").append(val).append("' ");
			break;
		default:
			sb.append("'").append(val).append("'");
			break;
		}
		return sb.toString();
	}
	/**
	 * 根据code编码从输入参数中获取对应的值
	 * @param code
	 * @param inputObj
	 * @return
	 */
	public static Object getParamVal(String code,Set<Entry<String,Object>> inputObjSet) {
		for (Entry<String, Object> entry : inputObjSet) {
			String key = entry.getKey();
			if(code.equals(key)) {
				return entry.getValue();
			}
		}
		return null;
	}
	/**
	 * 整理插入预编译语句
	 * @param insertUpdate
	 * @param inputObj
	 * @param columns
	 * @param keys 
	 * @return
	 * @throws WorkFlowJobException
	 */
	public static String handlePSInsert(TSysTimerJobSqlInsertUpdate insertUpdate,List<TSysSqlUpdateColumns> columns, List<TSysSqlUpdateKeys> keys) throws WorkFlowJobException {
		StringBuffer csb = new StringBuffer("insert into ");
		StringBuffer vsb = new StringBuffer();
		csb.append(insertUpdate.getTableName()).append("(");
		vsb.append("value(");
		if(columns.size()>0) {//更新字段
			int i = 1;
			for (TSysSqlUpdateColumns tSysSqlUpdateColumns : columns) {
				if(i==columns.size()) {
					if(keys.size()>0) {
						csb.append("`"+tSysSqlUpdateColumns.getCode()+"`, ");
						vsb.append("?,");
						i = 1;
						for (TSysSqlUpdateKeys tSysSqlUpdateKeys : keys) {
							String code = tSysSqlUpdateKeys.getDataCode1();
							if(i==keys.size()) {
								csb.append("`"+code+"`)");
								vsb.append("?)");
							}else {
								csb.append("`"+code+"` ").append(",");
								vsb.append("?,");
							}
							i++;
						}
					}else {
						csb.append("`"+tSysSqlUpdateColumns.getCode()+"`, ");
						vsb.append("?,");
					}
				}else {
					csb.append("`"+tSysSqlUpdateColumns.getCode()+"`,");
					vsb.append("?,");
				}
				i++;
			}
		}else {//更新或插入字段为空
			throw new WorkFlowJobException("更新或插入字段为空");
		}
		return csb.append(vsb.toString()).toString();
	}

	
	/**
	 * 将参数转换为键值对list<Map>
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map> transformParams(String ipcode,Object params){
		List<Map> listmap = null;
		Map<String,Object> map = null;
		if(params.getClass().isArray() || params instanceof java.util.List) {
			listmap = CommonUtils.json2List(CommonUtils.BeanToJSON(params), Map.class);
			if(listmap.size()==1) {
				listmap = CommonUtils.json2List(CommonUtils.BeanToJSON(listmap.get(0)), Map.class);
			}
		}else {
			map = (Map<String, Object>) CommonUtils.JsonToBean(CommonUtils.BeanToJSON(params), Map.class);
			if(StringUtils.isNotBlank(ipcode) && map.containsKey(ipcode)) {
				listmap = CommonUtils.json2List(CommonUtils.BeanToJSON(map.get(ipcode)), Map.class);
			}else {
				listmap = new ArrayList<Map>();
				listmap.add(map);
			}
		}
		return listmap;
	}
	/**
	 * 获取where条件语句
	 * @param keys
	 * @param inputObj
	 * @return
	 * @throws WorkFlowJobException
	 */
	public static String assemblePSWhereSql(List<TSysSqlUpdateKeys> keys) throws WorkFlowJobException {
		StringBuffer sb = new StringBuffer(" where 1=1 ");
			for (TSysSqlUpdateKeys tSysSqlUpdateKeys : keys) {
				String code = tSysSqlUpdateKeys.getCode();
				String symbol = tSysSqlUpdateKeys.getCompareSymbol();
				sb.append(" and ").append("`"+code+"`").append(symbol).append("?");
				if(symbol.equals("BETWEEN")) {//组装between and语句
					sb.append(" and ").append("? ");
				}
			}
		return sb.toString();
	}
	/**
	 * 获取where条件语句
	 * @param keys
	 * @param inputObj
	 * @return
	 * @throws WorkFlowJobException
	 */
	public static String assembleWhereSql(List<TSysSqlUpdateKeys> keys,Set<Entry<String,Object>> inputObjSet) throws WorkFlowJobException {
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		Object[] vkeys = collectWhereCondition(keys, inputObjSet);
		if(vkeys.length>0) {
			int i = 0;
			for (TSysSqlUpdateKeys tSysSqlUpdateKeys : keys) {
				String code = tSysSqlUpdateKeys.getCode();
				String symbol = tSysSqlUpdateKeys.getCompareSymbol();
				sb.append(" and ").append(code).append(symbol);
				int type = tSysSqlUpdateKeys.getType();
				Object val = vkeys[i];
				String rsval = getParamsTypeSql(type,val);
				sb.append(rsval);
				if(symbol.equals("BETWEEN")) {//组装between and语句
					Object code2 = getParamVal(tSysSqlUpdateKeys.getDataCode2(), inputObjSet);
					String rsval2 = getParamsTypeSql(type,code2);
					sb.append(" and ").append(rsval2);
				}
				i++;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 获取条件对应的数据值集合
	 * @param keys
	 * @param inputObj
	 * @return
	 * @throws WorkFlowJobException
	 */
	public static Object[] collectWhereCondition(List<TSysSqlUpdateKeys> keys,Set<Entry<String,Object>> inputObjSet) throws WorkFlowJobException {
		Object[] objs = new Object[keys.size()];
		int i = 0;
		for (TSysSqlUpdateKeys tSysSqlUpdateKeys : keys) {
			for (Entry<String, Object> entry : inputObjSet) {
				String key = entry.getKey();
				if(tSysSqlUpdateKeys.getDataCode1().equals(key)) {
					Object column = WorkFlowUtil.parseSqlType(tSysSqlUpdateKeys.getType(),entry.getValue());
					objs[i]=column;
					break;
				}
			}
			i++;
		}
		return objs;
	}
	/**
	 * 组装插入或更新语句
	 * @param insertUpdate
	 * @param inputObj
	 * @param keys
	 * @param columns
	 * @param where 
	 * @param count 
	 * @return
	 * @throws WorkFlowJobException
	 * @throws SQLException 
	 */
	public static String handleInsertOrUpdate(TSysTimerJobSqlInsertUpdate insertUpdate,Set<Entry<String,Object>> inputObjSet, List<TSysSqlUpdateKeys> keys, List<TSysSqlUpdateColumns> columns, String where, int count) throws WorkFlowJobException, SQLException {
		String psSql = null;
		if(keys.size()>0) {
			if(count>0) {//存在：更新
				psSql = handlePSInsertOrUpdate(insertUpdate,inputObjSet,columns);
				psSql+=where;
			}else {//插入
				psSql = handlePSInsert(insertUpdate,columns,keys);
			}
		}else {//条件为空--注：更新全表
			psSql = handlePSInsertOrUpdate(insertUpdate,inputObjSet,columns);
		}
		return psSql;
	}
	/**
	 * 组装预编译插入或更新语句
	 * @param insertUpdate
	 * @param inputObjSet
	 * @param columns
	 * @return
	 * @throws WorkFlowJobException
	 */
	public static String handlePSInsertOrUpdate(TSysTimerJobSqlInsertUpdate insertUpdate,Set<Entry<String,Object>> inputObjSet,List<TSysSqlUpdateColumns> columns) throws WorkFlowJobException {
		StringBuffer csb = new StringBuffer("update ");
		csb.append(insertUpdate.getTableName());
		if(inputObjSet==null) {//传入的参数为null
			if(insertUpdate.getInputParams()!=null) throw new WorkFlowJobException("传入的参数为null！");
			
		}else {
			if(insertUpdate.getInputParams()==null) throw new WorkFlowJobException("配置的输入参数错误！");
			if(columns.size()>0) {//更新字段
				int i = 1;
				for (TSysSqlUpdateColumns tSysSqlUpdateColumns : columns) {
					if(i==columns.size()) {
						csb.append("`"+tSysSqlUpdateColumns.getCode()+"`").append("=").append("? ");
					}else {
						if(i==1) {
							csb.append(" set ").append("`"+tSysSqlUpdateColumns.getCode()+"`").append("=").append("?, ");
						}else {
							csb.append("`"+tSysSqlUpdateColumns.getCode()+"`").append("=").append("?, ");
						}
					}
					i++;
				}
			}else {//更新或插入字段为空
				throw new WorkFlowJobException("更新或插入字段为空");
			}
		}
		return csb.toString();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String handlePSUpdate(TSysTimerJobSqlInsertUpdate insertUpdate,List<Map> listipobj, List<TSysSqlUpdateColumns> columns,List<TSysSqlUpdateKeys> keys) throws WorkFlowJobException {
		String where = WorkFlowUtil.assemblePSWhereSql(keys);
		Set<Entry<String,Object>> inputObjSet = listipobj.get(0).entrySet();
		String psSql = handlePSInsertOrUpdate(insertUpdate,inputObjSet,columns);
		return psSql + where ;
	}
	/**
	 * 检查是否有某一个权限 格式：eg：q_admin,sadmin
	 */
	public static Boolean hasAnyRoles(CharSequence... elements) {
		Object[] array = SystemUserInfo.getSystemUser().getRole().stream().map(r->r.getRoleSign()).toArray();
		List<Object> asList = Arrays.asList(array);
		for (CharSequence r : elements) {
			if(asList.contains(r)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 检查是否没有某一个权限 格式：eg：q_admin,sadmin
	 */
	public static Boolean hasNoAnyRoles(CharSequence... elements) {
		Object[] array = SystemUserInfo.getSystemUser().getRole().stream().map(r->r.getRoleSign()).toArray();
		List<Object> asList = Arrays.asList(array);
		for (CharSequence r : elements) {
			if(asList.contains(r)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 检查是否有某一组权限 格式：eg：q_admin,sadmin
	 */
	public static Boolean hasAllRoles(CharSequence... elements) {
		Object[] array = SystemUserInfo.getSystemUser().getRole().stream().map(r->r.getRoleSign()).toArray();
		List<Object> asList = Arrays.asList(array);
		boolean f = true;
		for (CharSequence r : elements) {
			if(asList.contains(r)) {
				f = true;
			}else {
				f = false;
				break;
			}
		}
		return f;
	}
	/**
	 * 执行前台回访
	 */
	public static void executeReceptionRemind(String name,String group) {
		try {
			TLtnCustomerExample example = new TLtnCustomerExample();
			example.createCriteria().andStatusEqualTo(1).andCompanyIdEqualTo(Integer.parseInt(group)).andRemindtimeEqualTo(DateUtil.parseStr(DateUtil.getDiyStrDateTime(0), DateUtil.DATE_DEFAULT_FORMAT));
			List<TLtnCustomer> ltnCustomers = SpringApplicationUtils.getBean(TLtnCustomerMapper.class).selectByExample(example);
			if(ltnCustomers!=null && ltnCustomers.size()>0) {
				String title = "回访提醒！";
				StringBuffer context = new StringBuffer();
				for (TLtnCustomer customer : ltnCustomers) {
					TSysUser sysUser = SpringApplicationUtils.getBean(TSysUserMapper.class).selectByPrimaryKey((long)customer.getCounsoler());
					String email = sysUser.getEmail();
					if(email==null || email=="") continue;
					String[] toEmailNames = {email};
					Map<String, Object> queryAmountSum = SpringApplicationUtils.getBean(TLtnCustomerMapper.class).getConsumptionProjects(customer.getId());
					if(queryAmountSum!=null && !queryAmountSum.isEmpty()) {
						BigDecimal bd = new BigDecimal(queryAmountSum.get("total_amount").toString());
						String projects = queryAmountSum.get("project_name").toString();
						context.append("你好,").append(sysUser.getNickname()).append("医师").append(": \n\n").append("  ●明天有您的回访客户:\n    ◎客户姓名：").append(customer.getCustomername()).append("\n    ◎联系方式：").append(customer.getPhonenumb()).append("\n    ◎消费项目：").append(projects).append("\n    ◎消费总金额：").append(bd.doubleValue()).append("\n    ◎上次治疗时间：").append(DateUtil.getDateStr(customer.getCreatetime(),DateUtil.TIME_DEFAULT_FORMAT)).append("\n\n  请您及时处理，如有问题请联系《客户消费信息平台》管理人员。\n\n").append("发送者：客户消费信息平台").toString();
						SpringApplicationUtils.getBean(EmailManager.class).sendHtmlMails(toEmailNames, title, context.toString());
					}
				}
				context = null;
				ltnCustomers = null;
				String mess = "定时器回访提醒运行成功。邮件已经发送！";
				logger.info(mess);
				SpringApplicationUtils.getBean(TSysTimerLogService.class).add(new TSysTimerLog(group + "->" + name ,InitProperties.TIMMERLOG_SUCCESS,null,null,null,0,DateUtil.getSysCurrentDate(),InitProperties.DEFAULT_USERNAME,mess));
			}else {
				String mess = "定时器 [" + group + "->" + name + "] 运行失败！没有找到对应的定时器配置。";
				logger.error(mess);
				SpringApplicationUtils.getBean(TSysTimerLogService.class).add(new TSysTimerLog(group + "->" + name ,InitProperties.TimmerLog_FAIL,null,null,null,0,DateUtil.getSysCurrentDate(),InitProperties.DEFAULT_USERNAME,mess));
			}
		} catch (Exception e) {
			logger.error("定时器回访提醒 [" + group + "->" + name + "] Exception:", e);
			SpringApplicationUtils.getBean(TSysTimerLogService.class).add(new TSysTimerLog(group + "->" + name ,InitProperties.TimmerLog_FAIL,null,null,null,0,DateUtil.getSysCurrentDate(),InitProperties.DEFAULT_USERNAME,"定时器回访提醒 [" + group + "->" + name + "] Exception:"+e));
			return ;
		}
		
	}
	public static void executeBackgroupRemind(String name, String group) {
		try {
			TCustomerSubscribeExample example = new TCustomerSubscribeExample();
			example.createCriteria().andStatusEqualTo(0).andCompanyIdEqualTo(Integer.parseInt(group)).andSubscribeDateLessThan(DateUtil.getSysCurrentDate());
			List<TCustomerSubscribe> customerSubscribes = SpringApplicationUtils.getBean(TCustomerSubscribeMapper.class).selectByExample(example);
			if(customerSubscribes!=null && customerSubscribes.size()>0) {
				String title = "回访提醒！";
				StringBuffer context = new StringBuffer();
				for (TCustomerSubscribe customer : customerSubscribes) {
					TSysUserExample sysUserExample = new TSysUserExample();
					sysUserExample.createCriteria().andUsernameEqualTo(customer.getCreateUser()).andCompanyidEqualTo(customer.getCompanyId());
					List<TSysUser> sysUsers = SpringApplicationUtils.getBean(TSysUserMapper.class).selectByExample(sysUserExample);
					if(sysUsers!=null && sysUsers.size()>0) {
						TSysUser sysUser = sysUsers.get(0);
						String email = sysUser.getEmail();
						if(email==null || email=="") continue;
						String[] toEmailNames = {email};
						Map<String, Object> queryAmountSum = SpringApplicationUtils.getBean(TLtnCustomerMapper.class).getConsumptionProjects(customer.getId());
						if(queryAmountSum!=null && !queryAmountSum.isEmpty()) {
							BigDecimal bd = new BigDecimal(queryAmountSum.get("total_amount").toString());
							String projects = queryAmountSum.get("project_name").toString();
							context.append("你好,").append(sysUser.getNickname()).append(": \n\n").append("  ●您有未到店就诊的预约客户:\n    ◎客户姓名：").append(customer.getCustomerName()).append("\n    ◎联系方式：").append(customer.getCustomerName()).append("\n    ◎消费项目：").append(projects).append("\n    ◎消费总金额：").append(bd.doubleValue()).append("\n    ◎门诊时间：").append(DateUtil.getDateStr(customer.getSubscribeDate(),DateUtil.TIME_DEFAULT_FORMAT)).append("\n\n  请您及时处理，如有问题请联系《客户消费信息平台》管理人员。\n\n").append("发送者：客户消费信息平台").toString();
							SpringApplicationUtils.getBean(EmailManager.class).sendHtmlMails(toEmailNames, title, context.toString());
						}
					}
				}
				context = null;
				String mess = "定时器回访提醒运行成功。邮件已经发送！";
				logger.info(mess);
				SpringApplicationUtils.getBean(TSysTimerLogService.class).add(new TSysTimerLog(group + "->" + name ,InitProperties.TIMMERLOG_SUCCESS,null,null,null,0,DateUtil.getSysCurrentDate(),InitProperties.DEFAULT_USERNAME,mess));
			}else {
				String mess = "定时器 [" + group + "->" + name + "] 运行失败！没有找到对应的定时器配置。";
				logger.error(mess);
				SpringApplicationUtils.getBean(TSysTimerLogService.class).add(new TSysTimerLog(group + "->" + name ,InitProperties.TimmerLog_FAIL,null,null,null,0,DateUtil.getSysCurrentDate(),InitProperties.DEFAULT_USERNAME,mess));
			}
		} catch (Exception e) {
			logger.error("定时器回访提醒 [" + group + "->" + name + "] Exception:", e);
			SpringApplicationUtils.getBean(TSysTimerLogService.class).add(new TSysTimerLog(group + "->" + name ,InitProperties.TimmerLog_FAIL,null,null,null,0,DateUtil.getSysCurrentDate(),InitProperties.DEFAULT_USERNAME,"定时器回访提醒 [" + group + "->" + name + "] Exception:"+e));
			return ;
		}
	}
}
