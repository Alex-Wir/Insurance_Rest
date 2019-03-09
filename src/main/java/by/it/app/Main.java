package by.it.app;

import by.it.dao.CarDAO;
import by.it.dao.InsuranceDAO;
import by.it.dao.ShiftDAO;
import by.it.dao.impl.CarDAOImpl;
import by.it.dao.impl.InsuranceDAOImpl;
import by.it.dao.impl.ShiftDAOImpl;
import by.it.model.Car;
import by.it.model.Insurance;
import by.it.model.Shift;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MONTHS;

public class Main {
    private static final CarDAO carDAO = CarDAOImpl.getInstance();
    private static final InsuranceDAO insuranceDAO = InsuranceDAOImpl.getInstance();
    private static final ShiftDAO shiftDAO = ShiftDAOImpl.getInstance();

    public static void main(String[] args) {

        createShift();

        createCar("FR", "PRS123", "JKHFJ123");
        createCar("BY", "AA1234", "Q1W2E3R4");

        createInsurance(1L, 10F, 900F,
                now().plus(6, MONTHS), getCar(1L), shiftDAO.getOne(1L));
        createInsurance(1L, 8F, 700F,
                now().plus(3, MONTHS), getCar(2L), shiftDAO.getOne(1L));

        updateInsuranceAmount(getInsurance(1L), 1000F);
    }

    private static Car getCar(Long id) {
        if (id == null) {
            return null;
        }
        return carDAO.getOne(id);
    }

    private static Insurance getInsurance(Long id) {
        if (id == null) {
            return null;
        }
        return insuranceDAO.getOne(id);
    }

    private static void createCar(String country, String carNumber, String vin) {
        Car transientCar = new Car();
        transientCar.setCountry(country);
        transientCar.setCarNumber(carNumber);
        transientCar.setVin(vin);
        carDAO.save(transientCar);
    }

    private static void createInsurance(Long number, Float payment, Float amount, LocalDate period, Car car, Shift shift) {
        Insurance transientInsurance = new Insurance();
        transientInsurance.setNumber(number);
        transientInsurance.setPayment(payment);
        transientInsurance.setAmount(amount);
        transientInsurance.setPeriod(period);
        transientInsurance.setCar(car);
        transientInsurance.setShift(shift);
        insuranceDAO.save(transientInsurance);
    }

    private static void createShift() {
        Shift transientShift = new Shift();
        transientShift.setOpeningTime(LocalDateTime.now().minus(6, HOURS));
        transientShift.setClosingTime(LocalDateTime.now().plus(6, HOURS));
        shiftDAO.save(transientShift);
    }

    private static void updateInsuranceAmount(Insurance persistentInsurance, Float amount) {
        persistentInsurance.setAmount(amount);
        insuranceDAO.update(persistentInsurance);
    }
}