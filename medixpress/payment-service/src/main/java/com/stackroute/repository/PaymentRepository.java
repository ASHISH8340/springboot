package com.stackroute.repository;

import com.stackroute.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaymentRepository extends MongoRepository<Payment,String> {
    @Query(value = "{orderId : ?0}")
    Payment  findByOrderId(String orderId);

    @Query(value = "{emailId : ?0}")
    List<Payment> findByEmail(String emailId);

}
