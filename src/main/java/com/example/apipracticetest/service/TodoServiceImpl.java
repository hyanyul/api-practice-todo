package com.example.apipracticetest.service;

import com.example.apipracticetest.entity.Todo;
import com.example.apipracticetest.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    // 생성자 주입
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void createTodo(Todo todo) {
        todoRepository.saveTodo(todo);
    }

    @Override
    public List<Todo> readAllTodo() {

        return todoRepository.findAllTodo();
    }

    @Override
    public Todo readOneTodo(long id) {
        return todoRepository.findByIdTodo(id);
    }

    @Override
    public void updateTodo(Todo todo) {
        todoRepository.updateTodo(todo);
    }

    @Override
    public void deleteTodo(long id) {
        todoRepository.deleteTodo(id);
    }

    @Override
    public boolean hasNext(int dataSize, int pageSize, int page) {
        int totalPages = (int) Math.ceil((double) dataSize / pageSize);
        return totalPages > page;
    }
}
