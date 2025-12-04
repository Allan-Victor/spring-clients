package com.allan.spring_clients.album;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;


import java.util.List;

@HttpExchange(url = "https://jsonplaceholder.typicode.com")
public interface AlbumClient {

    @GetExchange("/albums")
    List<Album> findAll();
}
