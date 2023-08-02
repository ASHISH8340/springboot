//package com.stackroute.userservice.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.atLeast;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.stackroute.userservice.Exception.userNotFound;
//import com.stackroute.userservice.Repository.UserRepository;
//import com.stackroute.userservice.config.Producer;
//import com.stackroute.userservice.config.UserDTO;
//import com.stackroute.userservice.model.Address;
//import com.stackroute.userservice.model.User;
//import com.stackroute.userservice.model.UserRole;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.bson.types.Binary;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ContextConfiguration(classes = {UserServiceImpl.class})
//@ExtendWith(SpringExtension.class)
//class UserServiceImplTest {
//    @MockBean
//    private Producer producer;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserServiceImpl userServiceImpl;
//
//
//    @Test
//    void testSaveUser() throws UnsupportedEncodingException {
//        doNothing().when(producer).sendMessageToRabbitMq((UserDTO) any());
//
//        User user = new User();
//        user.setAddress(new ArrayList<>());
//        user.setContactNo("Contact No");
//        user.setEmailId("42");
//        user.setGender("Gender");
//        user.setName("Name");
//        user.setPassword("iloveyou");
//        user.setShopImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        user.setUserRole(UserRole.SELLER);
//        when(userRepository.existsByEmailId((String) any())).thenReturn(true);
//        when(userRepository.save((User) any())).thenReturn(user);
//
//        User user1 = new User();
//        user1.setAddress(new ArrayList<>());
//        user1.setContactNo("Contact No");
//        user1.setEmailId("42");
//        user1.setGender("Gender");
//        user1.setName("Name");
//        user1.setPassword("iloveyou");
//        user1.setShopImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        user1.setUserRole(UserRole.SELLER);
//        assertThrows(userNotFound.class, () -> userServiceImpl.saveUser(user1));
//        verify(userRepository).existsByEmailId((String) any());
//    }
//
//
//
//    @Test
//    void testGetUserByEmail() throws UnsupportedEncodingException {
//        User user = new User();
//        user.setAddress(new ArrayList<>());
//        user.setContactNo("Contact No");
//        user.setEmailId("42");
//        user.setGender("Gender");
//        user.setName("Name");
//        user.setPassword("iloveyou");
//        user.setShopImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        user.setUserRole(UserRole.SELLER);
//        when(userRepository.findByEmailId((String) any())).thenReturn(user);
//        when(userRepository.existsByEmailId((String) any())).thenReturn(true);
//        assertSame(user, userServiceImpl.getUserByEmail("42"));
//        verify(userRepository).existsByEmailId((String) any());
//        verify(userRepository).findByEmailId((String) any());
//    }
//    @Test
//    void testDeleteUserByEmail() throws UnsupportedEncodingException {
//        User user = new User();
//        user.setAddress(new ArrayList<>());
//        user.setContactNo("Contact No");
//        user.setEmailId("42");
//        user.setGender("Gender");
//        user.setName("Name");
//        user.setPassword("iloveyou");
//        user.setShopImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        user.setUserRole(UserRole.SELLER);
//        when(userRepository.findByEmailId((String) any())).thenReturn(user);
//        doNothing().when(userRepository).deleteByEmailId((String) any());
//        when(userRepository.existsByEmailId((String) any())).thenReturn(true);
//        assertSame(user, userServiceImpl.deleteUserByEmail("42"));
//        verify(userRepository).existsByEmailId((String) any());
//        verify(userRepository).findByEmailId((String) any());
//        verify(userRepository).deleteByEmailId((String) any());
//    }
//
//
//    @Test
//    void testUpdateUser() throws UnsupportedEncodingException {
//        User user = new User();
//        user.setAddress(new ArrayList<>());
//        user.setContactNo("Contact No");
//        user.setEmailId("42");
//        user.setGender("Gender");
//        user.setName("Name");
//        user.setPassword("iloveyou");
//        user.setShopImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        user.setUserRole(UserRole.SELLER);
//
//        User user1 = new User();
//        user1.setAddress(new ArrayList<>());
//        user1.setContactNo("Contact No");
//        user1.setEmailId("42");
//        user1.setGender("Gender");
//        user1.setName("Name");
//        user1.setPassword("iloveyou");
//        user1.setShopImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        user1.setUserRole(UserRole.SELLER);
//        when(userRepository.save((User) any())).thenReturn(user1);
//        doNothing().when(userRepository).deleteByEmailId((String) any());
//        when(userRepository.findByEmailId((String) any())).thenReturn(user);
//        when(userRepository.existsByEmailId((String) any())).thenReturn(true);
//
//        User user2 = new User();
//        ArrayList<Address> addressList = new ArrayList<>();
//        user2.setAddress(addressList);
//        user2.setContactNo("Contact No");
//        user2.setEmailId("42");
//        user2.setGender("Gender");
//        user2.setName("Name");
//        user2.setPassword("iloveyou");
//        Binary binary = new Binary("AAAAAAAA".getBytes("UTF-8"));
//        user2.setShopImage(binary);
//        user2.setUserRole(UserRole.SELLER);
//        User actualUpdateUserResult = userServiceImpl.updateUser("42", user2);
//        assertSame(user2, actualUpdateUserResult);
//        assertEquals(addressList, actualUpdateUserResult.getAddress());
//        assertEquals(UserRole.SELLER, actualUpdateUserResult.getUserRole());
//        assertEquals(binary, actualUpdateUserResult.getShopImage());
//        assertEquals("iloveyou", actualUpdateUserResult.getPassword());
//        assertEquals("42", actualUpdateUserResult.getEmailId());
//        verify(userRepository).existsByEmailId((String) any());
//        verify(userRepository).findByEmailId((String) any());
//        verify(userRepository).save((User) any());
//        verify(userRepository).deleteByEmailId((String) any());
//    }
//
//
//    @Test
//    void testUpdateAddressUser() throws UnsupportedEncodingException {
//        User user = new User();
//        user.setAddress(new ArrayList<>());
//        user.setContactNo("Contact No");
//        user.setEmailId("42");
//        user.setGender("Gender");
//        user.setName("Name");
//        user.setPassword("iloveyou");
//        user.setShopImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        user.setUserRole(UserRole.SELLER);
//
//        User user1 = new User();
//        user1.setAddress(new ArrayList<>());
//        user1.setContactNo("Contact No");
//        user1.setEmailId("42");
//        user1.setGender("Gender");
//        user1.setName("Name");
//        user1.setPassword("iloveyou");
//        user1.setShopImage(new Binary("AAAAAAAA".getBytes("UTF-8")));
//        user1.setUserRole(UserRole.SELLER);
//        when(userRepository.save((User) any())).thenReturn(user1);
//        doNothing().when(userRepository).deleteByEmailId((String) any());
//        when(userRepository.findByEmailId((String) any())).thenReturn(user);
//        when(userRepository.existsByEmailId((String) any())).thenReturn(true);
//
//        User user2 = new User();
//        ArrayList<Address> addressList = new ArrayList<>();
//        user2.setAddress(addressList);
//        user2.setContactNo("Contact No");
//        user2.setEmailId("42");
//        user2.setGender("Gender");
//        user2.setName("Name");
//        user2.setPassword("iloveyou");
//        Binary binary = new Binary("AAAAAAAA".getBytes("UTF-8"));
//        user2.setShopImage(binary);
//        user2.setUserRole(UserRole.SELLER);
//        User actualUpdateAddressUserResult = userServiceImpl.updateAddressUser("42", user2);
//        assertEquals(addressList, actualUpdateAddressUserResult.getAddress());
//        assertEquals(UserRole.SELLER, actualUpdateAddressUserResult.getUserRole());
//        assertEquals(binary, actualUpdateAddressUserResult.getShopImage());
//        assertEquals("iloveyou", actualUpdateAddressUserResult.getPassword());
//        assertEquals("Contact No", actualUpdateAddressUserResult.getContactNo());
//        assertEquals("Gender", actualUpdateAddressUserResult.getGender());
//        assertEquals("Name", actualUpdateAddressUserResult.getName());
//        assertEquals("42", actualUpdateAddressUserResult.getEmailId());
//        verify(userRepository).existsByEmailId((String) any());
//        verify(userRepository).findByEmailId((String) any());
//        verify(userRepository).save((User) any());
//        verify(userRepository).deleteByEmailId((String) any());
//    }
//
//
//}
//
