package com.gasq.bdp.logn.service;
import java.util.Map;

import com.gasq.bdp.logn.model.TProject;

public interface TSysProjectsService {

    Map<String, Object> queryPagingList(TProject bean);

}