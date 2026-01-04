package com.docencia.hotel_puerto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.docencia.hotel_puerto.persistence.repository.jpa")
@EnableMongoRepositories(basePackages = "com.docencia.hotel_puerto.persistence.repository.nosql")
public class HotelPuertoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelPuertoApplication.class, args);
	}

}
