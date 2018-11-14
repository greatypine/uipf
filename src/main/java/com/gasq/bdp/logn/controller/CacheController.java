package com.gasq.bdp.logn.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gasq.bdp.logn.service.CacheStrategy;

import io.swagger.annotations.Api;

/**
 *	缓存操作接口
 * @author princejwg
 *
 */
@RestController
@RequestMapping(value = "/cache")
@Api(value="缓存controller",tags={"缓存页面管理"})
public class CacheController {
	
	@Autowired
	CacheStrategy cacheStrategy;
   
    /**
     * 清楚缓存
     */
    @RequestMapping(value = "/clearCache")
    @ResponseBody
    public int clearCache(int type) {
    	int cs = 0;
    	// 已登录则 跳到首页
    	switch (type) {
		case 1:
			cs = cacheStrategy.clearCacheUIPF();
			break;

		default:
			break;
		}
    	return cs;
    }
    
    /**
     * 读取缓存
     */
    @RequestMapping(value = "/readCache")
    @ResponseBody
    public List<Map<String,Object>> readCache(String name) {
        return cacheStrategy.readCache(name);
    }
    /**
     * 读取缓存
     */
    @RequestMapping(value = "/deleteCacheByKey")
    @ResponseBody
    public int deleteCacheByKey(String cacheStore,String key) {
        return cacheStrategy.deleteCacheByKey(cacheStore,key);
    }
}