package com.stackroute.shoplistService.Controller;


import com.stackroute.shoplistService.model.Shop;
import com.stackroute.shoplistService.serviceImpl.shopServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequestMapping("/api/v1")
@RestController
public class shopController {

    @Autowired
    shopServiceImpl shopserviceimpl;




    @PostMapping("/addimage/{shopid}")
    public ResponseEntity<Object> addShop(@PathVariable String shopid,@RequestParam(value = "multipartFile") MultipartFile multipartFile) {
        ResponseEntity<Object> responseEntity;
                log.info("In controlller class to Add Info");
        //        Gson gson = new Gson();
         //       Shop courseFileObj = gson.fromJson(contactEmail, Shop.class);
                Shop shopfromservice= (Shop) shopserviceimpl.addShopImage(shopid,multipartFile);
                responseEntity = new  ResponseEntity<Object>(shopfromservice, HttpStatus.OK);
        return responseEntity;
    }



    @PostMapping("add")
    public ResponseEntity<Object> addShopdto(@RequestBody Shop shop){
        shopserviceimpl.addShopdto(shop);
        return new ResponseEntity<Object>(shop,HttpStatus.OK);

    }

    @GetMapping("/getallshop")
    public ResponseEntity<Object> getallshop(){
        return new ResponseEntity<Object>(shopserviceimpl.viewallShop(),HttpStatus.OK);
    }


    @GetMapping(value="getById/{shopId}")
    public ResponseEntity<Object> viewShop(@PathVariable String shopId){
        Object shop=shopserviceimpl.viewShopById(shopId);
        return new ResponseEntity<Object>(shop,HttpStatus.OK);
    }

    @DeleteMapping(value="deleteShop/{shopId}")
    public ResponseEntity<Object> deleteShop(@PathVariable String shopId){
        Object shop=shopserviceimpl.deleteShop(shopId);
        return new ResponseEntity<Object>(shop,HttpStatus.OK);
    }

    @PutMapping(value = "updateShop/{shopId}")
    public ResponseEntity<Object> updateShop(@RequestBody Shop shop,@PathVariable String shopId){
        shopserviceimpl.updateDetails(shopId,shop);
        return new ResponseEntity<Object>(shop,HttpStatus.OK);
    }

    @GetMapping(value="getbypincode/{pincode}")
    public ResponseEntity<Object> getBypincode(@PathVariable String pincode){

        Object shop=shopserviceimpl.ViewShopByPincode(pincode);
        return new ResponseEntity<Object>(shop,HttpStatus.OK);
    }

    @GetMapping(value = "getBycontactEmail/{contactEmail}")
    public ResponseEntity<Object> getByEmailMedicine(@PathVariable String contactEmail){
        return new ResponseEntity<Object>(shopserviceimpl.findByshopEmail(contactEmail),HttpStatus.OK);
    }

}
