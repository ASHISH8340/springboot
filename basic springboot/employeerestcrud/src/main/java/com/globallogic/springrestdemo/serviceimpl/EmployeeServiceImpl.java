package com.globallogic.springrestdemo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.globallogic.springrestdemo.exception.IdNotFoundException;
import com.globallogic.springrestdemo.modal.Employee;
import com.globallogic.springrestdemo.repository.EmployeeRepository;
import com.globallogic.springrestdemo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	
	@Autowired
	Environment environment;

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
	public Employee getEmployeeById(Long employeeId)  {
		// TODO Auto-generated method stub
		Optional<Employee> employeeFromRepo = employeeRepository.findById(employeeId);
		if(employeeFromRepo.isPresent()) {
			return employeeFromRepo.get();
		}
		else {
			throw new IdNotFoundException("SERVICE.EMPLOYEE_NOT_FOUND");
		}
	}

	@Override
	public Employee updateEmployeeById(Long employeeId, Employee employee) {
		Optional<Employee> employeeFromDb = employeeRepository.findById(employeeId);
		Employee updatedEmployee = null;
		if (employeeFromDb.isPresent()) {
			Employee employeeromRepo = employeeFromDb.get();
			employeeromRepo.setDepartment(employee.getDepartment());
			employeeromRepo.setName(employee.getName());
			updatedEmployee = employeeRepository.save(employeeromRepo);
		}
		else {
			throw new IdNotFoundException("SERVICE.EMPLOYEE_NOT_FOUND");
		}
		return updatedEmployee;
	}

	@Override
	public void deleteEmployeeById(Long employeeId) {
		if(!employeeRepository.existsById(employeeId)) {
			throw new IdNotFoundException("SERVICE.EMPLOYEE_NOT_FOUND");
		}
		employeeRepository.deleteById(employeeId);
	}

}
