//package com.stackroute.orderService.serviceimpl;
//
//import com.stackroute.orderService.exception.CartItemNotFoundException;
//import com.stackroute.orderService.model.Cart;
//import com.stackroute.orderService.model.Medicines;
//import com.stackroute.orderService.repository.CartRepository;
//
//import java.util.ArrayList;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.List;
//import java.util.Optional;
//
//
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.anyLong;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class CartServiceImplTest {
//
//
//
//
//    @Autowired
//    CartServiceImpl cartService;
//
//    @MockBean
//    CartRepository cartRepository;
//
//    @Test
//    void testFindById() {
//        Cart cart = new Cart();
//        cart.setCartId(1L);
//
//        when(cartRepository.findById(cart.getCartId())).thenReturn(Optional.of(cart));
//        Cart cartResult = cartService.findById(cart.getCartId());
//        assertEquals(cart, cartResult);
//
//    }
//
//
//    @Test
//    void testGetAllItemsFromCart() {
//        Cart cart1 = new Cart();
//        cart1.setCartId(2L);
//        cart1.setQuantity(1);
//        cart1.setTotalPrice(5444.99);
//
//        Cart cart2 = new Cart();
//        cart2.setCartId(3L);
//        cart2.setQuantity(2);
//        cart2.setTotalPrice(4444.39);
//
//        when(cartRepository.findAll()).thenReturn(List.of(cart1, cart2));
//        List<Cart> cartListResult = cartService.getAllItemFromCart();
//        assertEquals(2, cartListResult.size());
//        assertEquals(cart1, cartListResult.get(0));
//        assertEquals(cart2, cartListResult.get(1));
//
//    }
//
//    @Test
//    void testAddItemToCart() {
//        Cart cart = new Cart();
//        cart.setCartId(6L);
//        cart.setQuantity(5);
//        cart.setTotalPrice(500.0);
//        when(cartRepository.save(cart)).thenReturn(cart);
//        assertEquals(cart, cartService.addItemToCart(cart));
//    }
//
//
//
//
//
//    @Test
//    void testDeleteCartItem() {
//        assertThrows(CartItemNotFoundException.class, () -> {
//            Cart cart = new Cart();
//            cart.setCartId(1L);
//            cartService.deleteCartItem(1);
//            verify(cartRepository, times(1)).deleteById(cart.getCartId());
//        });
//
//
//    }
//
//
//    @Test
//    void testGetMedicinesItemsFromCart() {
//        ArrayList<Medicines> medicinesList = new ArrayList<>();
//        when(cartRepository.getCart(anyLong())).thenReturn(medicinesList);
//        when(cartRepository.existsById((Long) any())).thenReturn(true);
//        List<Medicines> actualMedicinesItemsFromCart = cartService.getMedicinesItemsFromCart(123L);
//        assertSame(medicinesList, actualMedicinesItemsFromCart);
//        assertTrue(actualMedicinesItemsFromCart.isEmpty());
//        verify(cartRepository).existsById((Long) any());
//        verify(cartRepository).getCart(anyLong());
//    }
//
//
//
//    @Test
//    void testUpdateCartItem() {
//        Cart cart = new Cart();
//        cart.setCartId(1L);
//        cart.setQuantity(1);
//        cart.setTotalPrice(200.53);
//
//        CartItemNotFoundException cartItemNotFoundException = assertThrows(CartItemNotFoundException.class,
//                () -> cartService.updateCartItem(1L, cart));
//        assertEquals("SERVICE.Cart-Item_NOT_FOUND", cartItemNotFoundException.getMessage());
//    }
//
//}
