package com.oocl.training.repository.todo;

import com.oocl.training.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoDbRepository implements TodoRepository {
    JpaTodoRepository jpaTodoRepository;
    public TodoDbRepository(JpaTodoRepository jpaTodoRepository){
        this.jpaTodoRepository = jpaTodoRepository;
    }

    @Override
    public Todo addTodo(Todo todo) {
        return jpaTodoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodo() {
        return jpaTodoRepository.findAll();
    }

    @Override
    public Todo getTodoById(Integer id) {
        return jpaTodoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Todo> getTodosByPage(int page, int size) {
        return jpaTodoRepository.findAll().subList((page-1)*size,page*size);
    }

    @Override
    public void updateTodo(Integer id) {
        Todo todo = jpaTodoRepository.findById(id).orElse(null);
        if (todo != null) {
            todo.setCompleted(!todo.isCompleted());
            jpaTodoRepository.save(todo);
        }
    }

//    @Override
//    public void updateTodo(Integer id, Todo updatedTodo) {
//        jpaTodoRepository.save(updatedTodo);
//    }

    @Override
    public void deleteTodo(Integer id) {
        jpaTodoRepository.deleteById(id);
    }




}
