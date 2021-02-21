package com.cabsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.cabsys")	
@EnableJpaRepositories("com.cabsys.CabManagementRepository")


@EnableWebMvc
public class CabManagmentApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(CabManagmentApplication.class, args);
	}

}
