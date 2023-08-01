package com.example.parkingmanagementconsumer.controller;

import com.example.parkingmanagementconsumer.modal.KTable;
import com.example.parkingmanagementconsumer.repo.KTableRepo;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ParkingController1 {

	private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;
	private final KTableRepo kTableRepo;

	public ParkingController1(StreamsBuilderFactoryBean streamsBuilderFactoryBean,KTableRepo kTableRepo) {
		this.streamsBuilderFactoryBean = streamsBuilderFactoryBean;
		this.kTableRepo = kTableRepo;
	}

	@GetMapping("/v1/getparkingDetails")
	public ResponseEntity<String> getParkingSpace() {
		KafkaStreams kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();
		ReadOnlyKeyValueStore<String, Long> parkingSpace = kafkaStreams
				.store(StoreQueryParameters.fromNameAndType("parking_space_1", QueryableStoreTypes.keyValueStore()));
		System.out.println(parkingSpace.get("variable"));
		String noOfParkingAvailable = String.valueOf(parkingSpace.get("variable"));

		KTable kTable = new KTable();
		kTable.setNoOfParkingAvailable(noOfParkingAvailable.toString());
		kTableRepo.save(kTable);
		return new ResponseEntity<>( "No of Parking Avialable is " + parkingSpace.get("variable"),
				HttpStatus.OK);
	}

	@GetMapping("/v1/saveParkingDetails")
	public ResponseEntity<List<KTable>> getParkingSpace1() {
		List<KTable> kTables = kTableRepo.findAll();
		return new ResponseEntity<>(kTables, HttpStatus.OK);
	}
}
