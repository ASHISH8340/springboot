package com.globallogic.employeecrud.service;

import com.globallogic.employeecrud.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee addEmployee(Employee employee);

    public List<Employee> getAllEmployee();

    public Employee getEmployeeById(Long id);

    public Employee updateEmployeeById(Long id, Employee employee);

    public void deleteEmployeeById(Long id);
}
