package com.globallogic.UserdetailsApp.controller;

import com.globallogic.UserdetailsApp.Dto.UserDTO;
import com.globallogic.UserdetailsApp.model.Address;
import com.globallogic.UserdetailsApp.model.Company;
import com.globallogic.UserdetailsApp.model.Geo;
import com.globallogic.UserdetailsApp.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserServiceImpl userServiceImpl;

    @InjectMocks
    UserController userController;

    @Test
    @DisplayName("Testing add userDetails")
    void testAddUser() {
        UserDTO userDto = new UserDTO();
        Address address = new Address();
        address.setAddressId(1l);
        address.setCity("mzn");
        Geo geo = new Geo();
        geo.setGeoId(1l);
        geo.setLat("30.48");
        geo.setLng("50.67");
        address.setStreet("1");
        userDto.setAddress(address);
        Company company = new Company();
        company.setCompanyId(1l);
        company.setLocation("India");
        company.setName("globallogic");
        userDto.setCompany(company);
        userDto.setEmail("a@gmail.com");
        userDto.setUsername("ak");
        userDto.setPhone("7474747444");
        userDto.setUserId(1l);
        ResponseEntity<UserDTO> response = new ResponseEntity<>(null, HttpStatus.OK);
        when(userServiceImpl.addUser(userDto)).thenReturn(response.getBody());
        ResponseEntity<UserDTO> responseFromController = userController.addUser(userDto);
        assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
    }

    @Test
    @DisplayName("Testing update userDetails")
    void testupdateUser() {
        UserDTO userDto = new UserDTO();
        Address address = new Address();
        address.setAddressId(1l);
        address.setCity("mzn");
        Geo geo = new Geo();
        geo.setGeoId(1l);
        geo.setLat("30.48");
        geo.setLng("50.67");
        address.setStreet("1");
        userDto.setAddress(address);
        Company company = new Company();
        company.setCompanyId(1l);
        company.setLocation("India");
        company.setName("globallogic");
        userDto.setCompany(company);
        userDto.setEmail("a@gmail.com");
        userDto.setUsername("ak");
        userDto.setPhone("7474747444");
        userDto.setUserId(1l);
        ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
        when(userServiceImpl.updateUser(userDto.getUserId(),userDto)).thenReturn((UserDTO) response.getBody());
        ResponseEntity<UserDTO> responseFromController = userController.updateUser(userDto, userDto.getUserId());
        assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
    }

    @Test
    @DisplayName("Testing get specific userDetails")
    void testgetUser() {
        ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
        when(userServiceImpl.getSpecificUserDetails(1l)).thenReturn((UserDTO) response.getBody());
        ResponseEntity<UserDTO> responseFromController = userController.getUser(1l);
        assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
    }

    @Test
    @DisplayName("Testing get all userDetails")
    void testgetAllUserDetails() {
        ResponseEntity<UserDTO> response = new ResponseEntity<>(null, HttpStatus.OK);
        when(userServiceImpl.getAllUserDetails()).thenReturn((List<UserDTO>) response.getBody());
        ResponseEntity<List<UserDTO>> responseFromController = userController.getAllUserDetails();
        assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
    }

    @Test
    @DisplayName("Testing delete userDetails")
    void testDeleteUser() {
        ResponseEntity<String> responseFromController = userController.deleteUser(1L);
        assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
    }
}
