package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Document(collection = "Payments")
public class Payment {
    @Id
    private String myPaymentId;
    private String paymentId;
    private String orderId;
    private String emailId;
    private int amount;
    private String status;
    private String receipt;
}
