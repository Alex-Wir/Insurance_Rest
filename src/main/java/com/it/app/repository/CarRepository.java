package com.it.app.repository;

import com.it.app.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JPA Repository for Car entity
 */
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE LOWER(c.number) = LOWER(?1)")
    List<Car> findAllByNumber(String number);

}
