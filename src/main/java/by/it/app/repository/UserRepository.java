package by.it.app.repository;

import by.it.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA repository for for User entity
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContains(String name);
}
