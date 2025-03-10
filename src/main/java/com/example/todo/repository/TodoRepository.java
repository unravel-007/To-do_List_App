package com.example.todo.repository;

import com.example.todo.model.Todo;
import com.example.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;


public interface TodoRepository extends JpaRepository<Todo, UUID> {
    List<Todo> findByUser(User user);
}

