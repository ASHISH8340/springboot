package com.example.parkingmanagementconsumer.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ktable")
public class KTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String noOfParkingAvailable;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNoOfParkingAvailable() {
		return noOfParkingAvailable;
	}
	public void setNoOfParkingAvailable(String noOfParkingAvailable) {
		this.noOfParkingAvailable = noOfParkingAvailable;
	}
	@Override
	public String toString() {
		return "KTable [id=" + id + ", noOfParkingAvailable=" + noOfParkingAvailable + "]";
	}
	
	
	
	

}
