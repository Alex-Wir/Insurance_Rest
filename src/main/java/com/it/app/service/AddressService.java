package com.it.app.service;

import com.it.app.model.Address;

import java.util.List;

/**
 * Service interface for Address entity
 */
public interface AddressService {

    List<Address> findAll();

    Address findById(Long id);

    Address save(Address address);

    Address update(Address address);

    void delete(Address address);

    void deleteById(Long id);
}
