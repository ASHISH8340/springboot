package com.globallogic.employeecrud.service;

import com.globallogic.employeecrud.entity.Employee;
import com.globallogic.employeecrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee employee) {
        Optional<Employee> employeeFromDb = employeeRepository.findById(id);
        Employee updateEmployee = null;
        if (employeeFromDb.isPresent()) {
            Employee EmployeeFromRepo = employeeFromDb.get();
            EmployeeFromRepo.setName(employee.getName());
            EmployeeFromRepo.setDepartment(employee.getDepartment());

            updateEmployee = employeeRepository.save(EmployeeFromRepo);

        }
        return updateEmployee;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);

    }
}
