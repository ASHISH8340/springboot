package com.stackroute.orderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Medicine_List")
public class Medicines {
    private String medId;
    private int quantity;
    private double medPrice;
    private double medTotalPrice;

}
