package com.allan.spring_clients.album;

import com.allan.spring_clients.post.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlbumClientTest {
    @Autowired
    AlbumClient underTest;

    @Test
    void itShouldFindAll() {
        List<Album> posts = underTest.findAll();
        assertEquals(100, posts.size());

    }
}