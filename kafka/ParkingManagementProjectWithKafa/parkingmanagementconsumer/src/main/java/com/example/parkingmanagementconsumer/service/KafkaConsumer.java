package com.example.parkingmanagementconsumer.service;

import java.sql.Date;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "live_avl_empty_space_warning", groupId = "group_id")
	public void getWarning(ConsumerRecord<String, String> record) {
		System.out.println("Topic: " + record.topic());
		logger.info("Warinig data is :" + record.key() + " " + record.value() + " ");

	}

	@KafkaListener(topics = "live_avl_free_space", groupId = "group_id")
	public void getParkingSpace(ConsumerRecord<String, String> record) {
		System.out.println("====================================");
		
		System.out.println("Key is :" + record.key());
		System.out.println("Value is :" + record.value());
		System.out.println("Offset is :" + record.offset());
		System.out.println("Timestamp is :" + record.timestamp());
		System.out.println("Partition  is :" + record.partition());
		System.out.println("Topic: " + record.topic());
		Date date = new Date(record.timestamp());
		System.out.println("Date is:" + date);
		System.out.println("====================================");
	}
}
