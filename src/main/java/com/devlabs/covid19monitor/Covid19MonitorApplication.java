package com.devlabs.covid19monitor;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class Covid19MonitorApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(Covid19MonitorApplication.class, args);
	}
	
}
