package com.example.parkingmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ParkingmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingmanagementApplication.class, args);
		System.out.println("Started!!!1");
	}

}
	