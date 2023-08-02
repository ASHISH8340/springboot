package com.stackroute.orderService.serviceimpl;

import com.stackroute.orderService.exception.OrderNotFoundException;
import com.stackroute.orderService.model.Order;
import com.stackroute.orderService.repository.OrderRepository;
import com.stackroute.orderService.service.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;



@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    Environment environment;

    @Autowired
    SequenceGeneratorService service;


    @Override
    public Order saveOrder(Order order) {
        log.info("Order List,service; save Order");
        order.setOrderId(service.getSequenceNumber(Order.SEQUENCE_NAME));
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    @Override
    public List<Order> getAllOrder() {
        log.info("Order List,service; fetch all orders");
        //System.out.println("data from database:"+orderRepository.findAll());
        return orderRepository.findAll();
    }

    @Override
    public Order updateById(long orderId, Order order) {
        log.info(" Order List,service; update order with orderId ");
        Optional<Order> orderFromDb = orderRepository.findById(orderId);
        Order updateOrder = null;
        if (orderFromDb.isPresent()) {
            Order orderFromRepo = orderFromDb.get();


            //orderFromRepo.setMedicines(order.getMedicines());
            orderFromRepo.setAddress(order.getAddress());
            orderFromRepo.setDeliveryDate(order.getDeliveryDate());
            updateOrder = orderRepository.save(orderFromRepo);
        }else{
            log.info("Order List,service; update Order with orderId not found");
            throw new OrderNotFoundException("SERVICE.Order-Service_NOT_FOUND");
        }
      return updateOrder;
    }

    @Override
    public Order findById(long orderId) {


        log.info("Order List,service; fetch Orders by id");
        Optional<Order>orderFromRepo= orderRepository.findById(orderId);
        if(orderFromRepo.isPresent()){
            return orderFromRepo.get();
        }else{
            log.info("Order List, service; fetch Orders not found for given id");

            throw new OrderNotFoundException("SERVICE.Order-Service_NOT_FOUND");
        }
    }

    @Override
    public void deleteById(long orderId) {
        if(!orderRepository.existsById(orderId)){
            log.info("Void,service; id not found for delete");
            throw new OrderNotFoundException("SERVICE.Order-Service_NOT_FOUND");
        }
        log.info("Void,service; delete Order by id");
        orderRepository.deleteById(orderId);

    }
}
