package com.gl.Leave.controller;

import com.gl.Leave.dto.LeaveDto;
import com.gl.Leave.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/api")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;

    @PostMapping(value = "employee/leaves")
    public ResponseEntity<Object> addEmployee(@RequestBody LeaveDto leaveDto) {
        return new ResponseEntity<>(leaveService.addLeave(leaveDto), HttpStatus.CREATED);
    }

    @GetMapping("employee/leaves/{employeeId}")
    public ResponseEntity<Object> getLeaveByEmpId(@PathVariable Long employeeId) {
        return new ResponseEntity<Object>(leaveService.getLeaveByEmpId(employeeId), HttpStatus.OK);
    }

    @GetMapping("employee/leaves/details/{leave_id}")
    public ResponseEntity<Object> getEmpInfoByLeaveId(@PathVariable Long leave_id) {
        return new ResponseEntity<>(leaveService.getEmpInfoByLeaveId(leave_id), HttpStatus.OK);
    }
}
