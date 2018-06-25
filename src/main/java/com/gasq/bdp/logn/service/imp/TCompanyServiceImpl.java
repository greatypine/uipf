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
import com.gasq.bdp.logn.mapper.TCompanyMapper;
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.model.TCompanyExample;
import com.gasq.bdp.logn.model.TCompanyExample.Criteria;
import com.gasq.bdp.logn.service.TCompanyService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年10月31日上午11:33:35
 * @remark 
 */
@Service
public class TCompanyServiceImpl implements TCompanyService {
	@Autowired TCompanyMapper mapper;

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

	@Override
	public Map<String, Object> queryPagingList(TCompany bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TCompanyExample example = new TCompanyExample();
		Criteria c = example.createCriteria();
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		if(bean.getCreatetime()!=null) {
			c.andCreatetimeGreaterThanOrEqualTo(bean.getCreatetime());
		}
		example.setOrderByClause(" createTime desc ");
		int count = (int) mapper.countByExample(example);
		List<TCompany> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		RowBounds rowBounds = new RowBounds(start, bean.getRows());
		if(count>0) {
			list = mapper.selectByExampleWithRowbounds(example, rowBounds);
		}else {
			list = new ArrayList<TCompany>(); 
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TCompany bean) {
		if(bean.getId()!=null) {
			bean.setUpdatetime(DateUtil.getSysCurrentDate());
			mapper.updateByPrimaryKeySelective(bean);
			return true;
		}else {
			bean.setUpdatetime(DateUtil.getSysCurrentDate());
			bean.setCreatetime(DateUtil.getSysCurrentDate());
			mapper.insertSelective(bean);
			return true;
		}
	}

	@Override
	public List<Map<String, Object>> queryMapBeanList(TCompany bean) {
		return mapper.queryMapBeanList(bean);
	}
}
