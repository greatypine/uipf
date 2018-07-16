package com.gasq.bdp.logn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@ServletComponentScan(basePackages="com.gasq.bdp.logn.filter")
@SpringBootApplication
@ComponentScan(basePackages={"com.gasq.bdp.logn"})
@EnableScheduling // 启用定时任务
@EnableTransactionManagement
@EnableAsync
public class Application{
	// 启动的时候要注意，由于我们在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例  
    @Autowired  
    private RestTemplateBuilder builder;  
  
    // 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例  
    @Bean  
    public RestTemplate restTemplate() {  
        return builder.build();  
    } 
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
