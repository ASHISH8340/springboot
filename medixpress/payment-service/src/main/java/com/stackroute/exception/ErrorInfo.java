package com.stackroute.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {
    private String errorCode;
    private String errorMessage;
    private LocalDateTime time;
}
