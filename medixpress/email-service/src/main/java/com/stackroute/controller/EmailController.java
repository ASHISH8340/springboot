package com.stackroute.controller;

import com.stackroute.dto.MailResponse;
import com.stackroute.dto.OrderEmail;
import com.stackroute.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmailController {
    @Autowired
    EmailService emailService;

    @PostMapping("/sendingEmail")
    ResponseEntity<MailResponse> sendEmail(@RequestBody OrderEmail orderEmail){
        Map<String,Object> model=new HashMap<>();
        model.put("firstName", orderEmail.getFirstName());
        model.put("medicineList", orderEmail.getMedicineList());
        model.put("orderId", orderEmail.getOrderId());
        model.put("orderValue", orderEmail.getOrderValue());
        return new ResponseEntity<>(emailService.sendEmail(orderEmail, model), HttpStatus.OK);

    }

}
