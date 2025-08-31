package com.oocl.training.controller;

import com.oocl.training.controller.dto.TodoRequest;
import com.oocl.training.controller.dto.TodoResponse;
import com.oocl.training.controller.mapper.TodoMapper;
import com.oocl.training.model.Todo;
import com.oocl.training.repository.todo.TodoRepository;
import com.oocl.training.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api/v1/todos")
public class TodoController {
    private TodoMapper todoMapper;
    private TodoService todoService;
    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TodoResponse> getAllTodos() {
        return todoMapper.toResponseList(todoService.getAllTodo());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse addTodo(@Valid @RequestBody TodoRequest request) {
        Todo todo = todoMapper.toEntity(request);
        return todoMapper.toResponse(todoService.addTodo(todo));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoResponse getTodoById(@PathVariable int id) {
        return todoMapper.toResponse(todoService.getTodoById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteTodoById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTodo(@PathVariable int id, @Valid @RequestBody TodoRequest request) {
        Todo todo = todoMapper.toEntity(request);
        todoService.updateTodo(id, todo);
    }

}
