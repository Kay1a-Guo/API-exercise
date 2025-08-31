package com.oocl.training.repository.todo;

import com.oocl.training.model.Todo;

import java.util.List;

public interface TodoRepository {
    Todo addTodo(Todo todo);

    List<Todo> getAllTodo();

    Todo getTodoById(Integer id);

    List<Todo> getTodosByPage(int page, int size);

    void updateTodo(Integer id, Todo updatedTodo);


}
