/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.mapper.TProjectMapper;
import com.gasq.bdp.logn.model.TProject;
import com.gasq.bdp.logn.model.TProjectExample;
import com.gasq.bdp.logn.model.TProjectExample.Criteria;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.service.TSysProjectsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年10月31日上午11:33:35
 * @remark 
 */
@Service
public class TSysProjectsServiceImpl implements TSysProjectsService {
	@Autowired TProjectMapper mapper;

	@Override
	public Map<String, Object> queryPagingList(TProject bean) {
		Map<String,Object> result = new HashMap<String,Object>();
		TProjectExample example = new TProjectExample();
		Criteria c = example.createCriteria();
		if(bean.getProjectName()!=null && !"".equals(bean.getProjectName())) {
			c.andProjectNameLike("%"+bean.getProjectName()+"%");
		}
		PageHelper.startPage(bean.getPageIndex(), bean.getLength());
		List<TProject> listmaps = mapper.selectByExample(example);
		PageInfo<TProject> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("data",listmaps);
		result.put("recordsTotal",pageinfo.getTotal());
		result.put("recordsFiltered",pageinfo.getTotal());
		return result;
	}

}
