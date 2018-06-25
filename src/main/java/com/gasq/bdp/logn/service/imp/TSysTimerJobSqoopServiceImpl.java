/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.mapper.TSysTimerJobSqoopMapper;
import com.gasq.bdp.logn.model.TSysTimerJobSqoop;
import com.gasq.bdp.logn.model.TSysTimerJobSqoopExample;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月13日上午10:14:37
 * @remark 
 */
@Service
public class TSysTimerJobSqoopServiceImpl implements com.gasq.bdp.logn.service.TSysTimerJobSqoopService {
	@Autowired TSysTimerJobSqoopMapper sqoopMapper;

	@Override
	public long countByExample(TSysTimerJobSqoopExample example) {
//		String username = SystemUserInfo.getSystemUser().getUser().getUsername();
//		String groupname = SystemUserInfo.getSystemUser().getUser().getGroupName();
//		if(!"".equals(bean.getGroupname()) && bean.getGroupname()!=null) {
//			c.andGroupnameEqualTo(bean.getGroupname());
//		}else {
//			c.andGroupnameEqualTo(groupname);
//		}
//		if(bean.getCreateuser()!=null && !"".equals(bean.getCreateuser())){
//			c.andCreateuserEqualTo(bean.getCreateuser());
//		}else {
//			List<TSysRole> roles = SystemUserInfo.getSystemUser().getRole();
//			boolean flag = false;
//			for (TSysRole tSysRole : roles) {
//				if(tSysRole.getRoleSign().equals("sadmin") || tSysRole.getRoleSign().equals("admin")) {
//					flag = true;
//					break;
//				}
//			}
//			if(!flag) {
//				c.andCreateuserEqualTo(username);
//			}
//		}
		return sqoopMapper.countByExample(example);
	}

	@Override
	public boolean delete(int id) {
		return sqoopMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public List<TSysTimerJobSqoop> selectByExample(TSysTimerJobSqoopExample example) {
		return sqoopMapper.selectByExample(example);
	}

	@Override
	public TSysTimerJobSqoop selectByPrimaryKey(Integer id) {
		return sqoopMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TSysTimerJobSqoop bean) {
		return null;
	}

	@Override
	public boolean saveOrUpdate(TSysTimerJobSqoop bean) {
		return false;
	}

}
