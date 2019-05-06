package com.it.app.repository;

import com.it.app.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

    //TODO!!!!!!!!!!!!!!!!
    @Query("SELECT s, u FROM Shift s LEFT JOIN FETCH s.user ON user.role=role.name WHERE s.id = ?1")
    Optional<Shift> findById(Long id);

    @Query("SELECT s FROM Shift s LEFT JOIN FETCH s.user")
    List<Shift> findAll();
}
