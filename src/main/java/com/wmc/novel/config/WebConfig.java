package com.wmc.novel.config;

import com.wmc.novel.interceptor.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @ClassName: WebConfig
 * @Description: MVC配置
 * @author money
 * @date 2020年11月16日
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private WmcConfig wmcConfig;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:" + wmcConfig.getUploadPath());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/login", "/admin/login").excludePathPatterns("/static/**").excludePathPatterns("/uploads/**");//.excludePathPatterns("/error/**");
	}
}
