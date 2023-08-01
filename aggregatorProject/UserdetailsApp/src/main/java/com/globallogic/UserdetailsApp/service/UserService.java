package com.globallogic.UserdetailsApp.service;

import com.globallogic.UserdetailsApp.Dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);

    UserDTO getSpecificUserDetails(Long userId);

     List<UserDTO> getAllUserDetails();
     UserDTO updateUser(Long userId, UserDTO userDTO);


//     private User convertToEntity(UserDto userDto);
         void deleteUser(Long userId);

}
