package com.example.parkingmanagement.kafkaproducer;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage() {
		logger.info("Inside sendMessage Of KafkaProducer");
		for (int i = 1; i <= 1000; i++) {
			String parkingAvailibilty = String.valueOf(Math.round(Math.random() * 1000));
			logger.info("Parking avialibilty is :{}", parkingAvailibilty);
			kafkaTemplate.send("live_avl_free_space","variable", parkingAvailibilty);
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				String errorMessage = MessageFormat.format("Thread got interrupted :{0}", e.getMessage());
				logger.error(errorMessage);
				e.printStackTrace();

			}
		}
	}

}