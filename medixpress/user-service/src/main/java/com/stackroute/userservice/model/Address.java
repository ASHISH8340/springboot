package com.stackroute.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

        private String streetName	;
        private Long doorNumber;
        private String landmark;
        private String state	;
        private String city	;
        private String pinCode	;

    }


