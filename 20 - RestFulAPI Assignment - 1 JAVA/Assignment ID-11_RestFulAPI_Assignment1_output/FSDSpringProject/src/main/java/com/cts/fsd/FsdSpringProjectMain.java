package com.cts.fsd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author Amitabha Das [420652]
 *
 */
@SpringBootApplication
@ComponentScan("com.cts.fsd.*")
public class FsdSpringProjectMain extends ServletInitializer {

	public static void main(String[] args) {
		
//		set application configurations required 
//		to run the spring boot application 
//		from Eclipse
		setSpringBootApplicationConfiguration();
		
		SpringApplication.run(FsdSpringProjectMain.class, args);
	}
	

	private static void setSpringBootApplicationConfiguration() {

		System.setProperty("server.servlet.context-path", "/FsdSpringProject");
		System.setProperty("server.port", "8080");

//		spring.datasource properties
		System.setProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/fsd_hibernate?createDatabaseIfNotExist=true");
//		System.setProperty("spring.datasource.name", "fsd_hibernate");
		System.setProperty("spring.datasource.username", "root");
		System.setProperty("spring.datasource.password", "admin");
		System.setProperty("spring.datasource.driverClassName", "com.mysql.jdbc.Driver");
		System.setProperty("spring.datasource.initialize", "true");

//		spring.jpa properties
		System.setProperty("spring.jpa.generate-ddl", "true");
		System.setProperty("spring.jpa.hibernate.ddl-auto", "update");
		System.setProperty("spring.jpa.database-platform", "org.hibernate.dialect.MySQLDialect");
		System.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		System.setProperty("spring.jpa.show-sql", "true");
		
		
		System.setProperty("spring.mvc.view.prefix", "/WEB-INF/views/");
		System.setProperty("spring.mvc.view.suffix", ".jsp");

//		System.setProperty("logging.level.org.springframework", "TRACE");
//		System.setProperty("logging.level.com", "TRACE");

	}
}
