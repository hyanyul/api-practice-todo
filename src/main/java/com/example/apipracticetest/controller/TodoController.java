package com.example.apipracticetest.controller;

import com.example.apipracticetest.entity.Todo;
import com.example.apipracticetest.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Object> createTodo(@RequestBody Todo todo) {
        todoService.createTodo(todo);

        // 응답 데이터
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("statusCode", 201);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Todo> readAllTodo() {
        return todoService.readAllTodo();
    }

    @GetMapping("/{id}")
    public Todo readOneTodo(@PathVariable long id) {
        return todoService.readOneTodo(id);
    }

    @PutMapping("/{id}")
    public void updateTodo(@PathVariable long id, @RequestBody Todo updateTodo) {
        todoService.updateTodo(updateTodo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable long id) {
        todoService.deleteTodo(id);
    }
}
