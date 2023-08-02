package com.stackroute.orderService.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailsNotMatchingException extends RuntimeException{
    private String msg;
}
