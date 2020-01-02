package cn.com.demo.mvc.initailizer;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan("cn.com.demo.javaweb.shopping.service")
@Import(value = { MyBatisConfig.class })
public class SpringConfig {
	@Bean
	public DataSourceTransactionManager createDataSourceTransactionManager(@Autowired DataSource ds) {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(ds);
		return txManager;
	}
}
