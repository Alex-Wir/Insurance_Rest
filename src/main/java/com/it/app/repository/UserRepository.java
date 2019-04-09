package com.it.app.repository;

import com.it.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Auto implemented CRUD for User-entity
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
