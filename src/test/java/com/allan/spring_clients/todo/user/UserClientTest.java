package com.allan.spring_clients.todo.user;

import com.allan.spring_clients.user.UserClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserClientTest {

    @Mock
    WebClient webClient;



    @Autowired
    private UserClient userClient;

    @BeforeEach
    void setUp(){
        userClient = new UserClient(webClient);
    }

    @Test
    void itShouldFindAll() {
        StepVerifier.create(userClient.findAll())
                .expectNextCount(10)
                .verifyComplete();

    }
}