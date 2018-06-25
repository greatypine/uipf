package com.gasq.bdp.logn.config;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.gasq.bdp.logn.filter.MShiroFilterFactoryBean;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.config
 * @creatTime 2017年12月6日下午5:56:12
 * @remark
 */
@Configuration
public class ShiroConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean
    public EhCacheManager getEhCacheManager() {  
        EhCacheManager em = new EhCacheManager();  
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");  
        return em;  
    }  

    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(EhCacheManager cacheManager) {  
        MyShiroRealm realm = new MyShiroRealm(); 
        realm.setCacheManager(cacheManager);
        return realm;
    }  

    /**
     * 注册DelegatingFilterProxy（Shiro）
     * 集成Shiro有2种方法：
     * 1. 按这个方法自己组装一个FilterRegistrationBean（这种方法更为灵活，可以自己定义UrlPattern，
     * 在项目使用中你可能会因为一些很但疼的问题最后采用它， 想使用它你可能需要看官网或者已经很了解Shiro的处理原理了）
     * 2. 直接使用ShiroFilterFactoryBean（这种方法比较简单，其内部对ShiroFilter做了组装工作，无法自己定义UrlPattern，
     * 默认拦截 /*）
     *
     * @param dispatcherServlet
     */
//  @Bean
//  public FilterRegistrationBean filterRegistrationBean() {
//      FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//      filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//      //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理  
//      filterRegistration.addInitParameter("targetFilterLifecycle", "true");
//      filterRegistration.setEnabled(true);
//      filterRegistration.addUrlPatterns("/*");// 可以自己灵活的定义很多，避免一些根本不需要被Shiro处理的请求被包含进来
//      return filterRegistration;
//  }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroRealm myShiroRealm) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(myShiroRealm);
//      <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 --> 
        dwsm.setCacheManager(getEhCacheManager());
      //注入记住我管理器;  
        dwsm.setRememberMeManager(rememberMeManager());
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    /**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean){
        /////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
        logger.info("##################从数据库读取权限规则，加载到shiroFilter中##################");
//        filterChainDefinitionMap.put("/user/edit/**", "authc,perms[user:edit]");// 这里为了测试，固定写死的值，也可以从数据库或其他配置中读取
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/templates/**", "anon");
//        filterChainDefinitionMap.put("/swagger-ui*", "anon"); //swagger-ui.html
      //验证码可以匿名访问  
        filterChainDefinitionMap.put("/validatecodeServlet", "anon");
        filterChainDefinitionMap.put("/**", "authc");
     // 配置退出过滤器,其中的具体代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");  
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /**
     * ShiroFilter<br/>
     * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
     * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
     * @param myShiroRealm
     * @param stuService
     * @param scoreDao
     * @return
     */
    @SuppressWarnings("unused")
	@Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new MShiroFilterFactoryBean();
        Map<String,Filter> filters = shiroFilterFactoryBean.getFilters();//获取filters
//        filters.put("authc", new CustomFormAuthenticationFilter());//将自定义 的FormAuthenticationFilter注入shiroFilter中    
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/index");
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/homepage");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }
    
    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
       HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
       hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
//       hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
       return hashedCredentialsMatcher;
    }
    
    @Bean  
    public SimpleCookie rememberMeCookie(){  
//       System.out.println("ShiroConfiguration.rememberMeCookie()");  
       //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe  
       SimpleCookie simpleCookie = new SimpleCookie("rememberMe");  
       //<!-- 记住我cookie生效时间30天 ,单位秒;-->
       simpleCookie.setMaxAge(259200);
       return simpleCookie;  
    }  
    /**  
      * cookie管理对象;  
      * @return  
      */  
    @Bean  
    public CookieRememberMeManager rememberMeManager(){  
//       System.out.println("ShiroConfiguration.rememberMeManager()");  
       CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();  
       cookieRememberMeManager.setCookie(rememberMeCookie());  
       return cookieRememberMeManager;  
    }
    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     * @return
     */
    @Bean(name = "shiroDialect")  
    public ShiroDialect shiroDialect(){  
        return new ShiroDialect();  
    }  
}