package com.example.moviechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone; // Import this

@SpringBootApplication
public class MoviechatApplication {

	public static void main(String[] args) {
		// FIX: Force the timezone to UTC to avoid "Asia/Calcutta" error
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		SpringApplication.run(MoviechatApplication.class, args);
	}

}