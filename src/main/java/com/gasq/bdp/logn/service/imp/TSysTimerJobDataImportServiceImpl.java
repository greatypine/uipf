package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.mapper.TSysTimerJobDataImportMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysTimerJobDataImport;
import com.gasq.bdp.logn.model.TSysTimerJobDataImportExample;
import com.gasq.bdp.logn.model.TSysTimerJobDataImportExample.Criteria;
import com.gasq.bdp.logn.service.TSysTimerJobDataImportService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年11月30日上午9:41:29
 * @remark
 */
@Service
public class TSysTimerJobDataImportServiceImpl implements TSysTimerJobDataImportService {
	@Autowired TSysTimerJobDataImportMapper dataImportMapper;

	@Override
	public Map<String, Object> queryPagingList(TSysTimerJobDataImport bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysTimerJobDataImportExample example = new TSysTimerJobDataImportExample();
		Criteria c = example.createCriteria();
		if(bean.getFilename()!=null && !"".equals(bean.getFilename())) {
			c.andFilenameEqualTo(bean.getFilename());
		}
		if(bean.getRemark()!=null && !"".equals(bean.getRemark())) {
			c.andRemarkLike("%"+bean.getRemark()+"%");
		}
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getType()!=null) {
			c.andTypeEqualTo(bean.getType());
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
		int count = (int) dataImportMapper.countByExample(example);
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
			list = dataImportMapper.querySqlpagingList(params);
		}else {
			list = new ArrayList<Map<String,Object>>(); 
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysTimerJobDataImport bean) {
		bean.setUpdatetime(DateUtil.getSysCurrentDate());
		bean.setGroupname(SystemUserInfo.getSystemUser().getUser().getGroupName());
		if(bean.getId()!=null) {
			bean.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			dataImportMapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			bean.setUpdatetime(DateUtil.getSysCurrentDate());
			dataImportMapper.insertSelective(bean);
			return true;
		}
	}

	@Override
	public boolean delete(int id) {
		return dataImportMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public long countByExample(TSysTimerJobDataImportExample example) {
		return dataImportMapper.countByExample(example);
	}

	@Override
	public List<TSysTimerJobDataImport> selectByExample(TSysTimerJobDataImportExample example) {
		return dataImportMapper.selectByExample(example);
	}

}
