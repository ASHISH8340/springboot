package com.gl.EmployeeManagementSystem.serviceimpl;

import com.gl.EmployeeManagementSystem.model.Employee;
import com.gl.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomEmployeeDetailService implements UserDetailsService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee emp=employeeRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with given email not found"));
        return emp;
    }
}
