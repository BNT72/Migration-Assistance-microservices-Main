package com.example.reactive.service;

import com.example.reactive.dto.Messages;
import com.example.reactive.model.user.Message;
import com.example.reactive.repo.MessageRepo;
import com.example.reactive.repo.UserRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Service
public class MessageService {

    private final MessageRepo messageRepo;
    private final UserRepo userRepository;

    public MessageService(MessageRepo messageRepo, UserRepo userRepository) {
        this.messageRepo = messageRepo;
        this.userRepository = userRepository;
    }

    public Flux<Message> getDialog(String userName) {
        return userRepository.findByUsername(userName)
                .flatMapMany(user -> messageRepo.findAllByUserIdOrderByDateTime(user.getId()));
    }

    public Flux<Message> postMessage(String userName, Message messageInfo) {
        return userRepository.findByUsername(userName).doOnNext(user -> {})
                .flatMapMany(user -> {
                    messageInfo.setDateTime(LocalDateTime.now());
                    messageInfo.setUserId(user.getId());
                    messageRepo.save(messageInfo).subscribe();
                    return messageRepo.findAllByUserIdOrderByDateTime(user.getId());
        });
    }

    public Flux<Messages> getAllDialogs() {
        return userRepository.findAllByMessagesIsNotNull()
                .flatMap(user ->
                        messageRepo
                                .findAllByUserIdOrderByDateTime(user.getId())
                                .collectList()
                                .flatMap(messages -> Mono.just(new Messages(user, messages)))
        );
    }

}
