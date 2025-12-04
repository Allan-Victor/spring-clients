package com.allan.spring_clients.post;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class PostClient {
    private final RestClient restClient;

    public PostClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Post> findAll(){
        return  restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
