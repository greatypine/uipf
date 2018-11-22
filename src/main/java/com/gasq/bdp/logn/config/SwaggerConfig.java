package com.gasq.bdp.logn.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author princejwg
 * @时间 2018年11月22日下午2:32:25
 * @项目路径 com.gasq.bdp.logn.config
 * @描述
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{

	 @Bean
	    public Docket createRestApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.gasq.bdp.logn.controller"))
	                .paths(PathSelectors.any())
	                .build();
	    }
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("---------GABDP-WF系统API文档---------")
	                .description("描述系统中restful接口信息。")
//	                .termsOfServiceUrl("http://blog.csdn.net/forezp")
	                .version("1.0")
	                .build();
	    }
}
