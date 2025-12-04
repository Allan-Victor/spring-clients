package com.allan.spring_clients.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoClientTest {
    @Autowired
    private TodoClient underTest;

    @Test
    void itShouldFindAll() {
        // Given
        // When
        List<Todo> all = underTest.findAll();
        // Then
        assertEquals(200,all.size());

    }
}