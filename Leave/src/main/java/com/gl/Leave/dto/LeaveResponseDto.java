package com.gl.Leave.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LeaveResponseDto {

	private Long leave_Id;
	private Long employeeId;
	private LocalDate applicationDate;
	private LocalDate leaveStartDate;
	private LocalDate leaveEndDate;
	private String leaveType;
	private String reason;
	private EmployeeDto employeeDto;

}
