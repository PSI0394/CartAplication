package com.tut.configuration;

import java.util.Properties;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
//Datasourse
	@Value("${db.driverclass}")
	private String DRIVER_CLASS;
	@Value("${db.url}")
	private String URL;
	@Value("${db.password}")
	private String PASSWORD;
	@Value("${db.username}")
	private String USERNAME;
//Hibernate	
	@Value("${hibernate.dialect}")
	private String DIALECT;
	@Value("${hibernate.show_sql}")
	private String SHOW_SQL;
	@Value("${hibernate.formate_sql}")
	private String FORMATE_SQL;
	@Value("${hibernate.hbm2ddl.auto}")
	private String HBM2DDL;
	@Value("${entitymanager.packagesToScan}")
	private String ENTITYSCAN;
	
	@Bean 
	public DataSource setDataSource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER_CLASS);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}
	
	
	@Bean
	public Properties setHibernateProperties() {
		Properties properties=new Properties();
		properties.put("hibernate.dialect", DIALECT);
		properties.put("hibernate.show_sql", SHOW_SQL);
		properties.put("hibernate.format_sql", FORMATE_SQL);
		properties.put("hibernate.hbm2ddl.auto", HBM2DDL);
		
		return properties;
		
	}
	

	@Bean(name="entityManagerFactory")
	public LocalSessionFactoryBean getSessionFactoryBean() {
		LocalSessionFactoryBean lFactoryBean = new LocalSessionFactoryBean();
		lFactoryBean.setDataSource(setDataSource());
		lFactoryBean.setPackagesToScan(ENTITYSCAN);
		lFactoryBean.setHibernateProperties(setHibernateProperties());
		return lFactoryBean;

	}
	
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager txManager=new HibernateTransactionManager();
		txManager.setSessionFactory(getSessionFactoryBean().getObject());
		return txManager;
		
	}

}
