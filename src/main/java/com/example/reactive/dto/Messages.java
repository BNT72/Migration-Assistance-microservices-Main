package com.example.reactive.dto;

import com.example.reactive.model.user.Message;
import com.example.reactive.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    User user;
    List<Message> messages;
}
