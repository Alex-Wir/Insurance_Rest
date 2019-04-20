package by.it.app.repository;

import by.it.app.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JPA repository for for Insurance entity
 */
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    @Query("SELECT i FROM Insurance i WHERE i.payment BETWEEN ?1 AND ?2")
    List<Insurance> findInsuranceByPaymentBetween1(Float lowerLimit, Float upperLimit);
}
