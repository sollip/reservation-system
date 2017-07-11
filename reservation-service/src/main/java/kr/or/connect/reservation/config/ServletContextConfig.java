package kr.or.connect.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"kr.or.connect.reservation.controller"}) 
public class ServletContextConfig extends WebMvcConfigurerAdapter{ 
//	@Bean
//	public ViewResolver viewResolver(){
//		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
//		viewResolver.setOrder(2);
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//		return viewResolver;
//	}
	
	//다중 viewresolver 나중에 구현하기....................................
	@Bean
	public ViewResolver viewResolver2(){
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/resources/html/");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}
	
	@Override
	   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	       registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");  //   webapp/resources 경로를 의미
	   }

}