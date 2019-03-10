package by.it.app;

import by.it.model.Car;
import by.it.model.Insurance;
import by.it.model.Pos;
import by.it.model.Shift;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;

class Initialization extends DAOImport {

    static void createCar(String country, String carNumber, String vin) {
        Car transientCar = new Car();
        transientCar.setCountry(country);
        transientCar.setCarNumber(carNumber);
        transientCar.setVin(vin);
        carDAO.save(transientCar);
    }

    static void createInsurance(Long number, Float payment, Float amount, LocalDate period, Car car, Shift shift) {
        Insurance transientInsurance = new Insurance();
        transientInsurance.setNumber(number);
        transientInsurance.setPayment(payment);
        transientInsurance.setAmount(amount);
        transientInsurance.setPeriod(period);
        transientInsurance.setCar(car);
        transientInsurance.setShift(shift);
        insuranceDAO.save(transientInsurance);
    }

    static void createShift(Pos pos) {
        Shift transientShift = new Shift();
        transientShift.setOpeningTime(LocalDateTime.now().minus(6, HOURS));
        transientShift.setClosingTime(LocalDateTime.now().plus(6, HOURS));
        transientShift.setPos(pos);
        shiftDAO.save(transientShift);
    }

    static void createPos() {
        Pos transientPos = new Pos();
        transientPos.setNumber("POS123");
        posDAO.save(transientPos);
    }

    static void updateInsuranceAmount(Insurance persistentInsurance, Float amount) {
        persistentInsurance.setAmount(amount);
        insuranceDAO.update(persistentInsurance);
    }
}
