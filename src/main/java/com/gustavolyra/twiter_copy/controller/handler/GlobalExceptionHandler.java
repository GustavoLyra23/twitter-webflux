package com.gustavolyra.twiter_copy.controller.handler;

import com.gustavolyra.twiter_copy.dto.error.StandardError;
import com.gustavolyra.twiter_copy.exceptions.DatabaseConflitException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public Mono<ResponseEntity<StandardError>> userNameNotFoundExceptionHandler(UsernameNotFoundException ex, ServerRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        var error = new StandardError(Instant.now(), status.value(), ex.getMessage(), request.path());
        return Mono.just(ResponseEntity.status(status).body(error));
    }

    @ExceptionHandler(DatabaseConflitException.class)
    public Mono<ResponseEntity<StandardError>> userNameNotFoundExceptionHandler(DatabaseConflitException ex, ServerRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        var error = new StandardError(Instant.now(), status.value(), ex.getMessage(), request.path());
        return Mono.just(ResponseEntity.status(status).body(error));
    }


}
