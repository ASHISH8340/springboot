package com.stackroute.shoplistService.serviceImpl;


import com.stackroute.shoplistService.Exception.shopIdNotFound;
import com.stackroute.shoplistService.Repo.shopRepository;
import com.stackroute.shoplistService.model.Shop;
import com.stackroute.shoplistService.service.shopService;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class shopServiceImpl implements shopService {

    @Autowired
    shopRepository shoprepository;

    //Logger logger= (Logger) LoggerFactory.getLogger(shopServiceImpl.class);


    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    MongoOperations mongoOperations;

  //  Method for adding shop Image
    @Override
    public Object addShopImage(String shopid, MultipartFile multipartFile) {
        log.info("In Service class of logger for adding shop");
        boolean x=shoprepository.existsById(shopid);
        Shop shopfromRepo=null;
        if (!x){
            throw new shopIdNotFound("SHOP-INFO");
        }else {
            shopfromRepo= shoprepository.findByshopId(shopid).get();
            try {
                log.info("In Service Class for Add Method for adding Image");
//            seeker.setResume(new Binary(BsonBinarySubType.BINARY, seekerDto.getResume().getBytes()));
                shopfromRepo.setShopImage(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
                shoprepository.save(shopfromRepo);
                log.info("In Service Class for Add Method for adding Image sucessfull");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return shopfromRepo;
    }


    public Object addShopdto(Shop shop){
        shop.setShopId(sequenceGeneratorService.getSequenceNumber(Shop.SEQUENCE_NAME));
        Shop savedShop=shoprepository.save(shop);
        return savedShop;
    }

    //Method for Viewing all the Shop and Shop Details
    @Override
    public Object viewallShop() {
        return shoprepository.findAll();
    }


    //Method for Viewing Shop Details using ShopId
    @Override
    public Object viewShopById(String shopId) {
        Optional<Shop> shopfromRepo= shoprepository.findByshopId(shopId);
        if (shopfromRepo.isPresent()){
            return shopfromRepo.get();
        }else {
            throw new shopIdNotFound("SHOP-INFO");
        }

    }

    //Method for Deleting the Shop with ShopId
    @Override
    public Object deleteShop(String shopId) {
        if (!shoprepository.existsById(shopId)) {
            throw new shopIdNotFound("SHOP-DELETE");
        }
        Object shop=shoprepository.findByshopId(shopId);
        shoprepository.deleteById(shopId);
        return shop;
    }

    //Method for Updating Shop Details using ShopId
    @Override
    public Object updateDetails(String shopId, Shop shop) {

           Optional<Shop> shopFromDb= shoprepository.findByshopId(shopId);
           Shop updateShop=null;
           if (shopFromDb.isPresent()){
               Shop shopfromRepo=shopFromDb.get();
               shopfromRepo.setShopName(shop.getShopName());
               shopfromRepo.setPincode(shop.getPincode());
               shopfromRepo.setContactEmail(shop.getContactEmail());
               shopfromRepo.setCity(shop.getCity());
               shopfromRepo.setDescription(shop.getDescription());
               shopfromRepo.setLandmark(shop.getLandmark());
               shopfromRepo.setState(shop.getState());
               shopfromRepo.setCity(shop.getCity());
               shopfromRepo.setStreetName(shop.getStreetName());
               shopfromRepo.setDescription(shop.getDescription());
               shopfromRepo.setShopItems(shop.getShopItems());
               shopfromRepo.setShopVerification(shop.getShopVerification());
               shopfromRepo.setContactNumber(shop.getContactNumber());
               shopfromRepo.setShopOwnerName(shop.getShopOwnerName());
               updateShop=shoprepository.save(shopfromRepo);
               updateShop.setShopId(shopId);
           }else {
               throw new shopIdNotFound("SHOP-UPDATE");
           }

        return updateShop;
    }


    //Method for Getting all the Shops using ShopId
    @Override
    public Object ViewShopByPincode(String pincode) {
        if(!shoprepository.existsBypincode(pincode)){
            throw new shopIdNotFound("SHOP-PINCODE-NOT-FOUND");
        }
        return shoprepository.findBypincode(pincode);



    }


    //Method for getting List of Medicines using Email
    public Object findByshopEmail(String contactEmail){
        if(!shoprepository.existsBycontactEmail(contactEmail)){
            throw new shopIdNotFound("SHOP-EMAIL-NOT-PRESENT");
        }
    //    System.out.println(shoprepository.findBycontactEmail(contactEmail));
        return shoprepository.findBycontactEmail(contactEmail);
    }

}
