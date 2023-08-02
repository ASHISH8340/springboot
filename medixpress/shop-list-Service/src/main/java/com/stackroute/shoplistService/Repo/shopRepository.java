package com.stackroute.shoplistService.Repo;

import com.stackroute.shoplistService.model.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface shopRepository extends MongoRepository<Shop,String> {
    public Optional<Shop> findByshopId(String shopId);

    public List<Shop> findBypincode(String pincode);

   // public Shop findBycontactEmail(String email);
    public Boolean existsBypincode(String pincode);

    public Boolean existsBycontactEmail(String email);

//    @Query(fields="{ 'email' : 1, 'shopItems' : 1 }")
    public List<Shop> findBycontactEmail(String email);

}
