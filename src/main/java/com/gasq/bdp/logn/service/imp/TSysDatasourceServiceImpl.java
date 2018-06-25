/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.component.MyDAO;
import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.mapper.TSysDatasourceMapper;
import com.gasq.bdp.logn.model.SystemUser;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysDatasource;
import com.gasq.bdp.logn.model.TSysDatasourceExample;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysDatasourceExample.Criteria;
import com.gasq.bdp.logn.service.TSysDatasourceService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年10月31日上午11:33:35
 * @remark 
 */
@Service
public class TSysDatasourceServiceImpl implements TSysDatasourceService {
	@Autowired TSysDatasourceMapper mapper;
	@Autowired MyDAO dao;

	@Override
	public boolean delete(int id) {
		return mapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public int add(TSysDatasource record) {
		return mapper.insertSelective(record);
	}

	@Override
	public List<TSysDatasource> selectByExample(TSysDatasourceExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public TSysDatasource selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TSysDatasource bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysDatasourceExample example = new TSysDatasourceExample();
		Criteria c = example.createCriteria();
		if(bean.getCode()!=null && !"".equals(bean.getCode())) {
			c.andCodeLike("%"+bean.getCode()+"%");
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
		int count = (int) mapper.countByExample(example);
		List<TSysDatasource> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		RowBounds rowBounds = new RowBounds(start, bean.getRows());
		if(count>0) {
			list = mapper.selectByExampleWithRowbounds(example, rowBounds);
		}else {
			list = new ArrayList<TSysDatasource>(); 
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysDatasource bean) {
		bean.setUpdatetime(DateUtil.getSysCurrentDate());
		bean.setGroupname(SystemUserInfo.getSystemUser().getUser().getGroupName());
		if(bean.getId()!=null) {
			bean.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			mapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			bean.setCreateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			mapper.insertSelective(bean);
			return true;
		}
	}

	@Override
	public boolean check(TSysDatasource bean) throws WorkFlowJobException, SQLException {
		Connection conn = dao.getConnection(bean);
		boolean b = (conn!=null)?true:false;
		if(conn!=null) conn.close();
		return b;
	}

	@Override
	public List<Map<String, Object>> queryDataBaseTree(TSysDatasource bean) {
		SystemUser suser = SystemUserInfo.getSystemUser();
		String username = suser.getUser().getUsername();
		String groupname = suser.getUser().getGroupName();
		bean.setGroupname(groupname);
		List<TSysRole> roles = SystemUserInfo.getSystemUser().getRole();
		boolean flag = false;
		for (TSysRole tSysRole : roles) {
			if(tSysRole.getRoleSign().equals("sadmin") || tSysRole.getRoleSign().equals("admin")) {
				flag = true;
				break;
			}
		}
		if(!flag) {
			bean.setCreateuser(username);
		}
    	return mapper.queryDataBaseTree(bean);
	}

	@Override
	public List<Map<String, Object>> getColumnsByTable(String dbcode,String tablename) throws WorkFlowJobException {
		if(dbcode==null || tablename==null)return null;
		Connection conn = dao.getConnection(dbcode);
		return dao.query(conn, "select column_name as id,column_name as text,column_type as type from Information_schema.columns where table_Name = ?",new String[]{tablename});
	}

	@Override
	public List<Map<String, Object>> queryTablesByDb(String dbcode) throws WorkFlowJobException {
		if(dbcode==null)return null;
		Connection conn = dao.getConnection(dbcode);
		TSysDatasourceExample example = new TSysDatasourceExample();
		example.createCriteria().andCodeEqualTo(dbcode);
		List<TSysDatasource> listbean = mapper.selectByExample(example);
		if(listbean.size()>0) {
			String url = listbean.get(0).getUrl();
			String tempurl = url.substring(url.indexOf("//")+2, url.indexOf("?"));
			String dbname = tempurl.substring(tempurl.indexOf("/")+1);
			return dao.query(conn,"select table_name as id,table_name as text from information_schema.tables where table_schema=? and table_type='base table'",new String[]{dbname});
		}
		return null;
	}

}
