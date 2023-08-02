package com.stackroute.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderEmail {
    private String emailId;
    private String orderId;
    private String firstName;
    private List<Medicine> medicineList;
    private double orderValue;
}
