package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.service.CacheStrategy;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * @author justin 
 * @2017年5月5日 下午5:38:40
 * @TODO 注释：
 */
@Service
public class CacheConcreteStrategy implements CacheStrategy {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource
	EhCacheCacheManager cacheManager;
	@Override
	@CacheEvict(value = InitProperties.UIPF_CACHE,allEntries = true,beforeInvocation=true) 
	public int clearCacheUIPF() {
		logger.debug("已成功清空"+InitProperties.getCacheZHName(InitProperties.UIPF_CACHE)+"缓存...");
		return 1;
	}
	
	private List<Map<String,Object>> convertList2Map(String cacheName, List<String> cacheList, List<Map<String,Object>> elements) {
		if(cacheList.size()>0){
			for (String key : cacheList) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("projectname",InitProperties.getCacheZHName(cacheName));
				map.put("key",key);
				map.put("proabbreviation",cacheName);
				elements.add(map);
			}
		}
		return elements;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> readCache(String name) {
		CacheManager cm = cacheManager.getCacheManager();
		List<Map<String,Object>> elements = null;
		if(StringUtils.isBlank(name)){
			Cache cidmcache = cm.getCache(InitProperties.UIPF_CACHE);
			List<String> cidmlist = cidmcache.getKeys();
			elements = new ArrayList<Map<String,Object>>();
			elements = convertList2Map(InitProperties.UIPF_CACHE, cidmlist, elements);
		}else{
			Cache cache = cm.getCache(name);
			List<String> keys = cache.getKeys();
			elements = new ArrayList<Map<String,Object>>(keys.size());
			if(keys.size()>0){
				for (String key : keys) {
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("projectname",InitProperties.getCacheZHName(name));
					map.put("key",key);
					map.put("proabbreviation",name);
					elements.add(map);
				}
			}
		}
		return elements;
	}

	@Override
	public int deleteCacheByKey(String cacheStore,String key) {
		CacheManager cm = cacheManager.getCacheManager();
		Cache cache = cm.getCache(cacheStore);
		boolean f = cache.remove(key);
		return f?1:0;
	}
}
