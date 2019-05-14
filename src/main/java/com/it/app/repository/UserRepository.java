package com.it.app.repository;

import com.it.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for User entity
 */
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByName(String name);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.name = ?1")
    User findByName(String name);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles LEFT JOIN FETCH u.points p LEFT JOIN FETCH p.address WHERE u.id =?1")
    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles")
    List<User> findAll();

}
