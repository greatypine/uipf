package com.gasq.bdp.logn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gasq.bdp.logn.utils.MyBeanSerializerModifier;
import com.google.common.collect.ImmutableList;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
		registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
//		registry.addResourceHandler("/webapp/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/webapp/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		super.addResourceHandlers(registry);
	}
	
//	@Bean
//	public ViewResolver getViewResolver(){
//	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//	    resolver.setPrefix("/WEB-INF/jsp/");
//	    resolver.setSuffix(".jsp");
//	    resolver.setViewClass(JstlView.class);
//	    return resolver;
//	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();       
		ObjectMapper mapper = converter.getObjectMapper();
        // 为mapper注册一个带有SerializerModifier的Factory，此modifier主要做的事情为：当序列化类型为array，list、set时，当值为空时，序列化成[]
        mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()));  
        converter.setSupportedMediaTypes(ImmutableList.of(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
        return converter;
	}
}
