package com.globallogic.UserdetailsApp.service;

import com.globallogic.UserdetailsApp.Dto.UserDTO;
import com.globallogic.UserdetailsApp.exception.IdNotFoundException;
import com.globallogic.UserdetailsApp.model.User;
import com.globallogic.UserdetailsApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setUserId(userDTO.getUserId());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user.setGeo(userDTO.getGeo());
        user.setCompany(userDTO.getCompany());
        User savedUser =userRepository.save(user);
        userDTO.setUserId(savedUser.getUserId());
        return userDTO;

    }
    @Override
    public UserDTO getSpecificUserDetails(Long userId) {
        Optional<User> findById = userRepository.findById(userId);
        if(findById.isPresent()) {
            log.info("user is found to be returned");
            User userFromRepo = findById.get();
            UserDTO userDTO = new UserDTO();
            userDTO.setName(userFromRepo.getName());
            userDTO.setUsername(userFromRepo.getUsername());
            userDTO.setEmail(userFromRepo.getEmail());
            userDTO.setAddress(userFromRepo.getAddress());
            userDTO.setPhone(userFromRepo.getPhone());
            userDTO.setGeo(userFromRepo.getGeo());
            userDTO.setCompany(userFromRepo.getCompany());
            return userDTO;
        }else{
            log.error("user is not present to be returned");
            throw new IdNotFoundException("SERVICE.USERID_NOT_FOUND");
        }
    }

    @Override
    public List<UserDTO> getAllUserDetails() {
        List<UserDTO> responseList = new ArrayList<>();
        List<User> dataFromrepo = userRepository.findAll();
        for(User user :dataFromrepo) {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setUsername(user.getUsername());
            userDTO.setUserId(user.getUserId());
            userDTO.setPhone(user.getPhone());
            userDTO.setEmail(user.getEmail());
            userDTO.setAddress(user.getAddress());
            userDTO.setGeo(user.getGeo());
            userDTO.setCompany(user.getCompany());
            responseList.add(userDTO);
        }
        return responseList;
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {

        User updatedUser = new User();
        Optional<User> findById = userRepository.findById(userDTO.getUserId());
        if (findById.isPresent()) {
            User userToUpdate = findById.get();
            updatedUser.setName(userDTO.getName());
            updatedUser.setEmail(userDTO.getEmail());
            updatedUser.setPhone(userDTO.getPhone());
            updatedUser.setAddress(userToUpdate.getAddress());
            updatedUser.setGeo(userToUpdate.getGeo());
            updatedUser.setCompany(userToUpdate.getCompany());
            userRepository.save(updatedUser);


        }else{
            log.error("User not found to update");
            throw new IdNotFoundException("SERVICE.USERID_NOT_FOUND");
        }
        return userDTO;
    }

    @Override
    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)){
            log.error("user is not present to be deleted");
            throw new IdNotFoundException("SERVICE.USER_NOT_FOUND");
        }
        log.info("user is found to be deleted");
        userRepository.deleteById(userId);

    }


}
