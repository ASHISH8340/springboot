package com.stackroute.shoplistService.service;


import com.stackroute.shoplistService.model.Shop;
import org.springframework.web.multipart.MultipartFile;

public interface shopService {
//    public Object addShop(Shop shop, MultipartFile file);
    public Object addShopImage(String contactEmail, MultipartFile multipartFile);
    public Object addShopdto(Shop shop);
    public Object viewallShop();
    public Object viewShopById(String shopId);
    public Object deleteShop(String shopId);
    public Object updateDetails(String shopId,Shop  shop);

    public Object ViewShopByPincode(String pincode);

    public Object findByshopEmail(String email);
}
