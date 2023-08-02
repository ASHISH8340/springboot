package com.stackroute.orderService.service;

import com.stackroute.orderService.model.Prescription;
import org.springframework.web.multipart.MultipartFile;

public interface PrescriptionService {
    Prescription addPrescription(Prescription prescription, MultipartFile multipartFile);
}
