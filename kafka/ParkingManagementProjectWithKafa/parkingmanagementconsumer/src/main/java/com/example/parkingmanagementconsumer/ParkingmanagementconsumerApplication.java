package com.example.parkingmanagementconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

@SpringBootApplication
public class ParkingmanagementconsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingmanagementconsumerApplication.class, args);
	}

	
}
