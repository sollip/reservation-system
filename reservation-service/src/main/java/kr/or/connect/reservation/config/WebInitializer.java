package kr.or.connect.reservation.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

//인터페이스를 상속받아서 쓰는거라 부모타입?으로 사용된다~(자동으로~)
public class WebInitializer implements WebApplicationInitializer{
	//web.xml에서 했던 설정을 여기서 해준다.
	//웹 전체에 대한 설정 - 웹이 처음 동작할때 알려줘야하는 정보들
	
	private static final String CONFIG_LOCATION="kr.or.connect.reservation.config";
	private static final String MAPPING_URL="/";
	public WebInitializer(){
		
	}
	
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext context=getContext(); //AnnotationConfigWebApplicationContext
		
		//encoding filter설정
		EnumSet<DispatcherType> dispatcherTypes=EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
		
		CharacterEncodingFilter characterEncodingFilter =new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		FilterRegistration.Dynamic characterEncoding=servletContext.addFilter("characterEncoding", characterEncodingFilter);
		characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
		
		//dispatcher servlet설정
		servletContext.addListener(new ContextLoaderListener(context));
		ServletRegistration.Dynamic dispatcher= servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(MAPPING_URL);
		
		
	}
	public AnnotationConfigWebApplicationContext getContext(){
		AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(CONFIG_LOCATION);
		return context;
	}

}
