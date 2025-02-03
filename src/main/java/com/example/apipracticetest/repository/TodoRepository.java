package com.example.apipracticetest.repository;

import com.example.apipracticetest.entity.Todo;

import java.util.List;

public interface TodoRepository {

    void saveTodo(Todo todo);

    List<Todo> findAllTodo();

    Todo findByIdTodo(long id);

    void updateTodo(Todo todo);

    void deleteTodo(long id);
}
