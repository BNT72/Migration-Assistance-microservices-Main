package com.example.reactive.service;

import com.example.reactive.dto.TestQuestionDto;
import com.example.reactive.model.test.ETestType;
import com.example.reactive.repo.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Optional;

@AllArgsConstructor
@Component
public class TestService {
    private final QuestionRepository questionRepository;

    public Flux<TestQuestionDto> getTest(String testType) {
        Optional<ETestType> type = Optional.of(ETestType.valueOf(testType.substring(0, testType.length() - 1)));
        return questionRepository
                .findAllByType(type.orElseThrow(IllegalArgumentException::new))
                .map(TestQuestionDto::new);
    }
}
