package com.grayson.connect_the_dots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ConnectTheDotsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectTheDotsApplication.class, args);
	}
}
