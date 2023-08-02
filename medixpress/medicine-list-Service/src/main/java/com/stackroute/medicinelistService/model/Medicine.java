package com.stackroute.medicinelistService.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

//line 1

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection= "Medicine")
public class Medicine implements Serializable {
    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";
    @ApiModelProperty(notes = "id of the medicine", name = "id", dataType = "String")
    @Id
    private String medicineId;
    private String shopName;
    private String shopEmail;
    private String medicineName;
    private Binary Image;
    private String medicineCatagory;
    private double medicinePrice;
    private double medicineDiscountprice;
    private String medicineDescription;
    private String manafacturedate;
    private String expireydate;
    private int    medicineUnits;
    private double discountPercentage;
}