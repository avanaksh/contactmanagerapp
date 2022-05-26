package com.iasri.contact.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.iasri.contact.dao.ContactDAO;
import com.iasri.contact.dao.ContactDAOImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.iasri.contact")
public class SpringMvcConfig implements WebMvcConfigurer {
	
	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/contactdb");//?autoReconnect=true&useSSL=false;
		dataSource.setUsername("root");
		dataSource.setPassword("12345678");
		return dataSource;
	}
	
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver=new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public ContactDAO getContactDAO(){
		return new ContactDAOImpl(getDataSource());
	}

}
