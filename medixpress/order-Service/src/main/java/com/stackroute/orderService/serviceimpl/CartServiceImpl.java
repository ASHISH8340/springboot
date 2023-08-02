package com.stackroute.orderService.serviceimpl;

import com.stackroute.orderService.exception.CartItemNotFoundException;
import com.stackroute.orderService.model.Cart;
import com.stackroute.orderService.model.Medicines;
import com.stackroute.orderService.repository.CartRepository;
import com.stackroute.orderService.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class CartServiceImpl implements CartService {
    @Autowired
   private CartRepository cartRepository;

    @Autowired
    Environment environment;

    @Autowired
    SequenceGeneratorService service;




    @Override
    public Cart addItemToCart(Cart cart) {
        log.info("Cart List, service; save cart");
        cart.setCartId(service.getSequenceNumber(Cart.SEQUENCE_NAME));
        Cart addCartItem = cartRepository.save(cart);
        return addCartItem ;
    }



    @Override
    public Cart updateCartItem(long cartId,Cart cart) {
        log.info("Cart List,service; update cart with cartId");
        Optional<Cart> cartFromDb = cartRepository.findById(cartId);
        Cart updateCart = null;
        if (cartFromDb.isPresent()) {
            Cart CartFromRepo = cartFromDb.get();
            CartFromRepo.setCartQuantity(cart.getCartQuantity());
            CartFromRepo.setDeliveryDate(cart.getDeliveryDate());
            CartFromRepo.setMedicines(cart.getMedicines());

            updateCart = cartRepository.save(CartFromRepo);
        }else{
            log.info("Cart List, service; update cart with carId not found");
            throw new CartItemNotFoundException("SERVICE.Cart-Item_NOT_FOUND");
        }

        return updateCart;
    }

    @Override
    public List<Cart> getAllItemFromCart() {
        log.info("cart List, service; fetch all carts ");

        return cartRepository.findAll();
    }

    @Override
    public Cart findById(long cartId) {
        log.info("Cart List, service; fetch cart by id");
        Optional<Cart>cartFromRepo=cartRepository.findById(cartId);
        if(cartFromRepo.isPresent()){
            return cartFromRepo.get();
        }else{
            log.info("Cart List, service; fetch cart not found for given id");
            throw new CartItemNotFoundException("SERVICE.Cart-Item_NOT_FOUND");
        }

    }

    @Override
    public void deleteCartItem(long cartId) {
        if(!cartRepository.existsById(cartId)){
            log.info("Void, service; id not found for delete");
            throw new CartItemNotFoundException("SERVICE.Cart-Item-Delete_NOT_FOUND");
        }
        log.info("Void, service; delete cart by id");
        cartRepository.deleteById(cartId);

    }


    @Override
    public List<Medicines> getMedicinesItemsFromCart(long cartId) {
        if(!cartRepository.existsById(cartId)){
            log.info("Medicines List, service; fetch cart by id not found");
            throw new CartItemNotFoundException("SERVICE.Cart-Item-Medicine_NOT_FOUND");
        }
        log.info("Medicines List, service; fetch cart by id");
        List<Medicines> items=cartRepository.getCart(cartId);
        return items;
    }

    @Override
    public List<Medicines> getMedicinesItemsFromUserEmail(String userEmail) {
        if(!cartRepository.existsByuserEmail(userEmail)){
            throw new CartItemNotFoundException("SERVICE.Cart-Item-Medicine_NOT_FOUND");
        }
        List<Medicines> items= cartRepository.getCartByUserEmail(userEmail);
        return items;
    }

    @Override
    public void deleteCartItemByUserEmail(String userEmail) {
        if(!cartRepository.existsByuserEmail(userEmail)){
            throw new CartItemNotFoundException("SERVICE.Cart-Item-Delete_NOT_FOUND");
        }
        cartRepository.deleteByuserEmail(userEmail);
    }

    @Override
    public Cart deleteCartItemByMedicineId(String medId,String userEmail) {
       Cart cart=cartRepository.findByuserEmail(userEmail);
        List<Medicines> med= cart.getMedicines();
        if(med.size()==0){
            throw new CartItemNotFoundException("SERVICE.Cart-Item-Delete_NOT_FOUND");
        }
        for (int i=0;i<med.size();i++){
            if(med.get(i).getMedId().equals(medId)){
                med.remove(i);
            }
        }
        cart.setMedicines(med);
         Cart cartSave=cartRepository.save(cart);

        return cartSave;

    }




    @Override
    public Cart findByUserEmail(String userEmail) {
            log.info("Cart List, service; fetch cart by id");
            Optional<Cart> cartFromRepo = Optional.ofNullable(cartRepository.findByuserEmail(userEmail));
            if (cartFromRepo.isPresent()) {
                return cartFromRepo.get();
            } else {
                log.info("Cart List, service; fetch cart not found for given id");
                throw new CartItemNotFoundException("SERVICE.Cart-Item_NOT_FOUND");


            }

    }


    @Override
    public Cart updateCartItemByUserEmail(String  userEmail,Cart cart) {
        log.info("Cart List,service; update cart with cartId");
        Optional<Cart> cartFromDb = Optional.ofNullable(cartRepository.findByuserEmail(userEmail));
        Cart updateCart = null;
        if (cartFromDb.isPresent()) {
            Cart CartFromRepo = cartFromDb.get();
            CartFromRepo.setCartQuantity(cart.getCartQuantity());
            CartFromRepo.setDeliveryDate(cart.getDeliveryDate());
            Medicines med=new Medicines();
            med.setMedId(String.valueOf(cart.getMedicines().get(0).getMedId()));
            med.setQuantity(cart.getMedicines().get(0).getQuantity());
            med.setMedPrice(cart.getMedicines().get(0).getMedPrice());
            med.setMedTotalPrice(cart.getMedicines().get(0).getMedTotalPrice());

           List<Medicines> med1=CartFromRepo.getMedicines();

           Integer lin=med1.size();
           med1.add(med);
           CartFromRepo.setMedicines(med1);

            updateCart = cartRepository.save(CartFromRepo);
        }else{
            log.info("Cart List, service; update Cart");
            cart.setCartId(service.getSequenceNumber(Cart.SEQUENCE_NAME));
            cart.setUserEmail(userEmail);



             updateCart = cartRepository.save(cart);
        }

        return updateCart;
    }


}
