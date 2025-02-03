package com.example.apipracticetest.repository;

import com.example.apipracticetest.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TodoRepositoryImpl implements TodoRepository{

    private final Map<Long, Todo> todos = new HashMap<>();     // todo 저장소
    private long currentId = 1L;    // 현재 id

    @Override
    public void saveTodo(Todo todo) {
        todo.setId(currentId++);            // todo에 id 세팅 후 id 1 증가시킴
        todos.put(todo.getId(), todo);      // 키가 등록되지 않으면 null 반환
    }

    @Override
    public List<Todo> findAllTodo() {
        return todos.values().stream().toList();    // unmodifiableList 반환(불변) -> 원본인 todos의 불변성을 보장하지 않음
    }

    @Override
    public Todo findByIdTodo(long id) {
        return todos.get(id).clone();   // 깊은 복사를 통한 외부에서의 객체 수정 방지
    }

    @Override
    public void updateTodo(Todo todo) {
        todos.replace(todo.getId(), todo);  // 키가 존재하지 않으면 null 반환
    }

    @Override
    public void deleteTodo(long id) {
        todos.remove(id);
    }
}
