/**
 * 
 */
package com.gasq.bdp.logn.model;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.model
 * @creatTime 2017年12月20日下午1:31:27
 * @remark 
 */
public class RoleSign {
	/**
     * 普通后台管理员 标识
     */
    public static final String Q_ADMIN = "q_admin";
    /**
     * 后台管理员
     */
    public static final String H_ADMIN = "h_admin";
    /**
     * 管理员
     */
	public static final String SADMIN = "sadmin";
	/**
	 * 查询
	 */
	public static final String QUERY = "query";
	/**
	 * 治疗师
	 */
	public static final String Q_OPTION = "q_option";
	/**
	 * 后台操作员
	 */
	public static final String H_OPTION = "h_option";
	/**
	 * 测试
	 */
	public static final String Test = "test";
	/**
	 * 前台
	 */
	public static final String Q_RECEPTIONIST = "q_receptionist";
	/**
	 * 咨询师
	 */
	public static final String Q_COUNELOR = "q_counselor";
	/**
	 * 区域经理
	 */
	public static final String Q_AREA_SHOPMANAGER = "q_area_shopManager";
	/**
	 * 总经理
	 */
	public static final String GENERALMANAGER = "generalManager";
	/**
	 * 所有权限
	 */
	public static final String[] ALL = String.join(",", SADMIN,GENERALMANAGER,Q_AREA_SHOPMANAGER,Q_COUNELOR,Q_RECEPTIONIST,Test,H_OPTION,Q_OPTION,QUERY,H_ADMIN,Q_ADMIN).split(",");
	/**
	 * 前台所有人员权限
	 */
	public static final String[] Q_ALL = String.join(",", SADMIN,Q_AREA_SHOPMANAGER,Q_COUNELOR,Q_RECEPTIONIST,Test,Q_OPTION,QUERY,Q_ADMIN).split(",");;
	
	/**
	 * 后台所有人员权限
	 */
	public static final String[] H_ALL = String.join(",", SADMIN,H_ADMIN,H_OPTION,Test).split(",");
	
	/**
	 * 店长以上权限人员
	 */
	public static String[] M_ALL() {
		return String.join(",", SADMIN,Q_AREA_SHOPMANAGER,GENERALMANAGER,Test).split(",");
	}
}
