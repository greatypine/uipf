/**
 * 
 */
package com.gasq.bdp.logn.service;

import java.util.Map;

import com.gasq.bdp.logn.model.TSysBasecode;

/**
 * @author Ju_weigang
 * @时间 2018年6月1日下午12:49:07
 * @项目路径 com.gasq.bdp.logn.service
 * @描述 
 */
public interface BaseCodeService {

	TSysBasecode selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TSysBasecode bean);

	boolean saveOrUpdate(TSysBasecode bean);

	boolean delete(int id);
}
