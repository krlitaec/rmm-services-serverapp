package com.rmmservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RmmServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmmServicesApplication.class, args);
	}
}
