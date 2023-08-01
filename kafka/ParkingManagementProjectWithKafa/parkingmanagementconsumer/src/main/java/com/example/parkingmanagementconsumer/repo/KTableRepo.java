package com.example.parkingmanagementconsumer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parkingmanagementconsumer.modal.KTable;

@Repository
public interface KTableRepo  extends JpaRepository<KTable, Integer>{
	
	

}
