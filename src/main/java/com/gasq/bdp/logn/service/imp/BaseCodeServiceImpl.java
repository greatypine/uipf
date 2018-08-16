/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.mapper.TSysBasecodeMapper;
import com.gasq.bdp.logn.model.TSysBasecode;
import com.gasq.bdp.logn.model.TSysBasecodeExample;
import com.gasq.bdp.logn.model.TSysBasecodeExample.Criteria;
import com.gasq.bdp.logn.service.BaseCodeService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author Ju_weigang
 * @时间 2018年8月1日下午1:53:13
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述
 */
@Service
public class BaseCodeServiceImpl implements BaseCodeService {
	@Autowired TSysBasecodeMapper mapper;

	@Override
	public boolean delete(int id) {
		return mapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public TSysBasecode selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TSysBasecode bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysBasecodeExample example = new TSysBasecodeExample();
		Criteria c = example.createCriteria();
		if(StringUtils.isNotBlank(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(StringUtils.isNotBlank(bean.getViewName())) {
			c.andViewNameEqualTo(bean.getViewName());
		}
		if(bean.getCompanyId()!=null) {
			c.andCompanyIdEqualTo(bean.getCompanyId());
		}
		example.setOrderByClause(" createtime desc ");
		PageHelper.startPage((bean.getPage()-1) * bean.getRows(), bean.getRows());
		List<TSysBasecode> list = mapper.selectByExample(example);
		PageInfo<TSysBasecode> pageinfo = new PageInfo<>(list);
		result.put("rows",list);
		result.put("total",pageinfo.getTotal());
		return result;
	}

	@Override
	public boolean saveOrUpdate(TSysBasecode bean) {
		bean.setCreatetime(DateUtil.getSysCurrentDate());
		if(bean.getId()!=null) {
			mapper.updateByPrimaryKeySelective(bean);
		}else {
			mapper.insertSelective(bean);
		}
		return true;
	}

}
