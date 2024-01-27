package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
@OpenAPIDefinition
public class AlgoTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoTestApplication.class, args);
	}

}
