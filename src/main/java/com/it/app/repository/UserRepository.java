package com.it.app.repository;

import com.it.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByName(String name);

    User findByName(String name);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.id =?1")
    Optional<User> findById(Long id);

}
