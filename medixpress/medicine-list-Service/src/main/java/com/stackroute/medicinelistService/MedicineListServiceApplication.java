package com.stackroute.medicinelistService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.stackroute"})
@EntityScan(basePackages = "com.stackroute")
@EnableEurekaClient
public class MedicineListServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(MedicineListServiceApplication.class, args);
	}

}
