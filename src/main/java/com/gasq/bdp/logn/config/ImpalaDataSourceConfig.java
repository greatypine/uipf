package com.gasq.bdp.logn.config;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;
/**
 * 
 * @author princejwg
 * @时间 2018年11月22日下午2:31:56
 * @项目路径 com.gasq.bdp.logn.config
 * @描述
 */
@Configuration
public class ImpalaDataSourceConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private Environment env;
	
    @Bean(name = "impalaDataSource")
    public DataSource impalaDataSource() {
    	DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("impala.url")); 
        if(StringUtils.isNoneBlank(env.getProperty("impala.user")))dataSource.setUsername(env.getProperty("impala.user")); 
        if(StringUtils.isNoneBlank(env.getProperty("impala.pass")))dataSource.setPassword(env.getProperty("impala.pass")); 
        dataSource.setDriverClassName(env.getProperty("impala.dirverName"));
        logger.info("druid imapal configuration init compalte!");
        return dataSource;      
    }

    
    
    @Bean(name="impalaJdbcTemplate")
    public JdbcTemplate  secondaryJdbcTemplate(@Qualifier("impalaDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
