package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TProject;
import com.gasq.bdp.logn.model.TProjectExample;
import java.util.List;
import java.util.Map;

public interface TSysProjectService {
    long countByExample(TProjectExample example);

    List<TProject> selectByExample(TProject bean);

	Map<String, Object> queryPagingList(TProject bean);

	boolean saveOrUpdate(TProject bean);

	boolean delete(int id);

	TProject selectByPrimaryKey(Long id);

	List<TProject> queryProjectList(Integer companyId, Integer index, Integer size);
}