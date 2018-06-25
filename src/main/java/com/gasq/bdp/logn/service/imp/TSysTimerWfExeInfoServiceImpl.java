package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.mapper.TSysTimerWfExeInfoMapper;
import com.gasq.bdp.logn.model.TSysTimerWfExeInfo;
import com.gasq.bdp.logn.model.TSysTimerWfExeInfoExample;
import com.gasq.bdp.logn.model.TSysTimerWfExeInfoExample.Criteria;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年11月30日上午9:41:29
 * @remark
 */
@Service
public class TSysTimerWfExeInfoServiceImpl implements com.gasq.bdp.logn.service.TSysTimerWfExeInfoService {
	@Autowired TSysTimerWfExeInfoMapper exeInfoMapper;

	@Override
	public Map<String, Object> queryPagingList(TSysTimerWfExeInfo bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysTimerWfExeInfoExample example = new TSysTimerWfExeInfoExample();
		Criteria c = example.createCriteria();
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		if(bean.getCreatetime()!=null) {
			c.andCreatetimeGreaterThanOrEqualTo(bean.getCreatetime());
		}
		example.setOrderByClause(" createTime desc ");
		int count = (int) exeInfoMapper.countByExample(example);
		List<TSysTimerWfExeInfo> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		RowBounds rowBounds = new RowBounds(start, bean.getRows());
		if(count>0) {
			list = exeInfoMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
		}else {
			list = new ArrayList<TSysTimerWfExeInfo>();
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysTimerWfExeInfo bean) {
		if(bean.getId()!=null) {
			exeInfoMapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			exeInfoMapper.insertSelective(bean);
			return true;
		}
	}

	@Override
	public boolean delete(int id) {
		return exeInfoMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public List<TSysTimerWfExeInfo> selectByExample(TSysTimerWfExeInfoExample sqlqueryExample) {
		return exeInfoMapper.selectByExampleWithBLOBs(sqlqueryExample);
	}

	@Override
	public long countByExample(TSysTimerWfExeInfoExample example) {
		return exeInfoMapper.countByExample(example);
	}

	@Override
	public List<TSysTimerWfExeInfo> queryList(TSysTimerWfExeInfo exeInfo) {
		TSysTimerWfExeInfoExample example = new TSysTimerWfExeInfoExample();
		Criteria c = example.createCriteria();
		if(exeInfo.getStatus()!=null) {
			c.andStatusEqualTo(exeInfo.getStatus());
		}
		if(exeInfo.getCreatetime()!=null) {
			c.andCreatetimeGreaterThanOrEqualTo(exeInfo.getCreatetime());
		}
		if(exeInfo.getLogid()!=null) {
			c.andLogidEqualTo(exeInfo.getLogid());
		}
		if(exeInfo.getId()!=null) {
			c.andIdEqualTo(exeInfo.getId());
		}
		example.setOrderByClause(" createTime asc ");
		return exeInfoMapper.selectByExample(example);
	}

}
