package com.it.app.repository;

import com.it.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA Repository for User entity
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
}
