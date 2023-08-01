package com.gl.Leave.feignProxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "Employee", url = "http://localhost:8089")
//@FeignClient(name = "Employee")
public interface FeignProxy {
    @GetMapping("/v1/api/employee/{employeeId}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long employeeId);




}
