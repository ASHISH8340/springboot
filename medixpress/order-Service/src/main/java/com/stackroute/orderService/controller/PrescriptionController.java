package com.stackroute.orderService.controller;

import com.google.gson.Gson;
import com.stackroute.orderService.exception.DetailsNotMatchingException;
import com.stackroute.orderService.model.Prescription;
import com.stackroute.orderService.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class PrescriptionController {
    @Autowired
    PrescriptionService prescriptionService;

    @PostMapping(value = "/prescription")
    ResponseEntity<Prescription> addPrescription(@RequestParam("prescription") String prescription,
                                                 @RequestParam("file") MultipartFile file) throws DetailsNotMatchingException{
        Gson gson = new Gson();
        Prescription fromJson = gson.fromJson(prescription, Prescription.class);
        return new ResponseEntity<Prescription>(prescriptionService.addPrescription(fromJson, file),HttpStatus.OK);
    }

}





