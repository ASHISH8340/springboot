package com.stackroute.userservice.service;

import com.stackroute.userservice.Repository.UserRepository;
import com.stackroute.userservice.model.User;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoService {

    @Autowired
    private UserRepository photoRepo;

    public Object addImage(User user, MultipartFile multipartFile) {

        try {
            //log.info("In Service Class for Add Method for adding Image");
//            seeker.setResume(new Binary(BsonBinarySubType.BINARY, seekerDto.getResume().getBytes()));
            user.setShopImage(new Binary(BsonBinarySubType.BINARY,multipartFile.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User savedUser=photoRepo.save(user);
        return savedUser;
    }
    /*

    public String addPhoto(String title, MultipartFile file) throws IOException {
        User photo = new User(title);
        photo.setImage(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepo.insert(photo); return photo.getId();
    }

    public Photo getPhoto(String id) {
        return photoRepo.findById(id).get();
    }*/
}