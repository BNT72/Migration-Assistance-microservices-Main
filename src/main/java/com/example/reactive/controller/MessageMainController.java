package com.example.reactive.controller;

import com.example.reactive.dto.Messages;
import com.example.reactive.model.user.Message;
import com.example.reactive.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/controller")
public class MessageMainController {
    private final MessageService messageService;

    @Autowired
    public MessageMainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{userName}")
    public Flux<Message> getDialog(@PathVariable String userName) {
        return messageService.getDialog(userName);
    }

    @PostMapping("/{userName}")
    public Flux<Message> postMessage(@PathVariable String userName, @RequestBody Message messageInfo) {
        return messageService.postMessage(userName, messageInfo);
    }

    @GetMapping("/")
    public Flux<Messages> getAllDialogs() {
        return messageService.getAllDialogs();
    }
}