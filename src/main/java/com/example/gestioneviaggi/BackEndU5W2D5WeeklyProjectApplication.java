package com.example.gestioneviaggi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.example.gestioneviaggi.repository")
@EntityScan("com.example.gestioneviaggi.model")

public class BackEndU5W2D5WeeklyProjectApplication {



	public static void main(String[] args) {
		SpringApplication.run(BackEndU5W2D5WeeklyProjectApplication.class, args);
	}



}
