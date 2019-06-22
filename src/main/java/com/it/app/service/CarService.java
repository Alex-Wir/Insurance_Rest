package com.it.app.service;

import com.it.app.model.Car;

import java.util.List;

/**
 * Service interface for Car entity
 */
public interface CarService {

    List<Car> findAll();

    List<Car> findAllByNumber(String number);

    Car findById(Long id);

    Car save(Car car);

    Car update(Car car);

    void delete(Car car);

    void deleteById(Long id);
}
