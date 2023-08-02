package com.stackroute.orderService.serviceimpl;

import com.stackroute.orderService.model.Prescription;
import com.stackroute.orderService.repository.PrescriptionRepository;
import com.stackroute.orderService.service.PrescriptionService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Prescription addPrescription(Prescription prescription, MultipartFile multipartFile) {
        try{
            prescription.setPrescription(new Binary(BsonBinarySubType.BINARY,multipartFile.getBytes()));
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        Prescription addPrescription = prescriptionRepository.save(prescription);
        return addPrescription ;
    }
}
