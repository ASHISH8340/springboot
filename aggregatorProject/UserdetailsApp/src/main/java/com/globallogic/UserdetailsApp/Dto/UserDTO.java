package com.globallogic.UserdetailsApp.Dto;

import com.globallogic.UserdetailsApp.model.Address;
import com.globallogic.UserdetailsApp.model.Company;
import com.globallogic.UserdetailsApp.model.Geo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String name;
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    private Address address;
    @NotBlank
    private String phone;

    private Geo geo;

    private Company company;


}
