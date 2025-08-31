package com.oocl.training.repository.todo;

import com.oocl.training.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTodoRepository extends JpaRepository<Todo,Integer> {

}
