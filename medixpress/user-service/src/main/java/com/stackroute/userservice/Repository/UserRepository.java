package com.stackroute.userservice.Repository;


import com.stackroute.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByEmailId(String emailId);

    void deleteByEmailId(String emailId);

    boolean existsByEmailId(String emailId);
}
