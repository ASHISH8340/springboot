package com.stackroute.orderService.repository;

import com.stackroute.orderService.model.Prescription;
import org.bson.types.Binary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends MongoRepository<Prescription, Binary> {
}
