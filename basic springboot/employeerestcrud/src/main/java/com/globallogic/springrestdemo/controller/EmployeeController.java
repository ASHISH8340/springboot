package com.globallogic.springrestdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.springrestdemo.exception.IdNotFoundException;
import com.globallogic.springrestdemo.modal.Employee;
import com.globallogic.springrestdemo.service.EmployeeService;

@RestController
@RequestMapping(value = "/v1/api")

public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping(value = "employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.addEmployee(employee), HttpStatus.OK);
	}

	@GetMapping(value = "employees")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployee(), HttpStatus.OK);
	}

	@GetMapping(value = "employee/{employeeId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Long employeeId) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}

	@PutMapping(value = "employee/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Long employeeId) {
		return new ResponseEntity<Employee>(employeeService.updateEmployeeById(employeeId, employee), HttpStatus.OK);
	}

	@DeleteMapping(value = "employee/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
		employeeService.deleteEmployeeById(employeeId);
		return new ResponseEntity<String>("Employee deleted successfully", HttpStatus.OK);
	}
	
	
}










