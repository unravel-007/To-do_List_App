package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.model.User;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//to do 
@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoController(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    //add
    @PostMapping("/add")
    public ResponseEntity<?> addTodo(@RequestHeader("Authorization") String token, @RequestBody Todo todo) {
        System.out.println("Received Token: " + token);
        System.out.println("Received TODO: " + todo);

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token format");
        }

        token = token.substring(7); // Remove "Bearer " prefix
        Optional<User> user = userRepository.findByToken(token);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid token or user not found");
        }

        if (todo.getTask() == null || todo.getTask().trim().isEmpty() ||
                todo.getDate() == null || todo.getDate().trim().isEmpty() ||
                todo.getTime() == null || todo.getTime().trim().isEmpty() ||
                todo.getDescription() == null || todo.getDescription().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("All fields are required: task, date, time, description");
        }

        todo.setUser(user.get());
        todoRepository.save(todo);

        return ResponseEntity.ok("TODO added successfully");
    }
//mine
    @GetMapping("/mine")
    public ResponseEntity<?> viewMyTodos(@RequestHeader("Authorization") String token) {
        System.out.println("Received Token: " + token);

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token format");
        }

        token = token.substring(7); // Remove "Bearer " prefix
        Optional<User> user = userRepository.findByToken(token);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid token or user not found");
        }

        List<Todo> todos = todoRepository.findByUser(user.get());
        return ResponseEntity.ok(todos);
    }

//all
    @GetMapping("/all")
    public ResponseEntity<List<Todo>> viewAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return ResponseEntity.ok(todos);
    }
}
