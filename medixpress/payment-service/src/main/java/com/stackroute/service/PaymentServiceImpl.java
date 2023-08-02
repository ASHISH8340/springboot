package com.stackroute.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stackroute.dto.PaymentDto;
import com.stackroute.exception.PaymentNotFoundException;
import com.stackroute.model.Payment;
import com.stackroute.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PaymentDto paymentDto;

    @Autowired
    Payment payment;

    @Autowired
    Environment environment;

    /*
     * This Method is to add the payment details. By using paymentdto extracting
     * ammount.
     */

    @Override
    public String addPayment(PaymentDto paymentDto) throws RazorpayException {

        int amount = paymentDto.getAmount();
        amount = amount * 100;
        log.info("add payment Request {}", amount);

        // creating razor pay object to validate keys which are taken from razor pay
        RazorpayClient razorpayClient = new RazorpayClient(environment.getProperty("RAZOR_PAY_KEY_ID"),
                environment.getProperty("RAZOR_PAY_KEY_SECRET"));
        JSONObject options = new JSONObject();
        // convert amount in paisa
        options.put("amount", amount);
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");

        // creating order in razor pay by adding amount, currency and receipt details
        Order orderCreated = razorpayClient.Orders.create(options);
        // mapping dto to entity
        payment.setEmailId(paymentDto.getEmailId());
        payment.setAmount(paymentDto.getAmount());
        payment.setOrderId(orderCreated.get("id"));
        payment.setStatus(orderCreated.get("status"));
        payment.setReceipt(orderCreated.get("receipt"));

        // By using repository saving data to database
        Payment savedPayment = paymentRepository.save(payment);
        paymentDto.setMyPaymentId(savedPayment.getMyPaymentId());

        log.info("add payment Response {}", orderCreated.toString());
        return orderCreated.toString();


    }

    @Override
    public PaymentDto updatePayment(PaymentDto paymentDto) {
        log.info("update payment Request {}", paymentDto);
        String orderId = paymentDto.getOrderId();
        Payment findByOrderId = paymentRepository.findByOrderId(orderId);
        findByOrderId.setPaymentId(paymentDto.getPaymentId());
        findByOrderId.setStatus(paymentDto.getStatus());

        paymentDto.setMyPaymentId(findByOrderId.getMyPaymentId());
        paymentDto.setEmailId(findByOrderId.getEmailId());
        paymentDto.setAmount(findByOrderId.getAmount());
        paymentDto.setReceipt(findByOrderId.getReceipt());

        paymentRepository.save(findByOrderId);
        log.info("update payment Response {}", paymentDto);
        return paymentDto;

    }


    /*
     * This Method is to get all the payment details.
     */
    @Override
    public List<PaymentDto> getAllPayments() {
        log.info("Request to get all Payment Details {}");
        List<Payment> allPayments = paymentRepository.findAll();
        List<PaymentDto> allPaymentsDto = new ArrayList<>();
        for (Payment paymentEntity : allPayments) {
            paymentDto.setMyPaymentId(paymentEntity.getMyPaymentId());
            paymentDto.setEmailId(paymentEntity.getEmailId());
            payment.setAmount(paymentEntity.getAmount());
            paymentDto.setOrderId(paymentEntity.getOrderId());
            paymentDto.setPaymentId(paymentEntity.getPaymentId());
            paymentDto.setReceipt(paymentEntity.getReceipt());
            paymentDto.setStatus(paymentEntity.getStatus());
            allPaymentsDto.add(paymentDto);
        }
        log.info("get all payments Response {}", paymentDto);
        return allPaymentsDto;
    }

    @Override
    public List<PaymentDto> getPaymentByEmail(String emailId) {
        log.info("Request to Get Payment By Email {}", emailId);
        List<Payment> getByEmail = paymentRepository.findByEmail(emailId.toLowerCase());
        List<PaymentDto> allPaymentsDto = new ArrayList<>();
        if (!getByEmail.isEmpty()) {
            for (Payment paymentEntity : getByEmail) {
                paymentDto.setMyPaymentId(paymentEntity.getMyPaymentId());
                paymentDto.setEmailId(paymentEntity.getEmailId());
                payment.setAmount(paymentEntity.getAmount());
                paymentDto.setOrderId(paymentEntity.getOrderId());
                paymentDto.setPaymentId(paymentEntity.getPaymentId());
                paymentDto.setReceipt(paymentEntity.getReceipt());
                paymentDto.setStatus(paymentEntity.getStatus());
                allPaymentsDto.add(paymentDto);
            }
            log.info("get all payments Response {}", allPaymentsDto);
            return allPaymentsDto;
        } else {
            throw new PaymentNotFoundException("SERVICE.PAYMENT_NOT_FOUND");
        }
    }
}
