//package com.stackroute.orderService.serviceimpl;
//
//import com.stackroute.orderService.exception.OrderNotFoundException;
//import com.stackroute.orderService.model.Order;
//import com.stackroute.orderService.repository.OrderRepository;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class OrderServiceImplTest {
//    @Autowired
//    private OrderServiceImpl orderService;
//
//
//    @MockBean
//    private OrderRepository orderRepository;
//
//
//
//    @Test
//    void testFindById(){
//          Order order=new Order();
//          order.setOrderId(1L);
//
//         when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
//         Order orderResult =orderService.findById(order.getOrderId());
//         assertEquals(order,orderResult);
//
//
//    }
//
//    @Test
//    void testGetAllOrder(){
//        Order order1 =new Order();
//        order1.setOrderId(8L);
//        order1.setPrice(100.00);
//        order1.setDeliveryDate(LocalDate.now());
//
//        Order order2 = new Order();
//        order2.setOrderId(9L);
//        order2.setPrice(395.65);
//        order2.setDeliveryDate(LocalDate.now());
//
//        when(orderRepository.findAll()).thenReturn(List.of(order1,order2));
//        List<Order>orderListResult=orderService.getAllOrder();
//        assertEquals(2,orderListResult.size());
//        assertEquals(order1,orderListResult.get(0));
//        assertEquals(order2,orderListResult.get(1));
//
//    }
//
//
//    @Test
//    void testDeleteById()  {
//        assertThrows(OrderNotFoundException.class,()->{
//            Order order=new Order();
//            order.setOrderId(21L);
//            order.setDeliveryDate(LocalDate.now());
//            orderService.deleteById(order.getOrderId());
//            verify(orderRepository,times(1)).deleteById(order.getOrderId());
//
//        });
//
//    }
//    @Test
//    void testUpdateOrder(){
//        Order order=new Order();
//        order.setOrderId(2L);
//        order.setUserEmail("ashishmourya@gmail.com");
//        OrderNotFoundException orderNotFoundException=assertThrows(OrderNotFoundException.class,
//                ()->orderService.updateById(2L,order));
//        assertEquals("SERVICE.Order-Service_NOT_FOUND",orderNotFoundException.getMessage());
//
//    }
//    @Test
//    void testSaveOrder(){
//        Order order=new Order();
//        order.setOrderId(10L);
//        order.setPrice(200.88);
//        order.setDeliveryDate(LocalDate.now());
//        when(orderRepository.save(order)).thenReturn(order);
//        assertEquals(order,orderService.saveOrder(order));
//    }




//}
