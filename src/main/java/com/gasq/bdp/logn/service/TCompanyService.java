package com.gasq.bdp.logn.service;
import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.model.TCompanyExample;
import java.util.List;
import java.util.Map;

public interface TCompanyService {

    int add(TCompany record);

    List<TCompany> selectByExample(TCompanyExample example);

    TCompany selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TCompany bean);

	boolean saveOrUpdate(TCompany bean) throws WorkFlowStateException;

	boolean delete(int id);

	List<Map<String, Object>> queryMapBeanList(TCompany bean);

}