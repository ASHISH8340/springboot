package com.stackroute.orderService.service;

import com.stackroute.orderService.model.Cart;
import com.stackroute.orderService.model.Medicines;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CartService {
     Cart addItemToCart(Cart cart);
     Cart updateCartItem(long cartId,Cart cart);
     List<Cart>getAllItemFromCart();
     Cart findById(long cartId);
     void deleteCartItem(long cartId);

     List<Medicines>getMedicinesItemsFromCart(long cartId);

    List<Medicines>getMedicinesItemsFromUserEmail(String userEmail);

     void deleteCartItemByUserEmail(String userEmail);

   //  Cart getUserByEmail(String userEmail);
     Cart findByUserEmail(String userEmail);
     Cart updateCartItemByUserEmail(String  userEmail,Cart cart);

     Cart deleteCartItemByMedicineId(String medId, String userEmail);



}
