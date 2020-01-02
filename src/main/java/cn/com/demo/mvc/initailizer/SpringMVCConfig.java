package cn.com.demo.mvc.initailizer;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.util.UrlPathHelper;

@Configuration
//<context:component-scan base-package="cn.com.demo.mvc.controller"></context:component-scan>
@ComponentScan(basePackages = { "cn.com.demo.mvc.controller" })
//<mvc:annotation-driven ></mvc:annotation-driven>
// 完成了SpringMVC得所有默认配置
public class SpringMVCConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {
		// html jsp default访问
		// <mvc:default-servlet-handler default-servlet-name="default"/>
		defaultServletHandlerConfigurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 设置不需要经过SpringMVC框架过滤的资源，比如js css 和 images等
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// 禁用后缀访问 /xx.do 等有后缀请求不支持
		configurer.setUseRegisteredSuffixPatternMatch(true);
		// 支持MatrixVariable变量
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		// 支持带分号请求，缺省是不支持的 enable-matrix-variables="true"
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);
	}

	@Bean("viewResolver") // id name
	public UrlBasedViewResolver createUrlBasedViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setContentType("text/html;charset=utf-8");
		return resolver;
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// super.extendMessageConverters(converters);
		// 解决@ResponseBody乱码问题 response.setContentType("text/html;charset=utf-8")
		StringHttpMessageConverter httpConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		httpConverter.setSupportedMediaTypes(
				Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.TEXT_HTML));
		converters.add(0, httpConverter);// 需要添加到第一个，避免使用前面的msgConverter

		// 响应json
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		converters.add(jsonConverter);
	}

	@Bean(name = "multipartResolver") // beanName固定
	// 名称固定
	public CommonsMultipartResolver commonsMultipartResolver() {
		CommonsMultipartResolver multiResolver = new CommonsMultipartResolver();
		multiResolver.setMaxUploadSize(10 * 1024 * 1024);
		multiResolver.setDefaultEncoding("UTF-8");
		return multiResolver;
	}
}
