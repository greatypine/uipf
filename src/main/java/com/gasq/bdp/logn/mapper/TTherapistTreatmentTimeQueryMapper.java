package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TTherapistTreatmentTimeQuery;
import com.gasq.bdp.logn.model.TTherapistTreatmentTimeQueryExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTherapistTreatmentTimeQueryMapper {
    long countByExample(TTherapistTreatmentTimeQueryExample example);

    int deleteByExample(TTherapistTreatmentTimeQueryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TTherapistTreatmentTimeQuery record);

    int insertSelective(TTherapistTreatmentTimeQuery record);

    List<TTherapistTreatmentTimeQuery> selectByExampleWithRowbounds(TTherapistTreatmentTimeQueryExample example, RowBounds rowBounds);

    List<TTherapistTreatmentTimeQuery> selectByExample(TTherapistTreatmentTimeQueryExample example);

    TTherapistTreatmentTimeQuery selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TTherapistTreatmentTimeQuery record, @Param("example") TTherapistTreatmentTimeQueryExample example);

    int updateByExample(@Param("record") TTherapistTreatmentTimeQuery record, @Param("example") TTherapistTreatmentTimeQueryExample example);

    int updateByPrimaryKeySelective(TTherapistTreatmentTimeQuery record);

    int updateByPrimaryKey(TTherapistTreatmentTimeQuery record);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);

	void insertBatch(@Param("list") List<TTherapistTreatmentTimeQuery> beans);
}