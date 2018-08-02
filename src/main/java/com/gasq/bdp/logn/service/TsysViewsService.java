/**
 * 
 */
package com.gasq.bdp.logn.service;

import java.util.Map;

import com.gasq.bdp.logn.model.TSysViews;

/**
 * @author Ju_weigang
 * @时间 2018年6月1日下午12:49:07
 * @项目路径 com.gasq.bdp.logn.service
 * @描述 
 */
public interface TsysViewsService {

	TSysViews selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TSysViews bean);

	boolean saveOrUpdate(TSysViews bean);

	boolean delete(TSysViews bean);
}
