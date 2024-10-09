package com.gustavolyra.twiter_copy.repository;

import com.gustavolyra.twiter_copy.entities.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;


public interface UserRepository extends ReactiveMongoRepository<User, String> {

    @Query("{ 'email': ?0 }")
    Mono<User> findByEmail(String email);
}
