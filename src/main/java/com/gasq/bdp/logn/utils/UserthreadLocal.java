package com.gasq.bdp.logn.utils;

import com.gasq.bdp.logn.model.SystemUser;

/**
 * @more 存储用户信息
 */
public final class UserthreadLocal {
	
	private static final ThreadLocal<SystemUser> threadLocal = new ThreadLocal<SystemUser>();
    
    public static SystemUser getThreadLocal(){
        return threadLocal.get();
    }
    public static SystemUser get(){
    	if(threadLocal.get() == null) {
			return null;  
		}else {
			return threadLocal.get();
		}
    }

    public static void set(SystemUser bean) {
        threadLocal.set(bean);
    }

    public static void remove() {
        threadLocal.remove();
    }

}