package com.historialmedico.historialmedico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableCaching
@EnableMongoRepositories(basePackages = "com.historialmedico.historialmedico.repository")
public class HistorialmedicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistorialmedicoApplication.class, args);
	}

}
