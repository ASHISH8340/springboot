package com.stackroute.orderService.repository;

import com.stackroute.orderService.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {
    Optional<Order> findById(Long Id);
}
