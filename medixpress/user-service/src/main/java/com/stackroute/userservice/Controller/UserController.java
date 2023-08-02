package com.stackroute.userservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.stackroute.userservice.DTO.ImageUserDTO;
import com.stackroute.userservice.DTO.googleAuthDTO;
import com.stackroute.userservice.model.Address;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.PhotoService;
import com.stackroute.userservice.service.UserServiceImpl;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@RestController
@RequestMapping(value="/api/v1/user/")
public class UserController {
   @Autowired
    UserServiceImpl userserviceimpl;

    @Autowired
    PhotoService photoService;

    @PostMapping("addUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userserviceimpl.saveUser(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("authByGoogle")
    public ResponseEntity<User> googleAuth(@RequestBody googleAuthDTO userDto) {

        User u = userserviceimpl.addByAuth(userDto);

        return new ResponseEntity<User>(u, HttpStatus.OK);

    }


    @PostMapping("/addUserImage")
    public ResponseEntity<Object> addShop(@RequestParam("user") String user,@RequestParam(value = "multipartFile" , required = false) MultipartFile multipartFile)
    {
        ResponseEntity<Object> responseEntity;

        System.out.println("Test7.....string format passed is="+user);

            Gson gson = new Gson();
            User courseFileObj = gson.fromJson(user, User.class);
            User shopfromservice= (User) photoService.addImage(courseFileObj,multipartFile);
            responseEntity = new ResponseEntity<Object>(shopfromservice, HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping("/addUserImageByMail")
    public ResponseEntity<Object> addImageByMailId(@RequestParam("email") String email,@RequestParam(value = "multipartFile" , required = false) MultipartFile multipartFile)
    {
        ResponseEntity<Object> responseEntity;
        User userFetch=userserviceimpl.getUserByEmail(email);

        System.out.println("Test5.....mail is="+email);

        ImageUserDTO user = new ImageUserDTO();

        user.setEmailId(userFetch.getEmailId());
        user.setName(userFetch.getName());
        user.setContactNo(userFetch.getContactNo());
        user.setPassword(userFetch.getPassword());
        user.setGender(userFetch.getGender());


        user.setUserRole(userFetch.getUserRole());


if(userFetch.getAddress()!=null){


        List<Address> address2= new ArrayList<>(userFetch.getAddress());

        user.setAddress(address2);

}
else{

    user.setAddress(null);
}


        ObjectMapper objMapper =new ObjectMapper();

        try {
            String objinstring = objMapper.writeValueAsString(user);
            System.out.println("Test6.....user body in string is="+objinstring);
            Gson gson = new Gson();
            User courseFileObj = gson.fromJson(objinstring, User.class);
            User shopfromservice= (User) photoService.addImage(courseFileObj,multipartFile);
            responseEntity = new ResponseEntity<Object>(shopfromservice, HttpStatus.OK);
            return responseEntity;
        }catch(JsonProcessingException e){
            e.printStackTrace();

        }

       // String objinstring=user.toString();

      //  System.out.println("Test6.....user body in string is="+objinstring);
return null;

    }


    @GetMapping("/photos/{id}")
    public Binary getPhoto(@PathVariable String id, Model model) {
        User user = userserviceimpl.getUserByEmail(id);

       // model.addAttribute("title", );
        model.addAttribute("image", Base64.getEncoder().encodeToString(user.getShopImage().getData()));
        return user.getShopImage();
    }


    @GetMapping(value="getByEmail/{emailId}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("emailId") String emailId)
    {
        User user=userserviceimpl.getUserByEmail(emailId);

        return new ResponseEntity<User>(user, HttpStatus.OK  );
    }

    @DeleteMapping(value="deleteUserByEmail/{emailId}")
    public ResponseEntity<User> deleteUserByEmail(@PathVariable String emailId){
        User user=userserviceimpl.deleteUserByEmail(emailId);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }




    @PutMapping(value = "updateUserByEmail/{emailId}")
    public ResponseEntity<Object> updateUser(@RequestBody User user,@PathVariable String emailId){
        userserviceimpl.updateUser(emailId,user);
        return new ResponseEntity<Object>(user,HttpStatus.OK);
    }

    @PutMapping(value = "updateAddressByEmail/{emailId}")
    public ResponseEntity<Object> updateAddress(@RequestBody User user,@PathVariable String emailId){

        System.out.println("received email is.................." +emailId);
        userserviceimpl.updateAddressUser(emailId, user);
        return new ResponseEntity<Object>(user,HttpStatus.OK);
    }


}











