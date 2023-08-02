package com.stackroute.orderService.repository;

import com.stackroute.orderService.model.Cart;
import com.stackroute.orderService.model.Medicines;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface CartRepository extends MongoRepository<Cart, Long>{
    //@Query(value="{'cartId':{$gte:?}}")
    @Query(value="{'cartId':?0}")
    List<Medicines> getCart(long cartId);


    @Query(value="{'userEmail':?0}")
    List<Medicines> getCartByUserEmail(String userEmail);

    //@Query(value="{'userEmail':?0}")
    Boolean existsByuserEmail(String userEmail);
    @Query(value="{'userEmail':?0}")
    Cart findByuserEmail(String userEmail);
   // @Query(value="{'userEmail':?0}")
    void deleteByuserEmail(String userEmail);
}
