package com.stackroute.userservice.model;


import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="User")
@Builder
public class User {

        @Id
        private String emailId;
        private String name;
        private String contactNo;
        private String password;
        private String gender ;
        private UserRole userRole;
        private Binary shopImage;
        private List<Address> address;

    }


