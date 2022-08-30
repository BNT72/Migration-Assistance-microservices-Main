package com.example.reactive.repo;

import com.example.reactive.model.user.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MessageRepo extends ReactiveCrudRepository<Message,Long> {
    Flux<Message> findAllByUserIdOrderByDateTime(Long user_id);
}
