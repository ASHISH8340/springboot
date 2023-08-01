package com.globallogic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GloQuoraPost {

	private long userId;
	private String postId;
	private String title;
	private String body;

}
