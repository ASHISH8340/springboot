package com.stackroute.orderService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String streetName	;
    private Long doorNumber;
    private String landmark;
    private String state	;
    private String city	;
    private String pinCode	;
}
