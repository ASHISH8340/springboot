package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServerApplication {

	//@Value("${app.name}")
	// String hello;



	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);



		//System.out.println(ConfigServerApplication.hello);
	}

}
