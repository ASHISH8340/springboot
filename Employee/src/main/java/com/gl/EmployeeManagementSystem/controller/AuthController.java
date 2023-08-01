package com.gl.EmployeeManagementSystem.controller;

import com.gl.EmployeeManagementSystem.dto.EmployeeDto;
import com.gl.EmployeeManagementSystem.dto.JwtRequest;
import com.gl.EmployeeManagementSystem.dto.JwtResponse;
import com.gl.EmployeeManagementSystem.exception.IdNotFoundException;
import com.gl.EmployeeManagementSystem.security.JwtHelper;
import com.gl.EmployeeManagementSystem.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtHelper helper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
        this.doAuthenticate(request.getEmail(),request.getPassword());

        //token generate
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        //token store

        EmployeeDto employeeDto = modelMapper.map(userDetails,EmployeeDto.class);

        JwtResponse response = JwtResponse.builder().jwtToken(token).employee(employeeDto).build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,password);
        try {
            manager.authenticate(authentication);
        }catch (BadCredentialsException e){
            throw new IdNotFoundException("Invalid USERNAME and PASSWORD !!");
        }
    }


    @GetMapping("/current")
    public ResponseEntity<EmployeeDto> getCurrentUser(Principal principal) {
        String name = principal.getName();
        EmployeeDto employeeDto = modelMapper.map(userDetailsService.loadUserByUsername(name), EmployeeDto.class);
        return ResponseEntity.ok(employeeDto);
    }




}
