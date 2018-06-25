//package com.gasq.bdp.logn.filter;
//import org.apache.catalina.filters.RemoteIpFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * Created by zsl on 2017/9/3.
// */
//@Configuration
//public class ConfigurationFilter {
//    @Bean
//    public RemoteIpFilter remoteIpFilter() {
//        return new RemoteIpFilter();
//    }
//
//    @Bean
//    public FilterRegistrationBean testFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new MyFilter());//添加过滤器
//        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
//        registration.setName("MyFilter");//设置优先级
//        registration.setOrder(1);//设置优先级
//        return registration;
//    }
//
//    public class MyFilter implements Filter {
//        @Override
//        public void destroy() {
//        }
//
//        @Override
//        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain
//                filterChain)
//                throws IOException, ServletException {
//            HttpServletRequest request = (HttpServletRequest) srequest;
//            String requestURI = request.getRequestURI();
////            if(!requestURI.equals("/weChat-wf/amq") && !requestURI.equals("/weChat-wf/index")) {
////            	SystemUser user = (SystemUser) request.getSession().getAttribute("user");
////            	SystemUserInfo.setSystemUser(user);
////            	System.out.println(user);
////            }
//            filterChain.doFilter(srequest, sresponse);
//        }
//
//        @Override
//        public void init(FilterConfig arg0) throws ServletException {
//        }
//    }
//}