package by.it.app.repository;

import by.it.app.model.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Auto implemented CRUD for Pos-entity
 */
public interface PosRepository extends JpaRepository<Pos, Long> {

    @Query("SELECT p FROM Pos p WHERE p.number LIKE CONCAT ('%', :number, '%')")
    List<Pos> findByNumberLike1(@Param("number") String number);
}
