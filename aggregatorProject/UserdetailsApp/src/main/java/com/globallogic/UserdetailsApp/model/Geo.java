package com.globallogic.UserdetailsApp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "geo")
@Entity
public class Geo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long geoId;
    @NotBlank
    private String lat;
    @NotBlank
    private String lng;


}
