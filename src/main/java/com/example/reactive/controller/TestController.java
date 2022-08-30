package com.example.reactive.controller;

import com.example.reactive.dto.TestQuestionDto;
import com.example.reactive.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class TestController {


    private final TestService testService;


    @PostMapping("/")
    public Flux<TestQuestionDto> getTest(@RequestBody String testType) {
        return testService.getTest(testType);
    }
}
