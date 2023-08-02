package com.stackroute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PaymentDto {
    private String myPaymentId;
    private String paymentId;
    private String orderId;

    @NotEmpty(message = "email is required")
    @Email(message = "enter valid email address")
    private String emailId;

    private int amount;

    private String status;

    private String receipt;
}
