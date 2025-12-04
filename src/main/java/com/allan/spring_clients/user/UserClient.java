package com.allan.spring_clients.user;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserClient {

    private final WebClient webClient;


    public UserClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<User> findAll(){
        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(User.class);
    }

    public Mono<User> findUser(Integer id){
        return webClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
    }
}
