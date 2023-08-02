package com.stackroute.service;

import com.razorpay.RazorpayException;
import com.stackroute.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    String addPayment(PaymentDto paymentDto) throws RazorpayException;

    PaymentDto updatePayment(PaymentDto paymentDto);

    List<PaymentDto> getAllPayments();

    List<PaymentDto> getPaymentByEmail(String emailId);


}
