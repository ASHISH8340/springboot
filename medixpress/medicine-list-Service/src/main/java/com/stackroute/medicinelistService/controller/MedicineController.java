package com.stackroute.medicinelistService.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.stackroute.medicinelistService.Exceptions.MedicineNotFoundException;
import com.stackroute.medicinelistService.model.Medicine;
import com.stackroute.medicinelistService.repository.MedicineRepository;
import com.stackroute.medicinelistService.service.MedicineServiceImpl;
import com.stackroute.medicinelistService.service.SequenceGeneartor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Api(value = "MedicineController", description = "REST Apis related to MedicineController !!!!")
@Slf4j
@RestController
@RequestMapping("api/v1")
   public class MedicineController {

    @Autowired
    private MedicineRepository repo;
    @Autowired
    private MedicineServiceImpl service;
    @Autowired
    private SequenceGeneartor seq;
    private Optional<Medicine> med;

           @ApiOperation(value = "This API is used to add Medicine to database", response = Medicine.class, tags = "addMedicine")
            @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @PostMapping("/addimages/{medicineId}")
    public ResponseEntity<Object> addMedicineImageBymedicineId(@PathVariable String medicineId,@RequestParam(value = "multipartFile") MultipartFile multipartFile) throws MedicineNotFoundException {
        System.out.println("test ="+medicineId);
               ResponseEntity<Object> responseEntity;
        log.info("In controller class to Add Info");
        Medicine medicineFromService= (Medicine) service.addMedicineImageBymedicineId(medicineId,multipartFile);
        responseEntity = new ResponseEntity<Object>(medicineFromService, HttpStatus.OK);
        return responseEntity;
    }
 /*   @PostMapping("/addUserImageByMail")
    public ResponseEntity<Object> addImageByMailId(@RequestParam("email") String email,@RequestParam(value = "multipartFile" , required = false) MultipartFile multipartFile)
    {
        ResponseEntity<Object> responseEntity;
        User userFetch=userserviceimpl.getUserByEmail(email);

        System.out.println("Test5.....mail is="+email);

        ImageUserDTO user = new ImageUserDTO();

        user.setEmailId(userFetch.getEmailId());
        user.setName(userFetch.getName());
        user.setContactNo(userFetch.getContactNo());
        user.setPassword(userFetch.getPassword());
        user.setGender(userFetch.getGender());


        user.setUserRole(userFetch.getUserRole());


        if(userFetch.getAddress()!=null){


            List<Address> address2= new ArrayList<>(userFetch.getAddress());

            user.setAddress(address2);

        }
        else{

            user.setAddress(null);
        }


        ObjectMapper objMapper =new ObjectMapper();

        try {
            String objinstring = objMapper.writeValueAsString(user);
            System.out.println("Test6.....user body in string is="+objinstring);
            Gson gson = new Gson();
            User courseFileObj = gson.fromJson(objinstring, User.class);
            User shopfromservice= (User) photoService.addImage(courseFileObj,multipartFile);
            responseEntity = new ResponseEntity<Object>(shopfromservice, HttpStatus.OK);
            return responseEntity;
        }catch(JsonProcessingException e){
            e.printStackTrace();

        }

        // String objinstring=user.toString();

        //  System.out.println("Test6.....user body in string is="+objinstring);
        return null;

    }
*/
 /*   @PostMapping("/addUserImageByMail")
    public ResponseEntity<Object> addImageByMailId(@RequestParam("email") String email,@RequestParam(value = "multipartFile" , required = false) MultipartFile multipartFile)
    {
        ResponseEntity<Object> responseEntity;
        User userFetch=userserviceimpl.getUserByEmail(email);

        System.out.println("Test5.....mail is="+email);

        ImageUserDTO user = new ImageUserDTO();

        user.setEmailId(userFetch.getEmailId());
        user.setName(userFetch.getName());
        user.setContactNo(userFetch.getContactNo());
        user.setPassword(userFetch.getPassword());
        user.setGender(userFetch.getGender());


        user.setUserRole(userFetch.getUserRole());


        if(userFetch.getAddress()!=null){


            List<Address> address2= new ArrayList<>(userFetch.getAddress());

            user.setAddress(address2);

        }
        else{

            user.setAddress(null);
        }


        ObjectMapper objMapper =new ObjectMapper();

        try {
            String objinstring = objMapper.writeValueAsString(user);
            System.out.println("Test6.....user body in string is="+objinstring);
            Gson gson = new Gson();
            User courseFileObj = gson.fromJson(objinstring, User.class);
            User shopfromservice= (User) photoService.addImage(courseFileObj,multipartFile);
            responseEntity = new ResponseEntity<Object>(shopfromservice, HttpStatus.OK);
            return responseEntity;
        }catch(JsonProcessingException e){
            e.printStackTrace();

        }

        // String objinstring=user.toString();

        //  System.out.println("Test6.....user body in string is="+objinstring);
        return null;

    }
*/


    @PostMapping("/addMedicines")
    public ResponseEntity<Object> addMedicines(@RequestBody Medicine medicine) {
        service.addMedicine(medicine);
        return new ResponseEntity<Object>(medicine,HttpStatus.OK);
           }


    @GetMapping("/getallMedicine")
    public ResponseEntity getallMedicine(){
        if(service.getAllMedicines().size()!=0) {
            return new ResponseEntity(service.getAllMedicines(),HttpStatus.OK);
        }else {
            return new ResponseEntity("Data Not Available in DB",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getMedicineById/{id}")
    public ResponseEntity getMedicineById(@PathVariable(name="id") String id) {
               if(service.getMedicineById(id).isPresent()) {
             med=service.getMedicineById(id);
            return new ResponseEntity(med, HttpStatus.OK);

        }else {
            return new ResponseEntity("No Data Found", HttpStatus.NOT_FOUND);
        }
           }
//    @GetMapping("/getMedicineByshopEmail/{shopEmail}")
//    public ResponseEntity getMedicineByshopEmail(@PathVariable(name="shopEmail") String shopEmail) {
//               if (service.getMedicineByshopEmail(shopEmail).isPresent()) {
//            med = service.getMedicineByshopEmail(shopEmail);
//            return new ResponseEntity(med, HttpStatus.OK);
//               } else {
//            return new ResponseEntity("No Data Found", HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping(value = "/getMedicineByshopEmail/{shopEmail}")
    public ResponseEntity<List<Medicine>> getMedicineByshopEmail(@PathVariable String shopEmail){
        return new ResponseEntity<List<Medicine>>(service.getMedicineByshopEmail(shopEmail),HttpStatus.OK);
    }







    @GetMapping("/getMedicineByshopName/{shopName}")
    public ResponseEntity getMedicineByshopName(@PathVariable(name="shopName") String shopName) {
               if (service.getMedicineByshopName(shopName).isPresent()) {
               med = service.getMedicineByshopName(shopName);
            return new ResponseEntity(med, HttpStatus.OK);
        } else {
            return new ResponseEntity("No Data Found", HttpStatus.NOT_FOUND);
        }
    }

        @PutMapping(value = "updateMedicine/{id}")
    public ResponseEntity<Object> updateMedicine(@RequestBody Medicine medicine,@PathVariable String id){
        service.updateDetails(id,medicine);
        return new ResponseEntity<Object>(medicine,HttpStatus.OK);
    }

    @DeleteMapping(value="deleteMedicine/{medicineId}")
    public ResponseEntity<Object> deleteMedicines(@PathVariable String medicineId){
        Object medicine=service.deleteMedicines(medicineId);
        return new ResponseEntity<Object>(medicine,HttpStatus.OK);
    }

    @GetMapping("/getMedicineBymedicineName/{medicineName}")
    public ResponseEntity getMedicineBymedicineName(@PathVariable(name="medicineName") String medicineName) {
        if (service.getMedicineBymedicineName(medicineName).isPresent()) {
            med = service.getMedicineBymedicineName(medicineName);
            return new ResponseEntity(med, HttpStatus.OK);
        } else {
            return new ResponseEntity("No Data Found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getMedicineBymedicineCatagory/{medicineCatagory}")
    public ResponseEntity getMedicineBymedicineCatagory(@PathVariable(name="medicineCatagory") String medicineCatagory) {
        Object obj;
       // if (service.getMedicineBymedicineCatagory(medicineCatagory)) {
            obj = service.getMedicineBymedicineCatagory(medicineCatagory);
            return new ResponseEntity(obj, HttpStatus.OK);
    //    } else {
    //        return new ResponseEntity("No Data Found", HttpStatus.NOT_FOUND);
     //   }
    }
    @GetMapping("getwithMaxDiscount")
    public ResponseEntity<Object> getwitmaxDiscount(){
        Object returnObj=service.getwithMaxDiscount();
        return new ResponseEntity<Object>(returnObj,HttpStatus.OK);
    }

//    @GetMapping("/getMedicineByCatagory/{medicineName}")
//    public ResponseEntity getMedicineByCatagory(@PathVariable(name="medicineName") String medicineName) {
//        Medicine obj;
//
//        // if (service.getMedicineBymedicineCatagory(medicineCatagory)) {
//        obj = service.getMedicineByCatagory(medicineName);
//        return new ResponseEntity(obj, HttpStatus.OK);
//        //    } else {
//        //        return new ResponseEntity("No Data Found", HttpStatus.NOT_FOUND);
//        //   }
//    }
    @GetMapping(value = "/getCatagory/{medicineName}")
    public ResponseEntity<List<Medicine>> getMedicinesByCatagory(@PathVariable String medicineName){
        return new ResponseEntity<List<Medicine>>(service.getMedicinesByCatagory(medicineName),HttpStatus.OK);
    }

    @GetMapping("getwithLatestArrival")
    public ResponseEntity<Object> getwithLatestArrival(){
        Object returnObj1=service.getwithLatestArrival();
        return new ResponseEntity<Object>(returnObj1,HttpStatus.OK);
    }

}


