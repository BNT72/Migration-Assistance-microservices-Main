package com.example.reactive.repo;

import com.example.reactive.model.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepo extends ReactiveCrudRepository<Message,Long> {

}
