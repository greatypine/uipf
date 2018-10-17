/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.mapper.TMessageMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TMessage;
import com.gasq.bdp.logn.model.TMessageExample;
import com.gasq.bdp.logn.service.TMessageService;
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
public class TMessageServiceImpl implements TMessageService {
	@Autowired TMessageMapper mapper;

	@Override
	public boolean delete(int id) {
		return mapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public int add(TMessage record) {
		return mapper.insertSelective(record);
	}

	@Override
	public List<TMessage> selectByExample(TMessageExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public TMessage selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TMessage bean) {
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
	public boolean saveOrUpdate(TMessage bean) {
		bean.setUpdateTime(DateUtil.getSysCurrentDate());
		bean.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
		if(bean.getId()!=null) {
			bean.setUpdateUser(SystemUserInfo.getSystemUser().getUser().getNickname());
			mapper.updateByPrimaryKeySelective(bean);
		}else {
			bean.setCreateTime(DateUtil.getSysCurrentDate());
			bean.setCreateUser(SystemUserInfo.getSystemUser().getUser().getNickname());
			mapper.insertSelective(bean);
		}
		return true;
	}
}
