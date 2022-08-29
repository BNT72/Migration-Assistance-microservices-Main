package com.example.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    private Long id;
    private String data;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}


