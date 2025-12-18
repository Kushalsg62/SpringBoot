package com.viva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.viva.utils.ShelfLogger;

@SpringBootApplication
public class BookShelfAutomation {

	static ShelfLogger logger = new ShelfLogger(BookShelfAutomation.class);// initializing logger

	public static void main(String[] args) {// main function of the application
		SpringApplication.run(BookShelfAutomation.class, args);

		logger.info("...................Intialising Logger!...................");
	}

}
