package com.gasq.bdp.logn.model;

import org.apache.shiro.SecurityUtils;

/**
 * @author justin 
 * @2017年3月5日 上午8:02:30
 * @TODO 注释：
 */
public class SystemUserInfo {
	
	private static SystemUser SYSTEM_USER = null;
	
	public static synchronized SystemUser getInstance(){
		SYSTEM_USER = new SystemUser();
		return SYSTEM_USER;
	}

	public static SystemUser getSystemUser() {
		return (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("user");
	}
	public static SystemUser setSystemUser(SystemUser user) {
		SYSTEM_USER = user;
		return SYSTEM_USER;
	}
	
}