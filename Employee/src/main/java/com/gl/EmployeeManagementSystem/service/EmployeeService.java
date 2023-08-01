package com.gl.EmployeeManagementSystem.service;

import com.gl.EmployeeManagementSystem.dto.EmployeeDto;
import com.gl.EmployeeManagementSystem.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Object addEmployee(EmployeeDto employeedto);
    Object getAllEmployee();
    EmployeeDto getEmployeeById(Long employeeId);

    Object updateEmployeeById(Long employeeId, EmployeeDto employeeDto);

    Object deleteEmployeeById(Long employeeId);

    Page<Employee> getAllEmployeeSortedByName(int pageNumber, int pageSize);

    Object makeEmployeeAdmin(Long employeeId);



}
