/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.mapper.TSysSqlUpdateKeysMapper;
import com.gasq.bdp.logn.model.TSysSqlUpdateKeysExample;
import com.gasq.bdp.logn.model.TSysSqlUpdateKeys;
import com.gasq.bdp.logn.model.TSysSqlUpdateKeysExample.Criteria;
import com.gasq.bdp.logn.service.UpdateKeysService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年11月17日下午4:52:37
 * @remark 
 */
@Service
public class UpdateKeysServiceImpl implements UpdateKeysService {
	@Autowired
	TSysSqlUpdateKeysMapper updateKeysMapper;
	@Override
	public Map<String, Object> queryPagingList(TSysSqlUpdateKeys bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysSqlUpdateKeysExample example = new TSysSqlUpdateKeysExample();
		Criteria c = example.createCriteria();
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getCreatetime()!=null) {
			c.andCreatetimeGreaterThanOrEqualTo(bean.getCreatetime());
		}
		if(bean.getCode()!=null && !"".equals(bean.getCode())) {
			c.andCodeLike("%"+bean.getCode()+"%");
		}
		if(bean.getInsertUpdateJobId()!=null) {
			c.andInsertUpdateJobIdEqualTo(bean.getInsertUpdateJobId());
		}
		example.setOrderByClause(" createTime desc ");
		int count = (int) updateKeysMapper.countByExample(example);
		List<TSysSqlUpdateKeys> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		RowBounds rowBounds = new RowBounds(start, bean.getRows());
		if(count>0) {
			list = updateKeysMapper.selectByExampleWithRowbounds(example, rowBounds);
		}else {
			list = new ArrayList<TSysSqlUpdateKeys>();
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysSqlUpdateKeys bean) {
		if(bean.getId()!=null) {
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			updateKeysMapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			updateKeysMapper.insertSelective(bean);
			return true;
		}
	}

	@Override
	public boolean delete(int id) {
		return updateKeysMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public List<TSysSqlUpdateKeys> selectByExample(TSysSqlUpdateKeysExample sqlqueryExample) {
		return updateKeysMapper.selectByExample(sqlqueryExample);
	}

	@Override
	public long countByExample(TSysSqlUpdateKeysExample example) {
		return updateKeysMapper.countByExample(example);
	}

	@Override
	public List<TSysSqlUpdateKeys> queryBeanList(TSysSqlUpdateKeys bean) {
		TSysSqlUpdateKeysExample example = new TSysSqlUpdateKeysExample();
		Criteria c = example.createCriteria();
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getCode()!=null && !"".equals(bean.getCode())) {
			c.andCodeLike("%"+bean.getCode()+"%");
		}
		if(bean.getCreatetime()!=null) {
			c.andCreatetimeGreaterThanOrEqualTo(bean.getCreatetime());
		}
		if(bean.getInsertUpdateJobId()!=null) {
			c.andInsertUpdateJobIdEqualTo(bean.getInsertUpdateJobId());
		}
		example.setOrderByClause(" createTime desc ");
		return updateKeysMapper.selectByExample(example);
	}
}
