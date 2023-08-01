package com.gl.EmployeeManagementSystem.dto;


import com.gl.EmployeeManagementSystem.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto  {

    private Long employeeId;
    private String firstName;
    private String last_name;
    private String email;
    private String password;
    private String department;
    private String position;
    private Set<RoleDto> roles = new HashSet<>();
}
