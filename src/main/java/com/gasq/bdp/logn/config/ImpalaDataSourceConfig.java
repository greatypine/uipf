package com.gasq.bdp.logn.config;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class ImpalaDataSourceConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private Environment env;
	
    @Bean(name = "impalaDataSource")
    @ConfigurationProperties(prefix="impala")
    public DataSource impalaDataSource() {
    	DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("impala.url")); 
        dataSource.setUsername(env.getProperty("impala.user")); 
        dataSource.setPassword(env.getProperty("impala.pass")); 
        dataSource.setDriverClassName(env.getProperty("impala.dirverName")); 
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("impala.initialSize").toString())); 
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("impala.minIdle").toString())); 
        dataSource.setMaxWait(Integer.parseInt(env.getProperty("impala.maxWait").toString())); 
        dataSource.setDefaultAutoCommit(true);
        try {
        	dataSource.setFilters(env.getProperty("impala.filters")); 
        } catch (SQLException e) { 
        	logger.error("druid imapal configuration initialization filter", e); 
        } 
        return dataSource;      
    }

    @Bean(name="impalaJdbcTemplate")
    public JdbcTemplate  secondaryJdbcTemplate(@Qualifier("impalaDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
