//package com.gasq.bdp.logn.filter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//
//import org.apache.activemq.web.AjaxServlet;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.ServletContextInitializer;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ServletBean {
//	@Value("${spring.activemq.broker-url}")
//	private String url;
//    @Bean
//    public ServletRegistrationBean amqServletRegistrationBean() {
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
//        registrationBean.setServlet(new AjaxServlet());
//        registrationBean.setLoadOnStartup(1);
//        registrationBean.setEnabled(true);
//        List<String> urlMappings=new ArrayList<String>();
//        urlMappings.add("/amq/*");
//        registrationBean.setUrlMappings(urlMappings);
//        return registrationBean;
//    }
//
//    @Bean
//    public ServletContextInitializer initializer() {
//        return new ServletContextInitializer() {
//            @Override
//            public void onStartup(ServletContext servletContext) throws ServletException {
//                servletContext.setInitParameter("org.apache.activemq.brokerURL", url);
//            }
//        };
//    }
//}