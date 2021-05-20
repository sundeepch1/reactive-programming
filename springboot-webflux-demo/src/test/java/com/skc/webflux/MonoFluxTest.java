package com.skc.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono(){
        Mono<?> stringMono = Mono.just("Spring")
                .then(Mono.error(new RuntimeException("Exception Occurred")))
                .log();
        stringMono.subscribe(System.out::println
        , (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Hibernate", "Microservice")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .concatWithValues("Cloud")
                .log();

        stringFlux.subscribe(System.out::println, (e)->System.out.println(e.getMessage()));
    }
}
