package com.it.app.repository;

import com.it.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository for Role entity
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByName(String name);

    Role findByName(String name);
}
