package com.gasq.bdp.logn.provider;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Ju_weigang
 * @时间 2018年9月12日下午5:53:19
 * @项目路径 com.gasq.bdp.logn.provider
 * @描述
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ilogger {
	/**
	 * 方法说明
	 * @return
	 */
	String value() default "";
	
	/**
	 * EnableDetail枚举类型,是否开启参数、返回值打印{OPEN、CLOSE}
	 * @return
	 */
	EnableDetail flag() default EnableDetail.OPEN;
}