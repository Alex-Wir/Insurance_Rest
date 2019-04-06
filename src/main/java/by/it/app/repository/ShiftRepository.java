package by.it.app.repository;

import by.it.app.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Auto implemented CRUD for Shift-entity
 */
public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
