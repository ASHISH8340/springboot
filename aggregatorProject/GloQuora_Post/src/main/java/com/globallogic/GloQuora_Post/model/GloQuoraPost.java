package com.globallogic.GloQuora_Post.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "gloQuoraPost")
public class GloQuoraPost {
    @Id
    private String postId;

    @NotBlank
    private long userId;

    @NotBlank
    private String title;

    @NotBlank
    private String body;
}
