package com.stackroute.userservice.service;

import com.stackroute.userservice.DTO.googleAuthDTO;
import com.stackroute.userservice.Exception.userNotFound;
import com.stackroute.userservice.Repository.UserRepository;
import com.stackroute.userservice.config.Producer;
import com.stackroute.userservice.config.UserDTO;
import com.stackroute.userservice.model.Address;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.model.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository repository;

    @Autowired
    Producer producer;


    @Override
    public User saveUser(User user) {
        if (repository.existsByEmailId(user.getEmailId())){
            log.info("User already exist");
            throw new userNotFound("USER-ALREADY-EXIST");
        }

        System.out.println("saved user in mongo");

        UserDTO userdto=new UserDTO();
        userdto.setEmail(user.getEmailId());
            userdto.setPassword(user.getPassword());

        System.out.println("entered role is " +user.getUserRole());

        if(user.getUserRole().equals(UserRole.BUYER))
            userdto.setRole("BUYER");
        else if(user.getUserRole().equals(UserRole.SELLER))
        {
            System.out.println("entered SELLER");
            userdto.setRole("SELLER");
        }

        producer.sendMessageToRabbitMq(userdto);

        System.out.println("Helo...............role sis ="+userdto.getRole());

        User addedUser =repository.save(user);
        return addedUser;
    }

    @Override
    public User getUserByEmail(String emailId) {
        log.info("Inside the get by email");

        if (!repository.existsByEmailId(emailId)){
            log.info("Not found error");
            throw new userNotFound("USER-FETCH-DETAILS");
        }

        User user=repository.findByEmailId(emailId);


        return user;
    }


    @Override
    public User deleteUserByEmail(String emailId) {
        if (!repository.existsByEmailId(emailId) ){
            throw new userNotFound("USER-DELETE");
        }


        User user=repository.findByEmailId(emailId);
        repository.deleteByEmailId(emailId);
        return user;

    }

    @Override
    public User updateUser(String emailId, User user) {

        if (!repository.existsByEmailId(emailId) ){
            throw new userNotFound("USER-UPDATE");
        }
User userExisting= repository.findByEmailId(emailId);

        if(userExisting.getShopImage()!=null)
            user.setShopImage(userExisting.getShopImage());

        if(userExisting.getAddress()!=null)
            user.setAddress(userExisting.getAddress());

        if(userExisting.getUserRole()!=null)
            user.setUserRole(userExisting.getUserRole());

        if(userExisting.getPassword()!=null)
            user.setPassword(userExisting.getPassword());

        if(userExisting.getEmailId()!=null)
            user.setEmailId(userExisting.getEmailId());

        repository.deleteByEmailId(emailId);
        repository.save(user);
        return user;

    }

    @Override
    public User updateAddressUser(String emailId, User user) {

        if (!repository.existsByEmailId(emailId) ){
            throw new userNotFound("USER-UPDATE");
        }

        User existingUser = repository.findByEmailId(emailId);


        User newUser=new User();



        newUser.setEmailId(existingUser.getEmailId());
        newUser.setName(existingUser.getName());
        newUser.setPassword(existingUser.getPassword());
        newUser.setGender(existingUser.getGender());
        newUser.setUserRole(existingUser.getUserRole());
        newUser.setShopImage(existingUser.getShopImage());
        newUser.setContactNo(existingUser.getContactNo());

        List<Address> address= new ArrayList<>();


        //address.add((Address) user.getAddress());

        System.out.println("Received address is ............."+user.getAddress());

      //  address.add(new Address("street name is",100L ,"P.O","WB","Kolkata","700109"));

       // newUser.setAddress(address);



        List<Address> address2= new ArrayList<>(user.getAddress());

        newUser.setAddress(address2);
        System.out.println("List Received address is ............."+address2);


            
        
        repository.deleteByEmailId(emailId);
        repository.save(newUser);
        return newUser;

    }

@Override
    public User addByAuth(googleAuthDTO userDto) {


        String mail =userDto.getEmailId();

        if (!repository.existsByEmailId(mail) ){
            User user = new User();
            user.setEmailId(userDto.getEmailId());
            user.setName(userDto.getName());
            user.setUserRole(UserRole.BUYER);
            repository.save(user);
            return user;
        }



        else{
            User user=repository.findByEmailId(mail);
            return user;
        }
    }
}
