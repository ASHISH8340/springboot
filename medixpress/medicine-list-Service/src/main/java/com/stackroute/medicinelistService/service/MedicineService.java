package com.stackroute.medicinelistService.service;

import com.stackroute.medicinelistService.model.Medicine;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public interface MedicineService {

    public Object addMedicine(Medicine medicine);
    //public Object addMedicineImage(String contactEmail, MultipartFile multipartFile);


//    @Override
//    MedicineService(Medicine user, MultipartFile multipartFile);
//
//    public Object addMedicineImageBymedicineId(String contactEmail, MultipartFile multipartFile);

public List<Medicine> getAllMedicines();


public Optional<Medicine> getMedicineById(String id);

//public Object getMedicineByshopEmail(String shopEmail);

    public List<Medicine>getMedicineByshopEmail(String shopEmail);

public  Optional<Medicine>getMedicineByshopName(String  shopName);


    public Object updateDetails(String id, Medicine medicine);

    Object addMedicineImageBymedicineId(String medicineId, MultipartFile multipartFile);

    public Object deleteMedicines(String medicineId);

    public  Optional<Medicine>getMedicineBymedicineName(String  medicineName);

    public Object getMedicineBymedicineCatagory(String medicineCatagory);

  //  public Object getMedicineByCatagory(String medicineName);

    public List<Medicine> getMedicinesByCatagory(String MedicineName);

    public Object getwithMaxDiscount();
    public Object getwithLatestArrival();
}
