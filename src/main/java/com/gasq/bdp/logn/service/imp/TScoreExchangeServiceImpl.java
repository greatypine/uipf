/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.mapper.TScoreExchangeMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TScoreExchange;
import com.gasq.bdp.logn.model.TScoreExchangeExample;
import com.gasq.bdp.logn.model.TScoreExchangeExample.Criteria;
import com.gasq.bdp.logn.service.TScoreExchangeService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author Ju_weigang
 * @时间 2018年3月27日上午10:32:11
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述 
 */
@Service
public class TScoreExchangeServiceImpl implements TScoreExchangeService {
	@Autowired TScoreExchangeMapper exchangeMapper;

	@Override
	public long countByExample(TScoreExchangeExample example) {
		return exchangeMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(TScoreExchangeExample example) {
		return exchangeMapper.deleteByExample(example);
	}

	@Override
	public boolean delete(Integer id) {
		return exchangeMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public int insert(TScoreExchange record) {
		return exchangeMapper.insert(record);
	}

	@Override
	public int insertSelective(TScoreExchange record) {
		return exchangeMapper.insertSelective(record);
	}

	@Override
	public List<TScoreExchange> selectByExampleWithRowbounds(TScoreExchangeExample example, RowBounds rowBounds) {
		return exchangeMapper.selectByExampleWithRowbounds(example, rowBounds);
	}

	@Override
	public List<TScoreExchange> selectByExample(TScoreExchangeExample example) {
		return exchangeMapper.selectByExample(example);
	}

	@Override
	public TScoreExchange selectByPrimaryKey(Integer id) {
		return exchangeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(TScoreExchange record, TScoreExchangeExample example) {
		return exchangeMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(TScoreExchange record, TScoreExchangeExample example) {
		return exchangeMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(TScoreExchange record) {
		return exchangeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TScoreExchange record) {
		return exchangeMapper.updateByPrimaryKey(record);
	}

	@Override
	public Map<String, Object> queryPagingList(TScoreExchange bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TScoreExchangeExample example = new TScoreExchangeExample();
		Criteria c = example.createCriteria();
		if(StringUtils.isNotBlank(bean.getMobilePhone())) {
			c.andMobilePhoneLike("%"+bean.getMobilePhone()+"%");
		}
		if(StringUtils.isNotBlank(bean.getUserName())) {
			c.andUserNameLike("%"+bean.getUserName()+"%");
		}
		if(StringUtils.isNotBlank(bean.getBuycode())) {
			c.andBuycodeLike("%"+bean.getBuycode()+"%");
		}
		if(StringUtils.isNotBlank(bean.getRecomCode())) {
			c.andRecomCodeLike("%"+bean.getRecomCode()+"%");
		}
		example.setOrderByClause(" id desc ");
		int count = (int) exchangeMapper.countByExample(example);
		List<TScoreExchange> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		RowBounds rowBounds = new RowBounds(start, bean.getRows());
		if(count>0) {
			list = exchangeMapper.selectByExampleWithRowbounds(example, rowBounds);
		}else {
			list = new ArrayList<TScoreExchange>(); 
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	public boolean saveOrUpdate(TScoreExchange bean) {
		bean.setCreateTime(DateUtil.getSysCurrentDate());
		bean.setCompanyid(SystemUserInfo.getSystemUser().getCompany().getId());
		if(bean.getId()!=null) {
			exchangeMapper.updateByPrimaryKeySelective(bean);
		}else {
			bean.setCreateTime(DateUtil.getSysCurrentDate());
			bean.setCreateUser(SystemUserInfo.getSystemUser().getUser().getUsername());
			exchangeMapper.insertSelective(bean);
		}
		return true;
	}

}
