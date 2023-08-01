package com.gl.EmployeeManagementSystem.controller;

import com.gl.EmployeeManagementSystem.dto.EmployeeDto;
import com.gl.EmployeeManagementSystem.exception.IdNotFoundException;
import com.gl.EmployeeManagementSystem.model.Employee;
import com.gl.EmployeeManagementSystem.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/api")
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @PostMapping(value="employee")
    public ResponseEntity<Object> addEmployee(@RequestBody EmployeeDto employeeDto) {
        logger.info("Request for addEmployee :{}", employeeDto);
        return new ResponseEntity<Object>(employeeService.addEmployee(employeeDto), HttpStatus.OK);
    }

    @GetMapping(value="employees")
    public ResponseEntity<Object> getAllEmployee() {
        logger.info("Request for getAllEmployee");
        return new ResponseEntity<Object>(employeeService.getAllEmployee(),HttpStatus.OK);
    }

    @GetMapping(value="employee/{employeeId}")
    public ResponseEntity<EmployeeDto>getEmployeeById(@PathVariable Long employeeId) {
        if (employeeId == null) {
            throw new IdNotFoundException("Kindly provide employeeId");
        }
        logger.info("Request for employeeById employeeId :{}", employeeId);
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);

    }


    @PutMapping(value = "employee/{employeeId}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long employeeId,@RequestBody EmployeeDto employeeDto) {
        if (employeeId == null) {
            throw new IdNotFoundException("Kindly provide employeeId");
        }
        logger.info("updating employee and passing ok status");
        return new ResponseEntity<Object>(employeeService.updateEmployeeById(employeeId, employeeDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "employee/{employeeId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long employeeId) {
        if (employeeId == null) {
            throw new IdNotFoundException("Kindly provide employeeId");
        }
        logger.info("Request for deleteEmployee :{}", employeeId);
        return new ResponseEntity<Object>(employeeService.deleteEmployeeById(employeeId), HttpStatus.GONE);
    }


    @GetMapping("/employee")
    public List<Employee> getAllEmployeeSortedByFirstName(@RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<Employee> page = employeeService.getAllEmployeeSortedByName(pageNumber, pageSize);
        List<Employee> employees = page.getContent();
        return employees;
    }


//    @PostMapping("/{employeeId}/makeAdmin")
    @PostMapping("/makeAdmin/{employeeId}")
    public ResponseEntity<Object> makeEmployeeAdmin(@PathVariable Long employeeId) {
        Object employee = employeeService.makeEmployeeAdmin(employeeId);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }





}
