package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan({
//	"com.example.demo.query",
//	"com.example.demo.mutation",
//	"com.example.demo.resolver",
//	"com.example.demo.configuration",
//	"com.example.demo.service"
//})
//@EnableJpaRepositories("com.example.demo.repository")
//@EntityScan("com.example.demo.model")
@SpringBootApplication
public class GraphQLWithSpringBootApplication {

	public static void main(String[] args) {

		SpringApplication.run(GraphQLWithSpringBootApplication.class, args);
	}

}
