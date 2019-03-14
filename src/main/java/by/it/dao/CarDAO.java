package by.it.dao;

import by.it.model.Car;

import java.util.List;

public interface CarDAO extends GenericDAO<Car, Long> {

    List<Car> findAll(Integer firstResult, Integer maxResult);
}
