package com.stackroute.orderService.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo {
    private String errorCode;
    private String errorMessage;
    private LocalDateTime time;
}
