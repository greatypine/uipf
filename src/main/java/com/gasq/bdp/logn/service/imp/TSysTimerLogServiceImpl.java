package com.gasq.bdp.logn.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.mapper.TSysTimerLogMapper;
import com.gasq.bdp.logn.model.TSysTimerLog;
import com.gasq.bdp.logn.model.TSysTimerLogExample;
import com.gasq.bdp.logn.model.TSysTimerLogExample.Criteria;
import com.gasq.bdp.logn.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年11月30日上午9:41:29
 * @remark
 */
@Service
public class TSysTimerLogServiceImpl implements com.gasq.bdp.logn.service.TSysTimerLogService {
	@Autowired TSysTimerLogMapper logMapper;

	@Override
	public Map<String, Object> queryPagingList(TSysTimerLog bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysTimerLogExample example = new TSysTimerLogExample();
		Criteria c = example.createCriteria();
		if(bean.getModelname()!=null && !"".equals(bean.getModelname())) {
			c.andModelnameLike("%"+bean.getModelname()+"%");
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		if(bean.getCreatetime()!=null) {
			c.andCreatetimeGreaterThanOrEqualTo(bean.getCreatetime());
		}
		if(bean.getScid()!=null) {
			c.andScidEqualTo(bean.getScid());
		}
		if(bean.getWfid()!=null) {
			c.andWfidEqualTo(bean.getWfid());
		}
		example.setOrderByClause(" createTime desc ");
		PageHelper.startPage((bean.getPage()-1) * bean.getRows(), bean.getRows());
		List<TSysTimerLog> list = logMapper.selectByExample(example);
		PageInfo<TSysTimerLog> pageinfo = new PageInfo<>(list);
		result.put("rows",list);
		result.put("total",pageinfo.getTotal());
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysTimerLog bean) {
		if(bean.getId()!=null) {
			logMapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			logMapper.insertSelective(bean);
			return true;
		}
	}

	public void add(TSysTimerLog bean) {
		bean.setCreatetime(DateUtil.getSysCurrentDate());
		logMapper.insertSelective(bean);
	}
	@Override
	public boolean delete(int id) {
		return logMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public List<TSysTimerLog> selectByExample(TSysTimerLogExample sqlqueryExample) {
		return logMapper.selectByExampleWithBLOBs(sqlqueryExample);
	}

	@Override
	public long countByExample(TSysTimerLogExample example) {
		return logMapper.countByExample(example);
	}

	@Override
	public Integer getMaxNewId() {
		return logMapper.getMaxNewId();
	}

}
