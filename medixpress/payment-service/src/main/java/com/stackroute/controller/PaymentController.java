package com.stackroute.controller;

import com.razorpay.RazorpayException;
import com.stackroute.dto.PaymentDto;
import com.stackroute.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    /*
     * This Method is to create the payment order.
     * @Request Body is used to map the json content to java object
     */
    @PostMapping(value = "/payment")
    public ResponseEntity<?> addPayment(@Valid @RequestBody PaymentDto paymentDto) throws RazorpayException {
        return new ResponseEntity<>(paymentService.addPayment(paymentDto), HttpStatus.OK);
    }

    /*
     * This Method is to update the payments.
     * @Request Body is used to map the json content to java object
     */
    @PutMapping(value = "/payment")
    public ResponseEntity<PaymentDto> updatePayment(@Valid @RequestBody PaymentDto paymentDto){
        return new ResponseEntity<>(paymentService.updatePayment(paymentDto), HttpStatus.OK);
    }

    /*
     * This Method is to get all the payment details.
     */
    @GetMapping(value = "/payment")
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    /*
     * This Method is used to get the payment details By email id.
     * @PathVariable is used to fetch the content from the url
     */
    @GetMapping(value = "/payment/{emailId}")
    public ResponseEntity<List<PaymentDto>> getPaymentsByEmailId(@PathVariable String emailId) {
        return new ResponseEntity<>(paymentService.getPaymentByEmail(emailId), HttpStatus.OK);
    }

}
