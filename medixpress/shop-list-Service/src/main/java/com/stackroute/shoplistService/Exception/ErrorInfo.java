package com.stackroute.shoplistService.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorInfo {
    private String errormsg;
    private String errorCode;
    private LocalDateTime dateTime;


}
