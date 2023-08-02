package com.stackroute.shoplistService.Exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class shopIdNotFound extends RuntimeException{

    private String msg;

}
