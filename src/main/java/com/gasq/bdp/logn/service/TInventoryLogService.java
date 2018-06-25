package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TInventoryLog;
import com.gasq.bdp.logn.model.TInventoryLogExample;
import java.util.List;
import java.util.Map;

public interface TInventoryLogService {
    long countByExample(TInventoryLogExample example);

    List<TInventoryLog> selectByExample(TInventoryLog bean);

	Map<String, Object> queryPagingList(TInventoryLog bean);

	boolean saveOrUpdate(TInventoryLog bean);

	boolean delete(int id);

	TInventoryLog selectByPrimaryKey(Integer id);
}