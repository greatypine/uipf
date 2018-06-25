package com.gasq.bdp.logn.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = {"com.gasq.bdp.logn.mapper"}, sqlSessionFactoryRef = "gabdpSqlSessionFactory")
public class GabdpDBConfig {
    @Primary
    @Bean(name = "gabdpDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
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