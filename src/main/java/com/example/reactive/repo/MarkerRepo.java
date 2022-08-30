package com.example.reactive.repo;

import com.example.reactive.model.map.EMarkerType;
import com.example.reactive.model.map.MarkerEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MarkerRepo extends ReactiveMongoRepository<MarkerEntity, String> {

    Flux<MarkerEntity> findAllByMarkerType(EMarkerType type);

    Mono<MarkerEntity> findByAddressAndName(String name, String address);
}
