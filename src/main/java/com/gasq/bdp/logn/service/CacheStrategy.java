package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

/**
 * @author justin 
 * @2017年5月5日 下午5:34:52
 * @TODO 注释：
 */
public interface CacheStrategy {

	int clearCacheUIPF();

	List<Map<String, Object>> readCache(String name);

	int deleteCacheByKey(String cacheStore,String key);
}
