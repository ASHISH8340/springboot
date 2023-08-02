package com.stackroute.orderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="order_details")
public class Order {

    @Transient
    public static final String SEQUENCE_NAME="order_sequence";
    @Id
    private long orderId;
    private String userEmail;
    private double price;
    @LastModifiedDate
    private Date orderDate;
    private Date deliveryDate;
    private List<Cart>cart;
    private List<Address>address;



}
