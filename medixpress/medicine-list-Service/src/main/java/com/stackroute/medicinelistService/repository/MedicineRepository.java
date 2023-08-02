package com.stackroute.medicinelistService.repository;

import com.stackroute.medicinelistService.model.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends MongoRepository<Medicine,String> {
   //public Medicine findByMedicineId(int id);

    //@Query(value="{ 'shopEmail' : ?0 }",fields="{ 'shopEmail' : 1, 'shopItems' : 2 }")
    @Query(value="{ 'shopEmail' : ?0 }")
    Optional<Medicine> findByEmail(String shopEmail);

    //@Query(value="{ 'shopName' : ?0 }",fields="{ 'shopName' : 1, 'medicineItems' : 1 }")
    @Query(value="{ 'shopName' : ?0 }")
    Optional<Medicine> findMedicinesByShopName(String shopName);

    public Medicine findBymedicineId(String medicineId);


    @Query(value="{ 'medicineName' : ?0 }")
    Optional<Medicine> findBymedicineName(String medicineName);

    public Boolean existsByshopEmail(String shopEmail);
    public Boolean existsBymedicineId(String medicineId);


    public Medicine findByMedicineName(String medicineName);

    @Query(value="{ 'medicineCatagory' : ?0 }")
    List<Medicine> findBymedicineCatagory(String medicineCatagory);

  // @Query(value="{ 'medicineCatagory' : ?0 }")
  // // List<Medicine> findByMedicineCatagory(String medicineCatagory);
   // List<Medicine> findByMedicineCatagory(String medicineCatagory);


   // List<Medicine> findBymedicineMedicineName(String medicineName);


    @Query(value="{'medicineName':?0}")
    List<Medicine> getBymedicineName(String medicineName);
    Boolean existsBymedicineName(String medicineName);


    @Query(value="{'shopEmail':?0}")
    List<Medicine> findByshopEmail(String shopEmail);
    //Boolean existsByshopEmail(String shopEmail);





}

//public interface shopRepository extends MongoRepository<Shop,String> {

  //  public Medicine findByshopEmail(String shopEmail);