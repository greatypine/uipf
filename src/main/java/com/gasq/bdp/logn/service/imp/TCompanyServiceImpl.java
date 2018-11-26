/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.mapper.TCompanyMapper;
import com.gasq.bdp.logn.mapper.TSysUserMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.model.TCompanyExample;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TSysUserExample;
import com.gasq.bdp.logn.provider.Ilogger;
import com.gasq.bdp.logn.service.TCompanyService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年10月31日上午11:33:35
 * @remark 
 */
@Service
public class TCompanyServiceImpl implements TCompanyService {
	@Autowired TCompanyMapper mapper;
	@Autowired TSysUserMapper userMapper;

	@Override
	public boolean delete(int id) {
		return mapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public int add(TCompany record) {
		return mapper.insertSelective(record);
	}

	@Override
	public List<TCompany> selectByExample(TCompanyExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public TCompany selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Ilogger(value="分页查询公司信息")
	@Override
	public Map<String, Object> queryPagingList(TCompany bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		Map<String, Object> params= new  HashMap<String, Object>();
		if(bean.getName()!=null) {
			params.put("name", bean.getName());
		}
		if(bean.getStatus()!=null) {
			params.put("status", bean.getStatus());
		}
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Map<String, Object>> listmaps = mapper.queryPagingList(params);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveOrUpdate(TCompany bean) throws WorkFlowStateException{
		try {
			bean.setUpdatetime(DateUtil.getSysCurrentDate());
			if(bean.getId()!=null) {
				if(!bean.getStatus()) {//注销
					//先注销公司下面所有用户
					TSysUserExample example = new TSysUserExample();
					example.createCriteria().andCompanyidEqualTo(bean.getId());
					List<TSysUser> list = userMapper.selectByExample(example);
					for (TSysUser user : list) {
						user.setIsvalid(false);
						userMapper.updateByPrimaryKeySelective(user);
					}
				}
				bean.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getNickname());
				mapper.updateByPrimaryKeySelective(bean);
			}else {
				bean.setCreatetime(DateUtil.getSysCurrentDate());
				bean.setCreateuser(SystemUserInfo.getSystemUser().getUser().getNickname());
				mapper.insertSelective(bean);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> queryMapBeanList(TCompany bean) {
		return mapper.queryMapBeanList(bean);
	}
}
