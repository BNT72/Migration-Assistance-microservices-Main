package com.example.reactive.repo;

import com.example.reactive.model.Message;
import com.example.reactive.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;

public interface MessageRepo extends ReactiveCrudRepository<Message,Long> {
    List<Message> findAllByUserOrderByDateTime(User user);
}
