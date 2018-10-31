package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.quartz.SchedulerException;

import com.gasq.bdp.logn.model.TTherapistTreatmentTimeInfo;
import com.gasq.bdp.logn.model.TTherapistTreatmentTimeInfoExample;

public interface TTherapistTreatmentTimeInfoService {

    int insert(TTherapistTreatmentTimeInfo record);

    int insertSelective(TTherapistTreatmentTimeInfo record);

    List<TTherapistTreatmentTimeInfo> selectByExampleWithRowbounds(TTherapistTreatmentTimeInfoExample example, RowBounds rowBounds);

    List<TTherapistTreatmentTimeInfo> selectByExample(TTherapistTreatmentTimeInfo bean);

    TTherapistTreatmentTimeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTherapistTreatmentTimeInfo record);

    int updateByPrimaryKey(TTherapistTreatmentTimeInfo record);

	List<Map<String, Object>> queryZLSList(TTherapistTreatmentTimeInfo record);

	boolean saveOrUpdate(TTherapistTreatmentTimeInfo bean) throws SchedulerException;

	boolean delete(int id) throws SchedulerException;
}