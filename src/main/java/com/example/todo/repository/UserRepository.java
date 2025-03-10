package com.example.todo.repository;

import com.example.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByToken(String token);
    Optional<User> findByEmail(String email);

}
