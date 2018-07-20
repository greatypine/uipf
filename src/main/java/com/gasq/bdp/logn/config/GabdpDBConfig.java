package com.gasq.bdp.logn.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
@MapperScan(basePackages = {"com.gasq.bdp.logn.mapper"}, sqlSessionFactoryRef = "gabdpSqlSessionFactory")
public class GabdpDBConfig {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
    private Environment env;
	
    @Primary
    @Bean(name = "gabdpDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
    	 DruidDataSource dataSource = new DruidDataSource();
         dataSource.setUrl(env.getProperty("spring.datasource.url")); 
         dataSource.setUsername(env.getProperty("spring.datasource.username")); 
         dataSource.setPassword(env.getProperty("spring.datasource.password")); 
         dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name")); 
         dataSource.setInitialSize(Integer.parseInt(env.getProperty("spring.datasource.initialSize").toString())); 
         dataSource.setMinIdle(Integer.parseInt(env.getProperty("spring.datasource.minIdle").toString())); 
         dataSource.setMaxActive(Integer.parseInt(env.getProperty("spring.datasource.maxActive").toString())); 
         dataSource.setMaxWait(Integer.parseInt(env.getProperty("spring.datasource.maxWait").toString())); 
         dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(env.getProperty("spring.datasource.timeBetweenEvictionRunsMillis").toString())); 
         dataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(env.getProperty("spring.datasource.minEvictableIdleTimeMillis").toString())); 
         dataSource.setValidationQuery(env.getProperty("spring.datasource.validationQuery")); 
         dataSource.setTestWhileIdle(Boolean.parseBoolean(env.getProperty("spring.datasource.testWhileIdle").toString())); 
         dataSource.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("spring.datasource.testOnBorrow").toString())); 
         dataSource.setTestOnReturn(Boolean.parseBoolean(env.getProperty("spring.datasource.testOnReturn").toString())); 
         dataSource.setPoolPreparedStatements(Boolean.parseBoolean(env.getProperty("spring.datasource.poolPreparedStatements").toString())); 
         dataSource.setConnectionProperties(env.getProperty("spring.datasource.connectionProperties"));
         dataSource.setDefaultAutoCommit(true);
         try { 
         	dataSource.setFilters(env.getProperty("spring.datasource.filters")); 
         } catch (SQLException e) { 
         	logger.error("druid configuration initialization filter", e); 
         } 
         return dataSource; 
    }
    
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", env.getProperty("spring.datasource.login.username"));
        reg.addInitParameter("loginPassword", env.getProperty("spring.datasource.login.password"));
        reg.addInitParameter("logSlowSql", env.getProperty("spring.datasource.logSlowSql"));
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Primary
    @Bean(name = "gabdpSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("gabdpDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.gasq.bdp.logn.model");
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/*.xml"));
        return factoryBean.getObject();
    }
    
    @Primary
    @Bean(name = "gabdpTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("gabdpDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

 }