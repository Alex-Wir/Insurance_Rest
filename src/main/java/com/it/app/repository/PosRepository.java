package com.it.app.repository;

import com.it.app.model.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for Pos entity
 */
public interface PosRepository extends JpaRepository<Pos, Long> {

    @Query("SELECT p FROM Pos p LEFT JOIN FETCH p.point LEFT JOIN FETCH p.shifts WHERE p.id = ?1")
    Optional<Pos> findById(Long id);

    @Query("SELECT p FROM Pos p LEFT JOIN FETCH p.point")
    List<Pos> findAll();

    boolean existsByName(String name);

    Pos findByName(String name);
}
