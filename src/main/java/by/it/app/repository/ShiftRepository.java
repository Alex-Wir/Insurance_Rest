package by.it.app.repository;

import by.it.app.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for for Shift entity
 */
public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
