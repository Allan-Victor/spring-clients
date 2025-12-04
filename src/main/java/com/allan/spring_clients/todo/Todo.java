package com.allan.spring_clients.todo;


public record Todo(Integer userId,
                   Integer id,
                   String title,
                   Boolean completed) {
}
