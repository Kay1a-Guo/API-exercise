package com.oocl.training.controller.mapper;

import com.oocl.training.controller.dto.TodoRequest;
import com.oocl.training.controller.dto.TodoResponse;
import com.oocl.training.model.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TodoMapper {


    public TodoResponse toResponse(Todo todo){
        TodoResponse response = new TodoResponse();
        response.setId(todo.getId());
        response.setTitle(todo.getTitle());
        return response;
    }

    public List<TodoResponse> toResponseList(List<Todo> todos){
        return todos.stream().map(this::toResponse).toList();
    }

    public Todo toEntity(TodoRequest request){
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        return todo;
    }

}
