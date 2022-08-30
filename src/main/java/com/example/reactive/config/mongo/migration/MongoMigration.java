package com.example.reactive.config.mongo.migration;

import com.example.reactive.model.map.MarkerEntity;
import com.example.reactive.model.test.TestQuestionEntity;
import com.example.reactive.repo.MarkerRepo;
import com.example.reactive.repo.QuestionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class MongoMigration implements CommandLineRunner {

    private final QuestionRepository questionRepository;
    private final MarkerRepo markerRepo;

    public MongoMigration(QuestionRepository questionRepository, MarkerRepo markerRepo) {
        this.questionRepository = questionRepository;
        this.markerRepo = markerRepo;
    }


    @Override
    public void run(String... args) {
        ObjectMapper objectMapper = new ObjectMapper();
        testMigration(objectMapper);
        mapMigration(objectMapper);
    }

    private void testMigration(ObjectMapper objectMapper) {
        List<TestQuestionEntity> testQuestionEntities;
        try {
            testQuestionEntities = objectMapper.readValue(new File("src/main/resources/mongo/migration/MigrationTest.json"), new TypeReference<>() {
            });

            Flux.fromIterable(testQuestionEntities).flatMap(testQuestionEntity ->
                            questionRepository.findByQuestion(testQuestionEntity.getQuestion())
                                    .map(testQuestionEntity1 -> testQuestionEntity1).switchIfEmpty(questionRepository.save(testQuestionEntity))
                    )
                    .subscribe();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void mapMigration(ObjectMapper objectMapper) {
        List<MarkerEntity> mapEntities;
        try {
            mapEntities = objectMapper.readValue(new File("src/main/resources/mongo/migration/MigrationMap.json"), new TypeReference<>() {
            });
            Flux.fromIterable(mapEntities).flatMap(testQuestionEntity ->
                            markerRepo.findByAddressAndName(testQuestionEntity.getAddress(), testQuestionEntity.getName())
                                    .map(testQuestionEntity1 -> testQuestionEntity1).switchIfEmpty(markerRepo.save(testQuestionEntity)))
                    .subscribe();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
