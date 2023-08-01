package com.Truck_Producer.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class TruckManagement {



	@Bean
	public NewTopic topic() {

		return TopicBuilder.name("truck_topic").partitions(1).replicas(1).build();
	}

	@Autowired
	KafkaTemplate<String, String> template;


	@RequestMapping("/send")
	public String  readFile() throws IOException {

			File file = new File("TrucksDataSet.txt");

			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;

			while ((st = br.readLine()) != null) {

				 String[] str = st.split(",");
				 if(str.length>=5) {
				template.send("truck_topic","keyTruck", st);


     		    }

			}
			return "messege send";

	}


}
