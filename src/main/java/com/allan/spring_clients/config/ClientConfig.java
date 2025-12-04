package com.allan.spring_clients.config;

import com.allan.spring_clients.album.AlbumClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {
    // this bean is manually configured cos i also have spring mvc in the pom
    @Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    WebClient webClient(WebClient.Builder builder){
        return builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    @Bean
    RestClient restClient(RestClient.Builder builder){
        return builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    // old way of using http exchange
//    @Bean
//    AlbumClient albumClient(){
//        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
//        return factory.createClient(AlbumClient.class);
//    }
}
