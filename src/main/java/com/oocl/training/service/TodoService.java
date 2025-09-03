package com.oocl.training.service;

import com.oocl.training.model.Todo;
import com.oocl.training.repository.todo.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.addTodo(todo);
    }

    public Todo getTodoById(Integer id) {
        return todoRepository.getTodoById(id);
    }

    public List<Todo> getAllTodo() {
        return todoRepository.getAllTodo()
                .stream()
                .toList();
    }

    public List<Todo> getTodosByPage(int page, int size) {
        return todoRepository.getTodosByPage(page, size);
    }

    public void deleteTodoById(Integer id) {
        Todo todo = todoRepository.getTodoById(id);
        if (todo == null || !todo.isCompleted()) {
            throw new RuntimeException("Todo with id " + id + " does not exist.");
        }else {
            todoRepository.deleteTodo(id);
        }
    }

    public Todo updateTodoCompleted(Integer id) {
        Todo todo = todoRepository.getTodoById(id);
        if (todo == null) {
            throw new RuntimeException("Todo with id " + id + " does not exist.");
        }
        // 只更新 completed 字段
        todo.setCompleted(!todo.isCompleted());
        return todo;
        //todoRepository.updateTodo(id, todo);
    }

}
