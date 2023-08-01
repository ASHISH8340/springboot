package com.globallogic.springrestdemo.service;

import java.util.List;

import com.globallogic.springrestdemo.modal.Employee;

public interface EmployeeService {

	public Employee addEmployee(Employee employee);

	public List<Employee> getAllEmployee();

	public Employee getEmployeeById(Long employeeId);

	public Employee updateEmployeeById(Long employeeId, Employee employee);

	public void deleteEmployeeById(Long employeeId);

}
