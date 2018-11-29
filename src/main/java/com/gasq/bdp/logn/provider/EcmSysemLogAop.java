package com.gasq.bdp.logn.provider;
import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gasq.bdp.logn.model.SystemUser;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.utils.CommonUtils;
/**
 * 用户操作日志AOP
 * @author Ju_weigang
 * @时间 2018年9月12日下午5:53:31
 * @项目路径 com.gasq.bdp.logn.utils
 * @描述
 */
@Component
@Aspect
//@Order(1)标记切面类的处理优先级,i值越小,优先级别越高.PS:可以注解类,也能注解到方法上
public class EcmSysemLogAop {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EcmSysemLogAop.class);
	
	//开始执行时间
	Instant start = null;
	//开始执行时间
	Instant end = null;
		
	@Before(("execution(* com.gasq.bdp.logn..*.*(..)) && @annotation(ilogger)"))
	public void doBeforeAdvice(JoinPoint joinPoint, Ilogger ilogger) {
		start = Instant.now();
		SystemUser user = SystemUserInfo.getSystemUser();
		//请求的方法名
		String value = ilogger.value();
		EnableDetail flag = ilogger.flag();
		end = Instant.now();
		if(flag==EnableDetail.OPEN) {
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = signature.getName();
			Object[] args = joinPoint.getArgs();
			String clss = className + "." + methodName + "()";
			String exstr = "";
			if(user!=null) {
				if(args.length > 0) exstr = "请求参数："+CommonUtils.BeanToJSON(args[0]);
				LOGGER.info("用户【"+user.getUser().getNickname()+"】开始执行方法【"+value+"】。方法名称【"+clss+"】。"+exstr);
			}else {
				LOGGER.info("开始执行方法【"+value+"】。方法名称【"+clss+"】。"+exstr);
			}
		}else {
			if(user==null) {
				LOGGER.info("开始执行方法【"+value+"】.............");
			}else {
				LOGGER.info("用户【"+user.getUser().getNickname()+"】开始执行方法【"+value+"】.............");
			}
		}
	}
 
	@After(("execution(* com.gasq.bdp.logn..*.*(..)) && @annotation(ilogger)"))
	public void doAfterAdvice(JoinPoint joinPoint, Ilogger ilogger) {
		SystemUser user = SystemUserInfo.getSystemUser();
		//请求的方法名
		String value = ilogger.value();
		end = Instant.now();
		if(user!=null) {
			LOGGER.info("用户【"+user.getUser().getNickname()+"】执行方法【"+value+"】结束，运行时长："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
		}else {
			LOGGER.info("执行方法【"+value+"】结束，运行时长："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
		}
	}
}