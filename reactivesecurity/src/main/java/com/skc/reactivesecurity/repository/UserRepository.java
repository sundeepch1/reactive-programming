package com.skc.reactivesecurity.repository;

import com.skc.reactivesecurity.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    public Mono<User> findByUsername(String username);
}
