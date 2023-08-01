package com.globallogic.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
	private Long userId;
	private String name;
	private String username;
	private String email;
	private Long phone;
	private Address address;
	private Geo geo;
	private Company company;
	private List<GloQuoraPost> quorapost;
}
