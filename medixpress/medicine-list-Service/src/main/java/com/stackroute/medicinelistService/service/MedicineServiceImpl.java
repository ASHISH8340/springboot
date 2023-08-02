package com.stackroute.medicinelistService.service;

import com.stackroute.medicinelistService.Exceptions.MedicineNotFoundException;
import com.stackroute.medicinelistService.Exceptions.ShopNotFoundException;
import com.stackroute.medicinelistService.model.Medicine;
import com.stackroute.medicinelistService.repository.MedicineRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MedicineServiceImpl implements MedicineService{
    @Autowired
    private MedicineRepository repository;
@Autowired
SequenceGeneartor sequenceGeneartor;

    public List<Medicine> getAllMedicines() {
       return repository.findAll();
    }

    public Optional<Medicine> getMedicineById(String id) {
        return repository.findById(id);
    }

    @Override


    public Optional<Medicine> getMedicineByshopName(String shopName) {
        return repository.findMedicinesByShopName(shopName);
        }

    public Object updateDetails(String id, Medicine medicine) {
        if (!repository.existsById(id)) {
            try {
                throw new ShopNotFoundException("SHOP-UPDATE");
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
       // Optional<Medicine> byId = repository.findById(id);
        repository.deleteById(id);
        medicine.setMedicineId(id);
        repository.save(medicine);
        return medicine;
    }


    public Object addMedicine(Medicine medicine){
        medicine.setMedicineId(sequenceGeneartor.getSequenceNumber(Medicine.SEQUENCE_NAME));
        double x=medicine.getMedicinePrice();
        double y=medicine.getMedicineDiscountprice();
        double discountpercent=((x-y)/x)*100;
        medicine.setDiscountPercentage(discountpercent);

        Medicine saveMedicine=repository.save(medicine);

        return saveMedicine;
    }




    @Override
    public Object addMedicineImageBymedicineId(String medicineId, MultipartFile multipartFile) {
        log.info("In Service class of logger for adding shop");
        boolean x = repository.existsBymedicineId(medicineId);
        Medicine medicinefromRepo = null;
        if (!x) {
            try {
                throw new MedicineNotFoundException("SHOP-INFO");
            } catch (MedicineNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            medicinefromRepo = repository.findBymedicineId(medicineId);
            try {
                log.info("In Service Class for Add Method for adding Image");
                medicinefromRepo.setImage(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
                repository.save(medicinefromRepo);
                log.info("In Service Class for Add Method for adding Image sucessfull");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return medicinefromRepo;
    }
    @Override
    public Object deleteMedicines(String medicineId) {
        if (!repository.existsById(medicineId)) {
            try {
                throw new MedicineNotFoundException("SHOP-DELETE");
            } catch (MedicineNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        Object medicine=repository.findBymedicineId(medicineId);
        repository.deleteById(medicineId);
        return medicine;
    }

    public Optional<Medicine> getMedicineBymedicineName(String medicineName) {

        return repository.findBymedicineName(medicineName);
    }


    public Object getMedicineBymedicineCatagory(String medicineCatagory) {
 List<Medicine> lis=repository.findBymedicineCatagory(medicineCatagory);;

        return lis;
    }




    @Override
    public List<Medicine> getMedicineByshopEmail(String shopEmail) {

        List<Medicine> items= repository.findByshopEmail(shopEmail);
        return items;
    }

    public List<Medicine> getMedicinesByCatagory(String MedicineName) {

        List<Medicine> items= repository.getBymedicineName(MedicineName);
        return items;
    }

//    public List<Medicine> getMedicineByshopEmail(String shopEmail) {
//        return repository.findAll();
//    }



    public Object getwithMaxDiscount(){

        List<Medicine> result=repository.findAll(Sort.by(Sort.Direction.DESC,"discountPercentage"));
        System.out.println(result);
        ArrayList<Medicine> returnlist=new ArrayList<>(4);
        for(int i=0;i<4;i++){
            returnlist.add(result.get(i));
        }

        return returnlist;
    }



    public Object getwithLatestArrival(){

        List<Medicine> result=repository.findAll(Sort.by(Sort.Direction.ASC,"Double.parseDouble(medicineId)"));

        System.out.println(result);
        List<Medicine> rl=new ArrayList<>(4);
        for(int i=result.size()-1;i>=result.size()-4;i--){
            System.out.println(result.get(i) +" " +i);
            rl.add(result.get(i));
        }

        return rl;
    }




    }








/*  public Medicine addMedicines(Medicine medicine, MultipartFile multipartFile) {
        log.info("In Service class of logger for adding medicine");
        medicine.setMedicineId(sequenceGeneartor.getSequenceNumber(Medicine.SEQUENCE_NAME));
        try {
            log.info("In Service Class for Add Method for adding Image");
            medicine.setImage(new Binary(BsonBinarySubType.BINARY,multipartFile.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        repository.save(medicine);
        //  MedicineRepository.save(medicine);
        return medicine;
    }
     */
/*
    @Override
    public Object addMedicineImage(String shopEmail, MultipartFile multipartFile) {
        log.info("In Service class of logger for adding shop");
        boolean x = repository.existsByshopEmail(shopEmail);
        Medicine medicinefromRepo = null;
        if (!x) {
            try {
                throw new MedicineNotFoundException("SHOP-INFO");
            } catch (MedicineNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            medicinefromRepo = repository.findByshopEmail(shopEmail);
            try {
                log.info("In Service Class for Add Method for adding Image");
//            seeker.setResume(new Binary(BsonBinarySubType.BINARY, seekerDto.getResume().getBytes()));
                medicinefromRepo.setImage(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
                repository.save(medicinefromRepo);
                log.info("In Service Class for Add Method for adding Image sucessfull");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return medicinefromRepo;
    }*/

//@Override
//    public Object (Medicine user, MultipartFile multipartFile) {
//
//        try {
//            //log.info("In Service Class for Add Method for adding Image");
////            seeker.setResume(new Binary(BsonBinarySubType.BINARY, seekerDto.getResume().getBytes()));
//            user.setImage(new Binary(BsonBinarySubType.BINARY,multipartFile.getBytes()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Medicine savedUser=repository.save(user);
//        return savedUser;
//    }






// public Optional<Medicine> getMedicineByshopEmail(String shopEmail) {return repository.findByEmail(shopEmail);}
