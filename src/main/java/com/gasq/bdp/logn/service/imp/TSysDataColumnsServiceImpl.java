package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.mapper.TSysDataColumnsMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysDataColumns;
import com.gasq.bdp.logn.model.TSysDataColumnsExample;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysDataColumnsExample.Criteria;
import com.gasq.bdp.logn.service.TSysDataColumnsService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年11月13日下午7:56:17
 * @remark 
 */
@Service
public class TSysDataColumnsServiceImpl implements TSysDataColumnsService {
	@Autowired TSysDataColumnsMapper dataColumnsMapper;
	
	@Override
	public List<TSysDataColumns> selectByExample(TSysDataColumnsExample example) {
		return dataColumnsMapper.selectByExample(example);
	}

	@Override
	public TSysDataColumns selectByPrimaryKey(Integer id) {
		return dataColumnsMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TSysDataColumns bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysDataColumnsExample example = new TSysDataColumnsExample();
		Criteria c = example.createCriteria();
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getDataId()!=null) {
			c.andDataIdEqualTo(bean.getDataId());
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
		int count = (int) dataColumnsMapper.countByExample(example);
		List<TSysDataColumns> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		RowBounds rowBounds = new RowBounds(start, bean.getRows());
		if(count>0) {
			list = dataColumnsMapper.selectByExampleWithRowbounds(example, rowBounds);
		}else {
			list = new ArrayList<TSysDataColumns>(); 
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysDataColumns bean) {
		bean.setUpdatetime(DateUtil.getSysCurrentDate());
		bean.setGroupname(SystemUserInfo.getSystemUser().getUser().getGroupName());
		if(bean.getId()!=null) {
			bean.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			dataColumnsMapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			bean.setCreateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			dataColumnsMapper.insertSelective(bean);
			return true;
		}
	}

	@Override
	public boolean delete(int id) {
		return dataColumnsMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public List<Map<String, Object>> getCompareSymbol() {
		return dataColumnsMapper.getCompareSymbol();
	}

	@Override
	public List<TSysDataColumns> queryList(TSysDataColumns bean) {
		TSysDataColumnsExample example = new TSysDataColumnsExample();
		Criteria c = example.createCriteria();
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getDataId()!=null) {
			c.andDataIdEqualTo(bean.getDataId());
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		if(bean.getCreatetime()!=null) {
			c.andCreatetimeGreaterThanOrEqualTo(bean.getCreatetime());
		}
		example.setOrderByClause(" createTime desc ");
		return dataColumnsMapper.selectByExample(example);
	}

}
