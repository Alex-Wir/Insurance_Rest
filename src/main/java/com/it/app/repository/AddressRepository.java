package com.it.app.repository;

import com.it.app.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository for Address entity
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
