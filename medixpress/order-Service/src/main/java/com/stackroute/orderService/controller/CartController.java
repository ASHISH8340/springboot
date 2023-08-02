package com.stackroute.orderService.controller;

import com.stackroute.orderService.model.Cart;
import com.stackroute.orderService.model.Medicines;
import com.stackroute.orderService.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping(value="/api/v1")
public class
CartController {
    @Autowired
    private CartService cartService;



    @PostMapping(value="addCart")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        log.info("User clicked on addItem method");
        return new ResponseEntity<Cart>(cartService.addItemToCart(cart), HttpStatus.OK);
    }




    @PutMapping(value="cart/{cartId}")
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart,@PathVariable long cartId) {
        return new ResponseEntity<Cart>(cartService.updateCartItem(cartId,cart),HttpStatus.OK);

    }


    @PutMapping(value="cart/Email/{userEmail}")
    public ResponseEntity<Cart> updateCartByUserEmail(@RequestBody Cart cart,@PathVariable String userEmail) {
        return new ResponseEntity<Cart>(cartService.updateCartItemByUserEmail(userEmail,cart),HttpStatus.OK);

    }

    @GetMapping(value = "cart")
    public ResponseEntity<List<Cart>> getFromCart(){
        return new ResponseEntity<List<Cart>>(cartService.getAllItemFromCart(),HttpStatus.OK);
    }


    @GetMapping(value = "cart/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable long cartId){
        return new ResponseEntity<Cart>(cartService.findById(cartId),HttpStatus.OK);
    }


    @GetMapping(value = "cart/Email/{userEmail}")
    public ResponseEntity<Cart> getCartByUserEmail(@PathVariable String userEmail) throws Exception {

            return new ResponseEntity<Cart>(cartService.findByUserEmail(userEmail), HttpStatus.OK);



    }

    @DeleteMapping(value = "cart/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable long cartId){
        log.info("User clicked on deleteItem method");
        cartService.deleteCartItem(cartId);
        return new ResponseEntity<String>("CartItem deleted Successfully!!",HttpStatus.OK);
    }



    @DeleteMapping(value = "cart/MedId/{medId}/{userEmail}")
    public ResponseEntity<Cart> deleteCart(@PathVariable("medId") String medId,@PathVariable("userEmail") String userEmail){
        log.info("User clicked on deleteItem method");
       Cart cart= cartService.deleteCartItemByMedicineId(medId,userEmail);

        return new ResponseEntity<Cart>(cart,HttpStatus.OK);
    }

    @GetMapping(value = "medicines/{cartId}")
    public ResponseEntity<List<Medicines>> getFromMedicines(@PathVariable long cartId){
        return new ResponseEntity<List<Medicines>>(cartService.getMedicinesItemsFromCart(cartId),HttpStatus.OK);
    }



    @GetMapping(value = "medicines/Email/{userEmail}")
    public ResponseEntity<List<Medicines>> getFromMedicinesByUserEmail(@PathVariable String userEmail){
        return new ResponseEntity<List<Medicines>>(cartService.getMedicinesItemsFromUserEmail(userEmail),HttpStatus.OK);
    }

    @DeleteMapping(value = "cart/Email/{userEmail}")
    public ResponseEntity<String> deleteCartByUserEmail(@PathVariable String userEmail){
        log.info("User clicked on deleteItem method");
        cartService.deleteCartItemByUserEmail(userEmail);
        return new ResponseEntity<String>("CartItem deleted Successfully!!",HttpStatus.OK);
    }


}
