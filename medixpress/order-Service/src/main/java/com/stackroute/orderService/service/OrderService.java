package com.stackroute.orderService.service;

import com.stackroute.orderService.model.Order;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OrderService {

     Order saveOrder(Order order);
     List<Order>getAllOrder();
     Order updateById(long orderId,Order order );
     Order findById(long orderId);
     void deleteById(long orderId);


}
