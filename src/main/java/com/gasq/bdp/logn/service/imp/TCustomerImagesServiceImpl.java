/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gasq.bdp.logn.mapper.TCustomerImagesMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCustomerImages;
import com.gasq.bdp.logn.model.TCustomerImagesExample;
import com.gasq.bdp.logn.model.TCustomerImagesExample.Criteria;
import com.gasq.bdp.logn.service.TCustomerImagesService;

@Service
public class TCustomerImagesServiceImpl implements TCustomerImagesService {
	@Autowired TCustomerImagesMapper TCustomerImagesMapper;

	@Override
	public long countByExample(TCustomerImagesExample example) {
		return TCustomerImagesMapper.countByExample(example);
	}
	
	public long countByExample(TCustomerImages bean) {
		TCustomerImagesExample example = new TCustomerImagesExample();
		Criteria c = example.createCriteria();
		if(bean.getCompanyId()!=null) {
			c.andCompanyIdEqualTo(bean.getCompanyId());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		return TCustomerImagesMapper.countByExample(example);
	}

	@Override
	public boolean delete(int id) {
		return TCustomerImagesMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public List<TCustomerImages> selectByExample(TCustomerImages bean) {
		TCustomerImagesExample example = new TCustomerImagesExample();
		Criteria c = example.createCriteria();
		if(bean.getCompanyId()!=null) {
			c.andCompanyIdEqualTo(bean.getCompanyId());
		}
		if(StringUtils.isNotBlank(bean.getCustomerPhone())) {
			c.andCustomerPhoneEqualTo(bean.getCustomerPhone());
		}
		if(bean.getOrderId()!=null) {
			c.andOrderIdEqualTo(bean.getOrderId());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		example.setOrderByClause(" create_time desc ");
		return TCustomerImagesMapper.selectByExample(example);
	}

	@Override
	public TCustomerImages selectByPrimaryKey(Integer id) {
		return TCustomerImagesMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(TCustomerImages bean) {
		bean.setCreateUser(SystemUserInfo.getSystemUser().getUser().getNickname());
		bean.setCompanyId(SystemUserInfo.getSystemUser().getUser().getCompanyid());
		TCustomerImagesMapper.insertSelective(bean);
		return true;
	}

	@Override
	public Integer getCount(TCustomerImages bean) {
		TCustomerImagesExample example = new TCustomerImagesExample();
		Criteria c = example.createCriteria();
		if(bean.getCompanyId()!=null) {
			c.andCompanyIdEqualTo(bean.getCompanyId());
		}
		if(StringUtils.isNotBlank(bean.getCustomerPhone())) {
			c.andCustomerPhoneEqualTo(bean.getCustomerPhone());
		}
		if(bean.getOrderId()!=null) {
			c.andOrderIdEqualTo(bean.getOrderId());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		return (int)TCustomerImagesMapper.countByExample(example);
	}

}
