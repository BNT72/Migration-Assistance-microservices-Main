package com.example.reactive.dto;


import com.example.reactive.model.test.TestQuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TestQuestionDto {

    private String question;

    private List<String> choices;

    private String answer;

    public TestQuestionDto(TestQuestionEntity entity) {
        this.question = entity.getQuestion();
        this.choices = entity.getChoices();
        this.answer = entity.getAnswer();
    }
}
