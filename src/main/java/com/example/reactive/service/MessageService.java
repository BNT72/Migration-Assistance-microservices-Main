package com.example.reactive.service;

import com.example.reactive.model.Message;
import com.example.reactive.repo.MessageRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Flux<Message> list(){
        return messageRepo.findAll();
    }

    public Mono<Message> addOne(Message message){
        return messageRepo.save(message);
    }
}
