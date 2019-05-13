package com.it.app.repository;

import com.it.app.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    @Query("SELECT i FROM Insurance i LEFT JOIN FETCH i.car LEFT JOIN FETCH i.user WHERE i.id = ?1")
    Optional<Insurance> findById(Long id);

    @Query("SELECT i FROM Insurance i LEFT JOIN FETCH i.car LEFT JOIN FETCH i.user")
    List<Insurance> findAll();

    @Query("SELECT i FROM Insurance i LEFT JOIN FETCH i.car LEFT JOIN FETCH i.user WHERE i.user.id = ?1")
    List<Insurance> findAllByUserId(Long id);

    @Query("SELECT i FROM Insurance i LEFT JOIN FETCH i.car c LEFT JOIN FETCH i.user WHERE c.number = ?1")
    List<Insurance> findAllByCarNumber(String number);

    @Query("SELECT i FROM Insurance i LEFT JOIN FETCH i.car LEFT JOIN FETCH i.user WHERE i.date>= ?1 and i.date<= ?2")
    List<Insurance> findAllByYear(LocalDate yearBegin, LocalDate yearEnd);

    @Query("SELECT i FROM Insurance i LEFT JOIN FETCH i.car LEFT JOIN FETCH i.user LEFT JOIN FETCH i.shift s LEFT JOIN FETCH s.pos p LEFT JOIN FETCH p.point point WHERE i.date>= ?1 and i.date<= ?2 and point.id = ?3")
    List<Insurance> findAllByYearAndPoint (LocalDate yearBegin, LocalDate yearEnd, Long pointId);

    @Query("SELECT i FROM Insurance i LEFT JOIN FETCH i.car LEFT JOIN FETCH i.user LEFT JOIN FETCH i.shift s LEFT JOIN FETCH s.pos p WHERE i.date>= ?1 and i.date<= ?2 and p.id = ?3")
    List<Insurance> findAllByPeriodAndPos (LocalDate dateFrom, LocalDate dateTo, Long posId);

}
