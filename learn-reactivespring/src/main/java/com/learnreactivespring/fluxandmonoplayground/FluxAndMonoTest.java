package com.learnreactivespring.fluxandmonoplayground;


import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
    public void fluxTest(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .concatWith(Flux.just("After Error"))
                .log();
        stringFlux.subscribe(System.out::println, e -> System.out.println(e)
        , () -> System.out.println("Completed"));
    }

    @Test
    public void fluxTestElementWithoutError(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                .verifyComplete();
    }

    @Test
    public void fluxTestElementWithError(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void fluxTestElementCountWithError(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void fluxTestElementWithError1(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring","Spring Boot","Reactive Spring")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void testMono(){
        Mono<String> stringMono = Mono.just("Spring");

        StepVerifier.create(stringMono.log())
                .expectNext("Spring")
                .verifyComplete();
    }

    @Test
    public void testMono_Error(){

        StepVerifier.create(Mono.error(new RuntimeException("Exception Occurred")).log())
                .expectError(RuntimeException.class)
                .verify();
    }
}
