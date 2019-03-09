package by.it.app;

import by.it.dao.CarDAO;
import by.it.dao.InsuranceDAO;
import by.it.dao.impl.CarDAOImpl;
import by.it.dao.impl.InsuranceDAOImpl;
import by.it.model.Car;
import by.it.model.Insurance;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.MONTHS;

public class Main {
    private static final CarDAO carDAO = CarDAOImpl.getInstance();
    private static final InsuranceDAO insuranceDAO = InsuranceDAOImpl.getInstance();

    public static void main(String[] args) {
        Car car = getCar();
        performInsuranceOperations(car);
    }

    private static Car getCar() {
        Car car = carDAO.getOne(1L);
        if (car == null) {
            createCar("BY", "AA1234", "Q1W2E3R4");
        }
        return carDAO.getOne(1L);
    }

    private static void createCar(String country, String carNumber, String vin) {
        Car transientCar = new Car();
        transientCar.setCountry(country);
        transientCar.setCarNumber(carNumber);
        transientCar.setVin(vin);
        carDAO.save(transientCar);
    }

    private static void performInsuranceOperations(Car car) {
        Insurance insurance = insuranceDAO.getOne(1L);
        if (insurance == null) {
            createInsurance(1L, 10.5F, 900F, now().plus(6, MONTHS), car);
        }
        Insurance persistentInsurance = insuranceDAO.getOne(1L);
        updateInsuranceAmount(persistentInsurance, 1000F);
        insuranceDAO.delete(1L);
    }

    private static void updateInsuranceAmount(Insurance persistentInsurance, Float amount) {
        persistentInsurance.setAmount(amount);
        insuranceDAO.update(persistentInsurance);
    }

    private static void createInsurance(Long number, Float payment, Float amount, LocalDate period, Car car) {
        Insurance transientInsurance = new Insurance();
        transientInsurance.setNumber(number);
        transientInsurance.setPayment(payment);
        transientInsurance.setAmount(amount);
        transientInsurance.setPeriod(period);
        transientInsurance.setCar(car);
        insuranceDAO.save(transientInsurance);
    }
}