package com.onlinebookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EOnlineBooksWorldStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EOnlineBooksWorldStoreApplication.class, args);
	}

}
