package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.model.TCompanyExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TCompanyMapper {
    long countByExample(TCompanyExample example);

    int deleteByExample(TCompanyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCompany record);

    int insertSelective(TCompany record);

    List<TCompany> selectByExampleWithRowbounds(TCompanyExample example, RowBounds rowBounds);

    List<TCompany> selectByExample(TCompanyExample example);

    TCompany selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCompany record, @Param("example") TCompanyExample example);

    int updateByExample(@Param("record") TCompany record, @Param("example") TCompanyExample example);

    int updateByPrimaryKeySelective(@Param("record") TCompany record);

    int updateByPrimaryKey(@Param("record") TCompany record);

	List<Map<String, Object>> queryRootIn(@Param("record") TCompany bean);

	List<Map<String, Object>> querySex(@Param("record") TCompany bean);

	List<Map<String, Object>> queryUserStatus(@Param("record") TCompany bean);

	List<Map<String, Object>> queryCosmetologist(@Param("record") TCompany bean);

	List<Map<String, Object>> queryCounsoler(@Param("record") TCompany bean);

	List<Map<String, Object>> queryProjectInventory(@Param("record") TCompany bean);

	List<Map<String, Object>> getView(@Param("record") TCompany bean);

	List<Map<String, Object>> countConsumptionReport(Map<String, Object> map);

	Integer countConsumptionReportByBean(Map<String, Object> map);

	List<Map<String, Object>> countSubscribeReport(Map<String, Object> map);

	Integer countSubscribeReportByBean(Map<String, Object> map);

	List<Map<String, Object>> queryCountInventory(Map<String, Object> map);

	Integer countByBean(Map<String, Object> map);

	List<Map<String, Object>> queryCountInventoryPie(Map<String, Object> map);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);

	Integer countCompanyByBean(Map<String, Object> params);

	List<Map<String, Object>> queryMapBeanList(@Param("record") TCompany bean);

	List<Map<String, Object>> queryCountBusinessAnalysisDataDetail(Map<String, Object> map);

	Integer countBusinessAnalysisDataDetailByBean(Map<String, Object> map);

	List<Map<String, Object>> queryCountProjectsOrderCost(Map<String, Object> map);

	List<Map<String, Object>> queryCountProjectsOrderAmountCost(Map<String, Object> map);

	List<Map<String, Object>> queryIndexUserCount(@Param("companyid") Integer companyid);

	List<Map<String, Object>> queryIndexCompanyCount(@Param("companyid") Integer companyid);
}