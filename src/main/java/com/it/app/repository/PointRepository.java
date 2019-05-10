package com.it.app.repository;

import com.it.app.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {

    @Query("SELECT p FROM Point p LEFT JOIN FETCH p.address")
    List<Point> findAll();

    @Query("SELECT p FROM Point p LEFT JOIN FETCH p.address LEFT JOIN FETCH p.users WHERE p.id = ?1")
    Optional<Point> findById(Long id);

   /* @Query("SELECT p FROM Point p LEFT JOIN FETCH p.address LEFT JOIN FETCH p.users u WHERE u.id = ?1")
    List<Point> findPointsByUserId(Long id);*/
}
