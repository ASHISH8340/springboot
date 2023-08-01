package com.globallogic.GloQuora_Post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GloQuoraPostApplication {

	public static void main(String[] args) {
		SpringApplication.run(GloQuoraPostApplication.class, args);
	}

}
