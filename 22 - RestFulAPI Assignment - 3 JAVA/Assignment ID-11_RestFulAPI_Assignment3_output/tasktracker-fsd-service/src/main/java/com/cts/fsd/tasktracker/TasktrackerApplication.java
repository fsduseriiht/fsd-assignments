package com.cts.fsd.tasktracker;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Amitabha Das [420652]
 *
 */
@SpringBootApplication
public class TasktrackerApplication extends ServletInitializer {
	public static void main(String[] args) throws IOException {
		
//		set application configurations required to run the spring boot application
		setSpringBootApplicationConfiguration();
		
//		actual method call inside the main() method to run Spring Boot Application 
		SpringApplication.run(TasktrackerApplication.class, args);
	}
	
	
	private static void setSpringBootApplicationConfiguration() {
		
		System.setProperty("server.servlet.context-path", "/tasktrackerbackend");
//		System.setProperty("server.port", "8080");
		
//		spring.datasource properties
		System.setProperty("spring.datasource.url", "jdbc:h2:file:~\\tmp\\tasktracker");
		System.setProperty("spring.datasource.name", "task_db");
		System.setProperty("spring.datasource.username", "SA");
		System.setProperty("spring.datasource.password", "");
		System.setProperty("spring.datasource.driverClassName", "org.h2.Driver");
		System.setProperty("spring.datasource.initialize", "true");
		
		
//		spring.jpa properties
		System.setProperty("spring.jpa.generate-ddl", "true");
		System.setProperty("spring.jpa.hibernate.ddl-auto", "update");
		System.setProperty("spring.jpa.database-platform", "org.hibernate.dialect.H2Dialect");
		System.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		System.setProperty("spring.jpa.show-sql", "true");
		
//		spring.h2 properties
		System.setProperty("spring.h2.console.enabled", "true");
		System.setProperty("spring.h2.console.path", "/h2-console");
		
	}
}
