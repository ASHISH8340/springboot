package com.stackroute.shoplistService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "Shop")
@NoArgsConstructor
@AllArgsConstructor
public class Shop implements Serializable {
    private static final long serialVersionUID = 1L;
    @Transient
    public static final String SEQUENCE_NAME="user_sequence";

    @Id
    private String shopId;
    private Boolean shopVerification;
    private String shopName;
    private List<String> shopItems;
    private String shopOwnerName;
    private String contactEmail;
    private String shopEmail;
    private String description;
    private String contactNumber;
    private Binary shopImage;
    private String ShopOpeningTime;
    private String ShopCloseTime;
    private String streetName;
    private Long shopNumber;
    private String landmark;
    private String city;
    private String state;
    private String pincode;


}
