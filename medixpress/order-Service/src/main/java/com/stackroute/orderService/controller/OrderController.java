package com.stackroute.orderService.controller;

import com.google.gson.Gson;
import com.stackroute.orderService.exception.DetailsNotMatchingException;
import com.stackroute.orderService.model.Order;
import com.stackroute.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping(value="add")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return new ResponseEntity<Order>(orderService.saveOrder(order), HttpStatus.OK);
    }

    @PutMapping(value="order/{orderId}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order,@PathVariable long orderId) {
        return new ResponseEntity<Order>(orderService.updateById(orderId,order),HttpStatus.OK);

    }

    @GetMapping(value = "order")
    public ResponseEntity<List<Order>> getAllOrder(){
        return new ResponseEntity<List<Order>>(orderService.getAllOrder(),HttpStatus.OK);
    }


    @GetMapping(value="order/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable long orderId) {
        return new ResponseEntity<Order>(orderService.findById(orderId),HttpStatus.OK);

    }

    @DeleteMapping(value="order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable long orderId) {
        orderService.deleteById(orderId);
        return new ResponseEntity<String>("Order deleted successfully",HttpStatus.OK);

    }

}
