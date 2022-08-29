package com.example.reactive.handler;


import com.example.reactive.model.Message;
import com.example.reactive.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;


@Component
public class GreetingHandler {

    private final MessageService messageService;

    public GreetingHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
        Flux<Message> heelo = messageService.list();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(heelo, Message.class);
    }

    public Mono<ServerResponse> start(ServerRequest request) {
        String s = request.queryParam("param").orElse("Default");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("Hello" + s));
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Message> messageMono = request.bodyToMono(Message.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(messageMono.flatMap(messageService::addOne), Message.class));
    }

}