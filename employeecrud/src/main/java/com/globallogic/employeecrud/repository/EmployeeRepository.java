package com.globallogic.employeecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globallogic.employeecrud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
