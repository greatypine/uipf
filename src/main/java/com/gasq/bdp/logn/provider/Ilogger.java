package com.gasq.bdp.logn.provider;

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
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ilogger {
	
	String value() default "";
	
	EnableDetail flag() default EnableDetail.OPEN;
}