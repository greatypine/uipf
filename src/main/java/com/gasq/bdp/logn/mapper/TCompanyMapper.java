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

    int updateByPrimaryKeySelective(TCompany record);

    int updateByPrimaryKey(TCompany record);

	List<Map<String, Object>> queryMapBeanList(TCompany bean);

	List<Map<String, Object>> queryRootIn(@Param("record") TCompany bean);

	List<Map<String, Object>> querySex(@Param("record") TCompany bean);

	List<Map<String, Object>> queryUserStatus(@Param("record") TCompany bean);

	List<Map<String, Object>> queryCosmetologist(@Param("record") TCompany bean);

	List<Map<String, Object>> queryCounsoler(@Param("record") TCompany bean);

	List<Map<String, Object>> queryProjectInventory(@Param("record") TCompany bean);

	List<Map<String, Object>> getView(@Param("record") TCompany bean);

	List<Map<String, Object>> countConsumptionReport(Map<String, Object> map);

	List<Map<String, Object>> countSubscribeReport(Map<String, Object> map);

	List<Map<String, Object>> queryCountInventory(Map<String, Object> map);

	List<Map<String, Object>> queryCountInventoryPie(Map<String, Object> map);
}