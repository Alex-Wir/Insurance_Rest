package by.it.dao;

import by.it.model.Car;

public interface CarDAO extends GenericDAO<Car, Long> {

    Car findByCountryAndNumber(String country, String number);
}
