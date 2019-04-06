package by.it.app.repository;

import by.it.app.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Auto implemented CRUD for Point-entity
 */
public interface PointRepository extends JpaRepository<Point, Long> {

    List<Point> findByAddressCityContains(String city);
}