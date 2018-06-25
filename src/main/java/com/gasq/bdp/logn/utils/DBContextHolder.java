package com.gasq.bdp.logn.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @more 用户切换数据源只要在程序中使用 DBContextHolder.setDBType("1") 即可完成数据源切换
 */
public class DBContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static final Map<String,Object> dbs = new HashMap<String,Object>();
    public static void setDBType(String dbType) {
        contextHolder.set(dbType);
    }
    public static String getDBType() {
        return (String) contextHolder.get();
    }
    public static void clearDBType() {
        contextHolder.remove();
    }
}