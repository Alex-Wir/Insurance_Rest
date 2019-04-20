package by.it.app.repository;

import by.it.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for for Role entity
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}
