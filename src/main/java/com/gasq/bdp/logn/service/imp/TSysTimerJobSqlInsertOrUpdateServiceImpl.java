package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.mapper.TSysDataColumnsMapper;
import com.gasq.bdp.logn.mapper.TSysTimerJobSqlInsertUpdateMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysDataColumns;
import com.gasq.bdp.logn.model.TSysDataColumnsExample;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysSqlUpdateColumns;
import com.gasq.bdp.logn.model.TSysSqlUpdateKeys;
import com.gasq.bdp.logn.model.TSysTimerJobSqlInsertUpdate;
import com.gasq.bdp.logn.model.TSysTimerJobSqlInsertUpdateExample;
import com.gasq.bdp.logn.model.TSysTimerJobSqlInsertUpdateExample.Criteria;
import com.gasq.bdp.logn.service.TSysTimerJobSqlInsertOrUpdateService;
import com.gasq.bdp.logn.service.UpdateColumnsService;
import com.gasq.bdp.logn.service.UpdateKeysService;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月13日上午10:14:37
 * @remark 
 */
@Service
public class TSysTimerJobSqlInsertOrUpdateServiceImpl implements TSysTimerJobSqlInsertOrUpdateService {
	@Autowired TSysTimerJobSqlInsertUpdateMapper sqlInsertUpdateMapper;
	@Autowired UpdateKeysService keysService;
	@Autowired UpdateColumnsService columnsService;
	@Autowired TSysDataColumnsMapper dataColumnsMapper;

	@Override
	public Map<String, Object> queryPagingList(TSysTimerJobSqlInsertUpdate bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysTimerJobSqlInsertUpdateExample example = new TSysTimerJobSqlInsertUpdateExample();
		Criteria c = example.createCriteria();
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getRemark()!=null && !"".equals(bean.getRemark())) {
			c.andRemarkLike("%"+bean.getRemark()+"%");
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		if(bean.getCreatetime()!=null) {
			c.andCreatetimeGreaterThanOrEqualTo(bean.getCreatetime());
		}
		String username = SystemUserInfo.getSystemUser().getUser().getUsername();
		String groupname = SystemUserInfo.getSystemUser().getUser().getGroupName();
		if(!"".equals(bean.getGroupname()) && bean.getGroupname()!=null) {
			c.andGroupnameEqualTo(bean.getGroupname());
		}else {
			c.andGroupnameEqualTo(groupname);
		}
		if(bean.getCreateuser()!=null && !"".equals(bean.getCreateuser())){
			c.andCreateuserEqualTo(bean.getCreateuser());
		}else {
			List<TSysRole> roles = SystemUserInfo.getSystemUser().getRole();
			boolean flag = false;
			for (TSysRole tSysRole : roles) {
				if(tSysRole.getRoleSign().equals("sadmin") || tSysRole.getRoleSign().equals("admin")) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				c.andCreateuserEqualTo(username);
			}
		}
		example.setOrderByClause(" createTime desc ");
		int count = (int) sqlInsertUpdateMapper.countByExample(example);
		List<Map<String,Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		if(count>0) {
			Map<String, Object> params= new  HashMap<String, Object>();
			params.put("index", start);
			params.put("pageSize", number);
			params.put("createuser", username);
			params.put("groupname", groupname);
			String role = "user";
			for (TSysRole tSysRole : SystemUserInfo.getSystemUser().getRole()) {
				if(tSysRole.getRoleSign().equals("sadmin") || tSysRole.getRoleSign().equals("admin")) {
					role = "admin";
					break;
				}
			}
			params.put("role", role);
			list = sqlInsertUpdateMapper.queryPagingList(params);
		}else {
			list = new ArrayList<Map<String,Object>>(); 
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysTimerJobSqlInsertUpdate bean) {
		bean.setUpdatetime(DateUtil.getSysCurrentDate());
		bean.setGroupname(SystemUserInfo.getSystemUser().getUser().getGroupName());
		if(bean.getId()!=null) {
			bean.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			sqlInsertUpdateMapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			bean.setCreateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			sqlInsertUpdateMapper.insertSelective(bean);
			return true;
		}
	}

	@Override
	public boolean delete(int id) {
		return sqlInsertUpdateMapper.deleteByPrimaryKey((long)id)>0?true:false;
	}

	@Override
	public List<TSysTimerJobSqlInsertUpdate> selectByExample(TSysTimerJobSqlInsertUpdateExample sqlqueryExample) {
		return sqlInsertUpdateMapper.selectByExample(sqlqueryExample);
	}

	@Override
	public long countByExample(TSysTimerJobSqlInsertUpdateExample example) {
		return sqlInsertUpdateMapper.countByExample(example);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveAll(String baseData, String keys, String columns) throws WorkFlowJobException {
		boolean flag = false;
		try {
			TSysTimerJobSqlInsertUpdate insertUpdate = null;
			if(StringUtils.isNotBlank(baseData)) {
				insertUpdate = (TSysTimerJobSqlInsertUpdate) CommonUtils.JsonToBean(baseData, TSysTimerJobSqlInsertUpdate.class);
			}
			if(insertUpdate!=null) {
				if(insertUpdate.getId()==null) {
					Integer id = sqlInsertUpdateMapper.getMaxId();
					insertUpdate.setId(id.longValue());
				}
			}
			int dataid = insertUpdate.getInputParams();
			List<TSysSqlUpdateKeys> keyList = null;
			if(StringUtils.isNotBlank(keys)) {
				keyList = CommonUtils.json2List(keys,TSysSqlUpdateKeys.class);
			}
			List<TSysSqlUpdateColumns> columnsList = null;
			if(StringUtils.isNotBlank(columns) && columns!="") {
				columnsList = CommonUtils.json2List(columns,TSysSqlUpdateColumns.class);
			}
			if(insertUpdate!=null) {
				flag = saveOrUpdate(insertUpdate);
				if(!flag) throw new WorkFlowJobException("插入或更新基本数据更新失败！");
			}
			if(keyList!=null) {
				for (TSysSqlUpdateKeys tSysSqlUpdateKeys : keyList) {
					TSysDataColumns dataColumns = null;
					tSysSqlUpdateKeys.setInsertUpdateJobId(insertUpdate.getId().intValue());
					if(tSysSqlUpdateKeys.getType()==null) {
						if(tSysSqlUpdateKeys.getDataCode1()!=null) {
							TSysDataColumnsExample example = new TSysDataColumnsExample();
							example.createCriteria().andCodeEqualTo(tSysSqlUpdateKeys.getDataCode1()).andDataIdEqualTo(dataid);
							List<TSysDataColumns> listdata = dataColumnsMapper.selectByExample(example);
							if(listdata.size()>0) {
								dataColumns = listdata.get(0);
								tSysSqlUpdateKeys.setType(dataColumns.getType());
							}
						}
					}
					String username = SystemUserInfo.getSystemUser().getUser().getUsername();
					String groupname = SystemUserInfo.getSystemUser().getUser().getGroupName();
					if("".equals(tSysSqlUpdateKeys.getGroupname()) || tSysSqlUpdateKeys.getGroupname()==null) {
						tSysSqlUpdateKeys.setGroupname(groupname);
					}
					if(tSysSqlUpdateKeys.getCreateuser()==null || "".equals(tSysSqlUpdateKeys.getCreateuser())){
						List<TSysRole> roles = SystemUserInfo.getSystemUser().getRole();
						boolean b = false;
						for (TSysRole tSysRole : roles) {
							if(tSysRole.getRoleSign().equals("sadmin") || tSysRole.getRoleSign().equals("admin")) {
								b = true;
								break;
							}
						}
						if(!b) {
							tSysSqlUpdateKeys.setCreateuser(username);
						}
					}
					flag = keysService.saveOrUpdate(tSysSqlUpdateKeys);
					if(!flag) throw new WorkFlowJobException("更新条件失败！");
				}
			}
			
			if(columnsList!=null) {
				for (TSysSqlUpdateColumns sqlUpdateColumns : columnsList) {
					if(sqlUpdateColumns.getDataId()==null && sqlUpdateColumns.getDataCode()!=null) {
						TSysDataColumnsExample example = new TSysDataColumnsExample();
						example.createCriteria().andCodeEqualTo(sqlUpdateColumns.getDataCode()).andDataIdEqualTo(dataid);
						List<TSysDataColumns> listdata = dataColumnsMapper.selectByExample(example);
						if(listdata.size()>0) {
							sqlUpdateColumns.setDataId(listdata.get(0).getId());
							if(sqlUpdateColumns.getType()==null) {
								sqlUpdateColumns.setType(listdata.get(0).getType());
							}
						}
					}
					if(sqlUpdateColumns.getInsertUpdateJobId()==null) {
						sqlUpdateColumns.setCreatetime(DateUtil.getSysCurrentDate());
						sqlUpdateColumns.setInsertUpdateJobId(insertUpdate.getId().intValue());
					}
					String username = SystemUserInfo.getSystemUser().getUser().getUsername();
					String groupname = SystemUserInfo.getSystemUser().getUser().getGroupName();
					if("".equals(sqlUpdateColumns.getGroupname()) || sqlUpdateColumns.getGroupname()==null) {
						sqlUpdateColumns.setGroupname(groupname);
					}
					if(sqlUpdateColumns.getCreateuser()==null || "".equals(sqlUpdateColumns.getCreateuser())){
						List<TSysRole> roles = SystemUserInfo.getSystemUser().getRole();
						boolean b = false;
						for (TSysRole tSysRole : roles) {
							if(tSysRole.getRoleSign().equals("sadmin") || tSysRole.getRoleSign().equals("admin")) {
								b = true;
								break;
							}
						}
						if(!b) {
							sqlUpdateColumns.setCreateuser(username);
						}
					}
					flag = columnsService.saveOrUpdate(sqlUpdateColumns);
					if(!flag) throw new WorkFlowJobException("更新字段失败！");
				}
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return flag;
	}
}
