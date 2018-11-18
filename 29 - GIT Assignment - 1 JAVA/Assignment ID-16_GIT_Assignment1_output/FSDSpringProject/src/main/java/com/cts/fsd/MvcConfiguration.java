package com.cts.fsd;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan
public class MvcConfiguration implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		resolver.setContentType("text/html");
		registry.viewResolver(resolver);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/home").setViewName("forward:/index.jsp");
//        registry.addRedirectViewController("/home", "/");
		registry.addViewController("/error").setViewName("error");
//        registry.addViewController("/hello").setViewName("helloworld");
//        registry.addStatusController("/detail", HttpStatus.BAD_REQUEST);        
	}

}
