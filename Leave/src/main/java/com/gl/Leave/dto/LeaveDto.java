package com.gl.Leave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveDto {

    private Long leave_id;
    private Long employeeId;
    private LocalDate applicationDate;
    private LocalDate leaveStartDate;
    private LocalDate leaveEndDate;
    private String leaveType;
    private String reason;

}
