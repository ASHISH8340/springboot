package com.gl.Leave.serviceimpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.Leave.dto.EmployeeDto;
import com.gl.Leave.dto.LeaveDto;
import com.gl.Leave.dto.LeaveResponseDto;
import com.gl.Leave.exception.NotFoundException;
import com.gl.Leave.exception.Response;
import com.gl.Leave.feignProxy.FeignProxy;
import com.gl.Leave.model.Leave;
import com.gl.Leave.repository.LeaveRepository;
import com.gl.Leave.service.LeaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {
    Logger logger = LoggerFactory.getLogger(LeaveServiceImpl.class);

    @Autowired
    private FeignProxy feignProxy;

    @Autowired
    LeaveRepository leaveRepository;
    @Override
    public Object addLeave(LeaveDto leaveDto) {
        try {

            ResponseEntity<Object> response = feignProxy.getEmployeeById(leaveDto.getEmployeeId());


            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseBody = mapper.convertValue(response.getBody(), JsonNode.class);
            Long employeeId = responseBody.get("employeeId").asLong();
            if (employeeId < 0) {
                throw new NotFoundException("Enter a Valid employee id to apply leave");
            } else {
                Leave leave = new Leave();
                leave.setLeave_id(leaveDto.getLeave_id());
                leave.setEmployeeid(leaveDto.getEmployeeId());
                leave.setLeaveType(leaveDto.getLeaveType());
                leave.setLeaveStartDate(leaveDto.getLeaveStartDate());
                leave.setLeaveEndDate(leaveDto.getLeaveEndDate());
                leave.setReason(leaveDto.getReason());
                leave.setApplicationDate(leaveDto.getApplicationDate());
                Leave savedLeave =leaveRepository.save(leave);
                leaveDto.setLeave_id(savedLeave.getLeave_id());
                return new Response<>("Leave saved Successfully", "1", leaveDto);
            }
        } catch (Exception e) {
            String errorMsg = MessageFormat.format("Exception caught in addLeave of LeaveServiceImpl : {0}", e);
            logger.error(errorMsg);
            e.printStackTrace();
            throw new NotFoundException(e.getMessage());

        }

    }

    @Override
    public Object getLeaveByEmpId(Long employeeId) {
        logger.info("Inside getLeaveByEmpId of FeignController");
        try {
            Optional<Leave> leave = leaveRepository.findByEmployeeid(employeeId);
            if (leave.isEmpty()) {
                throw new NotFoundException("Leave Details Not Found For The Given employee id");
            } else {
                ResponseEntity<Object> response = feignProxy.getEmployeeById(employeeId);
                ObjectMapper mapper = new ObjectMapper();
                EmployeeDto employeeDto = mapper.convertValue(response.getBody(), EmployeeDto.class);
                LeaveResponseDto leaveResponseDto = new LeaveResponseDto();
                Leave leaves = leave.get();
                leaveResponseDto.setLeave_Id(leaves.getLeave_id());
                leaveResponseDto.setEmployeeId(employeeId);
                leaveResponseDto.setApplicationDate(leaves.getApplicationDate());
                leaveResponseDto.setLeaveStartDate(leaves.getLeaveStartDate());
                leaveResponseDto.setLeaveEndDate(leaves.getLeaveEndDate());
                leaveResponseDto.setLeaveType(leaves.getLeaveType());
                leaveResponseDto.setReason(leaves.getReason());
                leaveResponseDto.setEmployeeDto(employeeDto);
                return new Response<Object>(leaveResponseDto, "Post  fetched Successfully", "1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = MessageFormat.format("Exception caught in getLeaveByEmpId of LeaveServiceImpl :{0}", e);
            logger.error(errorMsg);
            throw new NotFoundException(e.getMessage());

        }
    }

    @Override
    public Object getEmpInfoByLeaveId(Long leave_id) {
        logger.info("Inside getEmpInfoByLeaveId of FeignProxy");
        try {
            Optional<Leave> findById = leaveRepository.findById(leave_id);
            if (findById.isEmpty()) {
                throw new NotFoundException("Enter a valid leave id");
            } else {
                Leave leave = findById.get();
                ResponseEntity<Object> employee = feignProxy.getEmployeeById(leave.getEmployeeid());
                ObjectMapper mapper = new ObjectMapper();
                EmployeeDto employeeDto = mapper.convertValue(employee.getBody(), EmployeeDto.class);

            return new Response<Object>(employeeDto, "Post  fetched Successfully", "1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = MessageFormat.format("Exception caught in getEmpInfoByLeaveId of LeaveServiceImpl :{0}", e);
            logger.error(errorMsg);
            throw new NotFoundException(e.getMessage());

        }
    }
}
