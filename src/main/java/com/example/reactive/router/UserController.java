package com.example.reactive.router;

import com.example.reactive.config.JwtUtil;
import com.example.reactive.model.User;
import com.example.reactive.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity> login(ServerWebExchange exchange) {
        return exchange.getFormData()
                .flatMap(stringStringMultiValueMap -> userService.
                        findByUsername(stringStringMultiValueMap.getFirst("username"))
                        .cast(User.class)
                        .map(user -> Objects.equals(
                                        stringStringMultiValueMap.getFirst("password"),
                                        user.getPassword()
                                )
                                        ? ResponseEntity.ok(jwtUtil.generateToken(user))
                                        : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
                        ).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build())
                );

    }
}
