package com.gl.EmployeeManagementSystem.serviceimpl;

import com.gl.EmployeeManagementSystem.dto.EmployeeDto;
import com.gl.EmployeeManagementSystem.dto.RoleDto;
import com.gl.EmployeeManagementSystem.exception.IdNotFoundException;
import com.gl.EmployeeManagementSystem.exception.Response;
import com.gl.EmployeeManagementSystem.model.Employee;
import com.gl.EmployeeManagementSystem.model.Role;
import com.gl.EmployeeManagementSystem.repository.EmployeeRepository;
import com.gl.EmployeeManagementSystem.repository.RoleRepository;
import com.gl.EmployeeManagementSystem.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${normal.role.id}")
    private String normalRoleId;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Object addEmployee(EmployeeDto employeedto) {
        try {
            Employee employee = new Employee();
            employee.setFirstName(employeedto.getFirstName());
            employee.setLast_name(employeedto.getLast_name());
            employee.setEmail(employeedto.getEmail());
            employee.setPassword(passwordEncoder.encode(employeedto.getPassword()));
            employee.setDepartment(employeedto.getDepartment());
            employee.setPosition(employeedto.getPosition());
            employee.getRoles();

            //fetch role of normal and set it to employee
            Role role = roleRepository.findById(normalRoleId).get();
            employee.getRoles().add(role);

            Employee savedEmployee = employeeRepository.save(employee);
            employeedto.setEmployeeId(savedEmployee.getEmployeeId());

            // Map roles to RoleDto objects
            Set<RoleDto> roleDto = savedEmployee.getRoles()
                    .stream().map((element) -> modelMapper.map(element, RoleDto.class))
                    .collect(Collectors.toSet());
            employeedto.setRoles(roleDto);
            return new Response<>("Employee saved Successfully", "1", employeedto);
        } catch (Exception e) {
                String errorMsg = MessageFormat.format("Exception caught in addEmployee of EmployeeServiceImpl : {0}", e);
                logger.error(errorMsg);
                e.printStackTrace();
                throw new IdNotFoundException(e.getMessage());

            }
    }

    @Override
    public Object getAllEmployee() {
        try {
            logger.info("Inside getAllEmployee of EmployeeServiceImpl");
            List<Employee> employeeFromRepo = employeeRepository.findAll();
            if (employeeFromRepo.size() == 0) {
                throw new IdNotFoundException("No Data Found");

            }
            List<EmployeeDto> responseDataList = new ArrayList<>();
            for (Employee employeeDetails : employeeFromRepo) {
                EmployeeDto employeeDto = new EmployeeDto();
                employeeDto.setEmployeeId(employeeDetails.getEmployeeId());
                employeeDto.setFirstName(employeeDetails.getFirstName());
                employeeDto.setLast_name(employeeDetails.getLast_name());
                employeeDto.setEmail(employeeDetails.getEmail());
                employeeDto.setPassword(employeeDetails.getPassword());
                employeeDto.setDepartment(employeeDetails.getDepartment());
                employeeDto.setPosition(employeeDetails.getPosition());
                // Map roles to RoleDto objects
                Set<RoleDto> roleDtoSet = employeeDetails.getRoles().stream()
                        .map(role -> new RoleDto(role.getRoleId(), role.getRoleName()))
                        .collect(Collectors.toSet());
                employeeDto.setRoles(roleDtoSet);



                responseDataList.add(employeeDto);

            }


            return new Response<Object>("EmployeeDetails Fetched Successfully", "1", responseDataList);
        } catch (Exception e) {
            String errorMsg = MessageFormat
                    .format("Exception caught in getAllEmployee of EmployeeServiceImpl : {0}", e);
            logger.error(errorMsg);
            e.printStackTrace();
            throw new IdNotFoundException(e.getMessage());
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        try {
            logger.info("Inside getEmployeeById of EmployeeServiceImpl with employeeId :{}", employeeId);
            Optional<Employee> employeeFromRepo = employeeRepository.findById(employeeId);
            logger.info("Data from repo is :{}", employeeFromRepo);
            if (employeeFromRepo.isEmpty()) {
                throw new IdNotFoundException("EmployeeId Does Not Exist");

            }
            EmployeeDto employeeDto = new EmployeeDto();
            Employee employeeDetails = employeeFromRepo.get();
            employeeDto.setEmployeeId(employeeDetails.getEmployeeId());
            employeeDto.setFirstName(employeeDetails.getFirstName());
            employeeDto.setLast_name(employeeDetails.getLast_name());
            employeeDto.setEmail(employeeDetails.getEmail());
            employeeDto.setPassword(employeeDetails.getPassword());
            employeeDto.setDepartment(employeeDetails.getDepartment());
            employeeDto.setPosition(employeeDetails.getPosition());

            return new Response<EmployeeDto>("EmployeeDetails Fetched Successfully", "1", employeeDto).getData();
        } catch (Exception e) {
            String errorMsg = MessageFormat
                    .format("Exception caught in getEmployeeById of EmployeeServiceImpl : {0}", e);
            logger.error(errorMsg);
            e.printStackTrace();
            throw new IdNotFoundException(e.getMessage());
        }
    }

    @Override
    public Object updateEmployeeById(Long employeeId, EmployeeDto employeeDto) {
        try {
            logger.info("Inside updateEmployeeById of EmployeeServiceImpl");
            Optional<Employee> findById = employeeRepository.findById(employeeId);
            if (findById.isPresent()) {
                Employee employeeToUpdate = findById.get();
                employeeToUpdate.setEmail(employeeDto.getEmail());
                employeeToUpdate.setDepartment(employeeDto.getDepartment());
                employeeToUpdate.setPosition(employeeDto.getPosition());
                employeeRepository.save(employeeToUpdate);

                // Update the EmployeeDto object with the updated values
                employeeDto.setEmployeeId(employeeToUpdate.getEmployeeId());
                employeeDto.setFirstName(employeeToUpdate.getFirstName());
                employeeDto.setLast_name(employeeToUpdate.getLast_name());

                return new Response<Object>("Employee Updated", "1", employeeDto);
            }

            throw new IdNotFoundException("Employee not found");
        } catch (Exception e) {
            String errorMsg = MessageFormat.format("Exception caught in updateEmployeeById of EmployeeServiceImpl: {0}", e);
            logger.error(errorMsg);
            e.printStackTrace();
            throw new IdNotFoundException(e.getMessage());
        }
    }

    @Override
    public Object deleteEmployeeById(Long employeeId) {
        try {
            Optional<Employee> employees = employeeRepository.findById(employeeId);
            if (employees.isEmpty()
            ) {
                throw new IdNotFoundException("Employee does not exist");
            }
            Employee employee = employees.get();
            // Remove associations between employee and roles
            employee.getRoles().clear();

            // Save the employee to update the associations in the database
            employeeRepository.save(employee);
            employeeRepository.deleteById(employee.getEmployeeId());
            return new Response<Object>("Employee deleted successfully", "1",employee);

        } catch (Exception e) {
            String errorMsg = MessageFormat
                    .format("Exception caught in deleteEmployeeById of EmployeeServiceImpl : {0}", e);
            logger.error(errorMsg);
            e.printStackTrace();
            throw new IdNotFoundException(e.getMessage());
        }
    }

    @Override
    public Page<Employee> getAllEmployeeSortedByName(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Object makeEmployeeAdmin(Long employeeId) {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
            if (employeeOptional.isEmpty()) {
                throw new IdNotFoundException("Employee does not exist");
            }

            Employee employee = employeeOptional.get();

            // Create an admin role object
            Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN");
            if (adminRole == null) {
                throw new IdNotFoundException("Admin role not found");
            }

            // Update the employee's roles
            employee.getRoles().clear();
            employee.getRoles().add(adminRole);

            // Save the updated employee Role findByRoleName(String roleName);
            employeeRepository.save(employee);

            return new Response<>("Employee is now an admin", "1", employee);
        } catch (Exception e) {
            String errorMsg = MessageFormat.format("Exception caught in makeEmployeeAdmin of EmployeeServiceImpl: {0}", e);
            logger.error(errorMsg);
            e.printStackTrace();
            throw new IdNotFoundException(e.getMessage());
        }
    }


}
