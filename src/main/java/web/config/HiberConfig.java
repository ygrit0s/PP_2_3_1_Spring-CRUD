package web.config;

import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class HiberConfig {
	
	private Environment env;
	
	public HiberConfig(Environment env) {
		this.env = env;
	}
	
	@Bean
	public DataSource getDataSource() {
		var dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver")));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean getEMFactory() {
		var properties = new Properties();
		properties.put("hibernate.show_sql", env.getProperty("hiber.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hiber.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hiber.dialect"));
		
		var factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(getDataSource());
		factoryBean.setPackagesToScan("web.model");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setJpaProperties(properties);
		
		return factoryBean;
	}
	
	@Bean
	public PlatformTransactionManager getTransactionManager() {
		var transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(getEMFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
}

