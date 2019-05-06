package com.it.app.repository;

import com.it.app.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    @Query("SELECT i FROM Insurance i LEFT JOIN FETCH i.car WHERE i.id = ?1")
    Optional<Insurance> findById(Long id);

    @Query("SELECT i FROM Insurance i LEFT JOIN FETCH i.car")
    List<Insurance> findAll();
}
