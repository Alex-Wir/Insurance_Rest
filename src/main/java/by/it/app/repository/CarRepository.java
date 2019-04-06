package by.it.app.repository;

import by.it.app.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Auto implemented CRUD for Car-entity
 */
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByCarNumberIsEndingWith(String numberEnd);
}
