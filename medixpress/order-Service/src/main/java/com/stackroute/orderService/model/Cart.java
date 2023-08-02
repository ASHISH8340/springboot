package com.stackroute.orderService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="cart_details")
public class Cart  {
    @Transient
    public static final String SEQUENCE_NAME="cart_sequence";
    @Id
    private long cartId;
    private int cartQuantity;
    private String userEmail;
    private List<Medicines>medicines;
    private Date deliveryDate;


}
