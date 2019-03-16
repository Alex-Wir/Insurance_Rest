package by.it.dao;

import by.it.model.Address;

import java.util.List;

public interface AddressDAO extends GenericDAO<Address, Long> {

    List<Address> findByCity(Integer firstResult, Integer maxResult, String city);
}