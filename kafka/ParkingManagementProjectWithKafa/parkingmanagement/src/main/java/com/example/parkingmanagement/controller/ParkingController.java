package com.example.parkingmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parkingmanagement.kafkaproducer.KafkaProducer;

import kotlin.coroutines.RestrictsSuspension;

@RestController
public class ParkingController {

	@Autowired
	KafkaProducer kafkaProducer;
	@GetMapping(value="/parkingDetails")
	public ResponseEntity<Object> sendParkingDetails(){
		kafkaProducer.sendMessage();
		return new ResponseEntity<Object>("Done ",HttpStatus.OK);
	}
	
}
