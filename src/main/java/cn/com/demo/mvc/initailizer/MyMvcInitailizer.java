package cn.com.demo.mvc.initailizer;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyMvcInitailizer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringConfig.class };// spring容器的配置类
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringMVCConfig.class };// web容器的配置类
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	// 重写该方法注册Filter，该方法的所有Filter都会映射到DispatcherServlet上，因此没必要声明映射路径
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new CharacterEncodingFilter("UTF-8", true, true) };
	}

}
