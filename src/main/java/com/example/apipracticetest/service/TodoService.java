package com.example.apipracticetest.service;

import com.example.apipracticetest.entity.Todo;

import java.util.List;

public interface TodoService {

    void createTodo(Todo todo);

    List<Todo> readAllTodo();

    Todo readOneTodo(long id);

    void updateTodo(Todo todo);

    void deleteTodo(long id);

    boolean hasNext(int dataSize, int pageSize, int page);
}
