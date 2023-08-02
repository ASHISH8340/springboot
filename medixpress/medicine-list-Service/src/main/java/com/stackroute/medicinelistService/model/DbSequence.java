package com.stackroute.medicinelistService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document(collection="db_sequence")
public class DbSequence {
    @Id
    private String id;
    private String seq;
}
