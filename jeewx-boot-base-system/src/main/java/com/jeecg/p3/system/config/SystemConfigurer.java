package com.jeecg.p3.system.config;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jeecg.p3.system.interceptors.AccessSignInterceptor;
import com.jeecg.p3.system.interceptors.LoginInterceptor;
import com.jeecg.p3.system.servlet.RandCodeImageServlet;

@Configuration
public class SystemConfigurer implements WebMvcConfigurer {
	private final static Logger log = LoggerFactory.getLogger(SystemConfigurer.class);
	@Value("${jeewx.path.upload}")
	private String upLoadPath;
	@Value("${spring.resource.static-locations}")
	private String staticLocations;

	/**默认拦截器排除资源*/
	private List<String> EXCLUDE_PATHS= Arrays.asList("/plug-in/**","/content/**","/upload/**","/system/*.do","/error");
	@Autowired
    private LoginInterceptor loginInterceptor;
	@Autowired
    private AccessSignInterceptor accessSignInterceptor;
	
	@Value("${jeewx.interceptor.is-open}")
	private boolean isOpen;
	@Value("${jeewx.interceptor.excludeUrls.login-interceptor}")
	private String loginInterceptorExcludeUrls;
	@Value("${jeewx.interceptor.excludeUrls.access-sign-interceptor}")
	private String accessSignInterceptorExcludeUrls;
	
	/**
	 * 登录验证码
	 */
	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public ServletRegistrationBean randCodeImageServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new RandCodeImageServlet(), "/randCodeImage");
        return servletRegistrationBean;
    }
	
	/**
	 * 拦截器（登录 + 签名）
	 */
    public void addInterceptors(InterceptorRegistry registry) {
    	if(isOpen) {
    		log.info("loginInterceptorExcludeUrls: "+loginInterceptorExcludeUrls);
    		log.info("accessSignInterceptorExcludeUrls: "+accessSignInterceptorExcludeUrls);
    		registry.addInterceptor(loginInterceptor).addPathPatterns("/**/back/**/*").excludePathPatterns(EXCLUDE_PATHS).excludePathPatterns(loginInterceptorExcludeUrls.split(","));
    		registry.addInterceptor(accessSignInterceptor).addPathPatterns("/**").excludePathPatterns("/**/back/**").excludePathPatterns(EXCLUDE_PATHS).excludePathPatterns(accessSignInterceptorExcludeUrls.split(","));
    	}
    }
    
    /**
     * 默认跳转登录页面
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/system/login.do");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

	/**
	 * 静态资源的配置 - 使得可以从磁盘中读取 Html、图片、视频、音频等
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("file:" + upLoadPath + "//")
				.addResourceLocations(staticLocations.split(","));
	}

}