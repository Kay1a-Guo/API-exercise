package com.oocl.training.controller.mapper;

import com.oocl.training.controller.dto.TodoRequest;
import com.oocl.training.model.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TodoMapper {
    public Todo toResponse(Todo todo){
        Todo response = new Todo();
        BeanUtils.copyProperties(todo, response);
        return response;
    }
    public List<Todo> toResponseList(List<Todo> todos){
        return todos.stream().map(this::toResponse).toList();
    }
    public Todo toEntity(TodoRequest todo){
        Todo response = new Todo();
        BeanUtils.copyProperties(todo, response);
        return response;
    }
}
