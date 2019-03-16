package by.it.dao;

import by.it.model.Car;

import java.util.List;

public interface CarDAO extends GenericDAO<Car, Long> {

    Car findByCountryAndNumber(String country, String number);
}
