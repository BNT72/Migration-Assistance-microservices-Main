package com.example.reactive.model.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class TestQuestionEntity {
    @Id
    private String id;

    private ETestType type;


    private String question;


    private List<String> choices;

    private String answer;

}
