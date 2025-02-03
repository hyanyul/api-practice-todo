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
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Map<String, Object>> readAllTodo(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {

        List<Todo> result = todoService.readAllTodo();
        boolean hasNext = todoService.hasNext(result.size(), size, page);
        Map<String, Object> paging = new HashMap<>();
        paging.put("page", page);
        paging.put("size", size);
        paging.put("offset", (page - 1) * size);
        paging.put("hasNext", hasNext);

        Map<String, Object> response = new HashMap<>();
        response.put("result", result);
        response.put("status", "success");
        response.put("statusCode", 200);
        response.put("paging", paging);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> readOneTodo(@PathVariable long id) {

        Todo result = todoService.readOneTodo(id);

        Map<String, Object> response = new HashMap<>();
        response.put("result", result);
        response.put("status", "success");
        response.put("statusCode", 200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTodo(@PathVariable long id, @RequestBody Todo updateTodo) {
        updateTodo.setId(id);
        todoService.updateTodo(updateTodo);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("statusCode", 200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteTodo(@PathVariable long id) {
        todoService.deleteTodo(id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("statusCode", 200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
