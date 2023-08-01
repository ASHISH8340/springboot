package com.gl.Leave.service;

import com.gl.Leave.dto.EmployeeDto;
import com.gl.Leave.dto.LeaveDto;
import com.gl.Leave.model.Leave;

public interface LeaveService {
    Object addLeave(LeaveDto leaveDto);

    Object getLeaveByEmpId(Long employeeId);

    Object getEmpInfoByLeaveId(Long leave_id);
}
