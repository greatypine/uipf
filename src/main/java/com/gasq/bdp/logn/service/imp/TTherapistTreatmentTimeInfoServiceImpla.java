/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.mapper.TTherapistTreatmentTimeInfoMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TTherapistTreatmentTimeInfo;
import com.gasq.bdp.logn.model.TTherapistTreatmentTimeInfoExample;
import com.gasq.bdp.logn.model.TTherapistTreatmentTimeInfoExample.Criteria;
import com.gasq.bdp.logn.service.TTherapistTreatmentTimeInfoService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author Ju_weigang
 * @时间 2018年10月8日下午5:28:40
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述 
 */
@Service
public class TTherapistTreatmentTimeInfoServiceImpla implements TTherapistTreatmentTimeInfoService {
	@Autowired TTherapistTreatmentTimeInfoMapper treatmentTimeInfoMapper;

	@Override
	public int insertSelective(TTherapistTreatmentTimeInfo record) {
		return treatmentTimeInfoMapper.insertSelective(record);
	}

	@Override
	public List<TTherapistTreatmentTimeInfo> selectByExampleWithRowbounds(TTherapistTreatmentTimeInfoExample example,
			RowBounds rowBounds) {
		return treatmentTimeInfoMapper.selectByExampleWithRowbounds(example, rowBounds);
	}

	@Override
	public TTherapistTreatmentTimeInfo selectByPrimaryKey(Integer id) {
		return treatmentTimeInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TTherapistTreatmentTimeInfo record) {
		return treatmentTimeInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TTherapistTreatmentTimeInfo record) {
		return treatmentTimeInfoMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public List<Map<String,Object>> queryZLSList(TTherapistTreatmentTimeInfo record) {
		if(record.getDay()!=null) {
			if(DateUtil.getSysCurrentDate()==record.getDay()) {
				record.setDaytype(1);
			}else if(DateUtil.getSysCurrentDate().before(record.getDay())) {//当前时间小于传入的时间
				record.setDaytype(2);
			}else {
				record.setDaytype(1);
			}
		}
		return treatmentTimeInfoMapper.queryZLSList(record);
	}

	@Override
	public int insert(TTherapistTreatmentTimeInfo record) {
		return treatmentTimeInfoMapper.insertSelective(record);
	}

	@Override
	public List<TTherapistTreatmentTimeInfo> selectByExample(TTherapistTreatmentTimeInfo bean) {
		TTherapistTreatmentTimeInfoExample example = new TTherapistTreatmentTimeInfoExample();
		Criteria c = example.createCriteria();
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		example.setOrderByClause(" id asc ");
		return treatmentTimeInfoMapper.selectByExample(example);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveOrUpdate(TTherapistTreatmentTimeInfo bean)throws SchedulerException {
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			bean.setUpdateTime(DateUtil.getSysCurrentDate());
			bean.setCompanyid(SystemUserInfo.getSystemUser().getCompany().getId());
			if(bean.getId()!=null) {
				bean.setUpdateUser(SystemUserInfo.getSystemUser().getUser().getUsername());
				treatmentTimeInfoMapper.updateByPrimaryKeySelective(bean);
			}else {
				bean.setCreateTime(DateUtil.getSysCurrentDate());
				bean.setCreateUser(user.getUsername());
				treatmentTimeInfoMapper.insertSelective(bean);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;
	}

	@Override
	@Transactional
	public boolean delete(int id)throws SchedulerException {
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			TTherapistTreatmentTimeInfo bean = treatmentTimeInfoMapper.selectByPrimaryKey(id);
			bean.setStatus(false);
			bean.setUpdateUser(user.getNickname());
			bean.setUpdateTime(DateUtil.getSysCurrentDate());
			treatmentTimeInfoMapper.updateByPrimaryKeySelective(bean);
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return false;
	}
}
