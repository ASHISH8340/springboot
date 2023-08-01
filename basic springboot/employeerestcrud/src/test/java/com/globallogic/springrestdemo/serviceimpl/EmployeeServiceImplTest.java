package com.globallogic.springrestdemo.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.globallogic.springrestdemo.modal.Employee;
import com.globallogic.springrestdemo.repository.EmployeeRepository;


@SpringBootTest
class EmployeeServiceImplTest {

	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@MockBean 
	private EmployeeRepository employeeRepository;
	
//	@org.junit.Before
//	void setup() {
//		employee=new Employee();
//		employee.setEmployeeId(Employee_ID);
//		employeeList=new ArrayList<>();
//		employeeList.add(employee);
//	}
//		
//		Optional<Employee> employee=Optional.of(new Employee(1L, "ashish","hr"));
//		Mockito.when(employeeRepository.findById(1L)).thenReturn(employee);
//	}
	
	
	
	@Test
	 void testGetEmployeeBy_Id() {
	
		Employee employee=new Employee();
		employee.setEmployeeId(1L);
		when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		Employee employeeResult=employeeService.getEmployeeById(employee.getEmployeeId());
		assertEquals(employee, employeeResult);
	//	Mockito.verifyNoMoreInteractions(employeeRepository);
		
	
//		 String name="ashish";
		//Employee employee=new Employee();
		//employee.setDepartment("Developer");
//		Employee employeeById = employeeService.getEmployeeById(1L);
//		assertEquals(name, employeeById.getName());
		
		
	}
	
	@org.junit.Test
	
	 void testGetAllEmployee() {
		 Employee employee1=new Employee();
		 employee1.setEmployeeId(1L);
		 employee1.setName("Ashish");
		 employee1.setDepartment("hr");
		 
		 Employee employee2=new Employee();
		 employee2.setEmployeeId(2L);
		 employee2.setName("Manish");
		 employee2.setDepartment("Developer");
		 when(employeeRepository.findAll()).thenReturn(List.of(employee1,employee2));
		 List<Employee>employeeListResult=employeeService.getAllEmployee();
		 
		 assertEquals(2, employeeListResult.size());
		 assertEquals(employee1, employeeListResult.get(0));
		 assertEquals(employee2, employeeListResult.get(1));
		 
		 
		 
	 }
	
	
	

}
