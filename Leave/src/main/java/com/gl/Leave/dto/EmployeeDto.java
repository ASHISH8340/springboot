package com.gl.Leave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
}
