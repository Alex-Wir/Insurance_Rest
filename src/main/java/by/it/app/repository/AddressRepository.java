package by.it.app.repository;

import by.it.app.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Auto implemented CRUD for Address-entity
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByHouseNumberAfter(String number);
}