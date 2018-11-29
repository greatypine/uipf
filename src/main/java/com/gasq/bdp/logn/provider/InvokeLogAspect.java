//package com.gasq.bdp.logn.provider;
//
//import java.io.File;
//import java.lang.reflect.Method;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.InputStreamSource;
//import org.springframework.stereotype.Component;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.util.StopWatch;
//
//import com.alibaba.fastjson.JSONObject;
//import com.gasq.bdp.logn.model.SystemUser;
//import com.gasq.bdp.logn.model.SystemUserInfo;
//import com.gasq.bdp.logn.utils.CommonUtils;
//
///**
// * 
// * @author princejwg
// * @时间 2018年11月26日上午11:26:38
// * @项目路径 com.gasq.bdp.logn.provider
// * @描述
// */
//@Aspect
//@Component
//public class InvokeLogAspect {
//
//    public static final Logger logger = LoggerFactory.getLogger(InvokeLogAspect.class);
//    // 执行最大时间 超过该时间则警告
//    private static final int DEFAULT_TIME_LIMIT = 3000;
//    private static final String MSG = "用户：{}--请求--\n --方法：{}\n --位置：{}\n --参数：{}\n --返回：{}\n --耗时：{} ms";
//
//    //切点
//    @Pointcut("@annotation(com.gasq.bdp.logn.provider.Ilogger)")
//    public void executePointCut() {
//
//    }
//
//    // around 切面强化
//    @Around("executePointCut()")
//    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] args = joinPoint.getArgs();
//        if (logger.isDebugEnabled() || logger.isWarnEnabled()) {
//            StopWatch clock = new StopWatch();
//            clock.start();
//            Object retrunobj = null;
//            try {
//                // 注意和finally中的执行顺序 finally是在return中的计算结束返回前执行
//                return retrunobj = joinPoint.proceed(args);
//            } catch (Exception e) {
//                throw e;
//            } finally {
//                clock.stop();
//                long totalTime = clock.getTotalTimeMillis();
//                // 打印日志
//                handleLog(joinPoint, args, retrunobj, totalTime);
//            }
//        } else {
//            return joinPoint.proceed(args);
//        }
//
//
//    }
//
//    /**
//     * 日志处理
//     *
//     * @param joinPoint 位置
//     * @param args      参数
//     * @param retrunobj 响应
//     * @param totalTime  耗时ms
//     */
//    private void handleLog(ProceedingJoinPoint joinPoint, Object[] args, Object retrunobj, long totalTime) {
//        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
//        Ilogger invokeLog = method.getAnnotation(Ilogger.class);
//        printLogMsg(invokeLog.value(), invokeLog.flag(),joinPoint, args, retrunobj, totalTime);
//    }
//
//    /**
//     * @param value            操作名称
//     * @param enableDetail     是否打印参数
//     * @param printReturn     是否打印响应
//     * @param joinPoint       位置
//     * @param args            参数
//     * @param returnObj       响应
//     * @param totalTimeMillis 耗时ms
//     */
//    protected void printLogMsg(String value, EnableDetail enableDetail,JoinPoint joinPoint, Object[] args, Object returnObj, long totalTimeMillis) {
//        Object[] params = argsDemote(args);
//        SystemUser user = SystemUserInfo.getSystemUser();
//        String username = "";
//        if(user!=null) {
//        	username = user.getUser().getNickname();
//        }
//        if (totalTimeMillis < DEFAULT_TIME_LIMIT)
//            logger.info(MSG, new Object[]{username,value,joinPoint.getStaticPart(), enableDetail==EnableDetail.OPEN?CommonUtils.BeanToJSON(params[0]):"", getPrintMsg(enableDetail, returnObj), totalTimeMillis});
//        else
//            logger.warn(MSG, new Object[]{username,value,joinPoint.getStaticPart(), enableDetail==EnableDetail.OPEN?CommonUtils.BeanToJSON(params[0]):"", getPrintMsg(enableDetail, returnObj), totalTimeMillis});
//    }
//
//    private String getPrintMsg(EnableDetail enableDetail, Object returnObj) {
//        return enableDetail==EnableDetail.OPEN ? ((returnObj != null) ? JSONObject.toJSONString(returnObj) : "null") : "[printReturn = false]";
//    }
//
//    private Object[] argsDemote(Object[] args) {
//        if (args == null || args.length == 0) {
//            return new Object[]{};
//        }
//        Object[] params = new Object[args.length];
//        for (int i = 0; i < args.length; i++) {
//            Object arg = args[i];
//            if (arg instanceof ServletRequest || arg instanceof ServletResponse || arg instanceof ModelMap
//                    || arg instanceof Model || arg instanceof InputStreamSource ||
//                    arg instanceof File) {
//                params[i] = args.toString();
//            } else {
//                params[i] = args[i];
//            }
//        }
//        return params;
//    }
//}