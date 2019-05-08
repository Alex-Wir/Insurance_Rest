package com.it.app.repository;

import com.it.app.model.Pos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosRepository extends JpaRepository<Pos, Long> {

    boolean existsByName(String name);

    Pos findByName(String name);
}
