package cn.com.demo.mvc.initailizer;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.github.pagehelper.PageInterceptor;

@Configuration
@PropertySource(value = "classpath:db.properties", encoding = "utf-8")
@MapperScan("cn.com.demo.javaweb.shopping.dao")
@Intercepts({ @Signature(type = Executor.class, method = "select", args = { MappedStatement.class, Object.class }) })
public class MyBatisConfig implements EnvironmentAware, Interceptor {
	@Value("${jdbc.driverName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.userName}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Bean
	public DataSource createDataSource() {
		BasicDataSource bds = new BasicDataSource();
		if (this.driverClassName == null || this.url == null || this.username == null || this.password == null) {
			this.driverClassName = this.env.getProperty("jdbc.driverName");
			this.url = this.env.getProperty("jdbc.url");
			this.username = this.env.getProperty("jdbc.userName");
			this.password = this.env.getProperty("jdbc.password");
		}
		bds.setDriverClassName(driverClassName);
		bds.setUrl(url);
		bds.setUsername(username);
		bds.setPassword(password);
		return bds;
	}

	@Bean("sqlSessionFactoryBean")
	public SqlSessionFactoryBean createSqlSessionFactory(@Autowired DataSource ds) {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(ds);
		return factoryBean;
	}

//	//@Bean
//	public MapperScannerConfigurer createMapperScannerConfigurer() {
//		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
//		configurer.setBasePackage("cn.com.ssm.shopping.mapper");
//		configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
//		return configurer;
//	}

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}

	private Environment env;

	private Properties properties = new Properties();

	public Object intercept(Invocation invocation) throws Throwable {
		// implement pre processing if need
		Object returnObject = invocation.proceed();
		// implement post processing if need
		return returnObject;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public Object plugin(Object target) {
		// TODO 自动生成的方法存根
		PageInterceptor p = new PageInterceptor();
		target = p.plugin(target);
		return target;
	}

}
