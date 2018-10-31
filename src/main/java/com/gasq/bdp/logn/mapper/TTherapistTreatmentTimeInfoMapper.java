package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TTherapistTreatmentTimeInfo;
import com.gasq.bdp.logn.model.TTherapistTreatmentTimeInfoExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTherapistTreatmentTimeInfoMapper {
    long countByExample(TTherapistTreatmentTimeInfoExample example);

    int deleteByExample(TTherapistTreatmentTimeInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TTherapistTreatmentTimeInfo record);

    int insertSelective(TTherapistTreatmentTimeInfo record);

    List<TTherapistTreatmentTimeInfo> selectByExampleWithRowbounds(TTherapistTreatmentTimeInfoExample example, RowBounds rowBounds);

    List<TTherapistTreatmentTimeInfo> selectByExample(TTherapistTreatmentTimeInfoExample example);

    TTherapistTreatmentTimeInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TTherapistTreatmentTimeInfo record, @Param("example") TTherapistTreatmentTimeInfoExample example);

    int updateByExample(@Param("record") TTherapistTreatmentTimeInfo record, @Param("example") TTherapistTreatmentTimeInfoExample example);

    int updateByPrimaryKeySelective(TTherapistTreatmentTimeInfo record);

    int updateByPrimaryKey(TTherapistTreatmentTimeInfo record);
    
    List<Map<String, Object>> queryZLSList(@Param("record") TTherapistTreatmentTimeInfo record);
}