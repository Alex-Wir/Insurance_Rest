package by.it.app;

import by.it.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;

class Initialization extends DAOInstance {

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

    static void createShift(Pos pos, User user) {
        Shift transientShift = new Shift();
        transientShift.setOpeningTime(LocalDateTime.now().minus(6, HOURS));
        transientShift.setClosingTime(LocalDateTime.now().plus(6, HOURS));
        transientShift.setPos(pos);
        transientShift.setUser(user);
        shiftDAO.save(transientShift);
    }

    static void createPos(Point point) {
        Pos transientPos = new Pos();
        transientPos.setNumber("POS123");
        transientPos.setPoint(point);
        posDAO.save(transientPos);
    }

    static void createPoint(String name) {
        Point transientPoint = new Point();
        transientPoint.setName(name);
        pointDAO.save(transientPoint);
    }

    static void createAddress() {
        Address transientAddress = new Address();
        transientAddress.setCity("Grodno");
        transientAddress.setStreet("Central street");
        transientAddress.setHouseNumber("1A");
        transientAddress.setPostcode("230000");
        addressDAO.save(transientAddress);
    }

    static void createUser(String name, Role role) {
        User transientUser = new User();
        transientUser.setName(name);
        transientUser.setRole(role);
        userDAO.save(transientUser);
    }

    static void createRole(RoleEnum roleEnum) {
        Role transientRole = new Role();
        transientRole.setRole(roleEnum);
        roleDAO.save(transientRole);
    }

    static void updatePointAddress(Point persistentPoint, Address address) {
        persistentPoint.setAddress(address);
        pointDAO.update(persistentPoint);
    }

    static void updateInsuranceAmount(Insurance persistentInsurance, Float amount) {
        persistentInsurance.setAmount(amount);
        insuranceDAO.update(persistentInsurance);
    }

    static void updateUserPoint(User persistentUser, Point point) {
        persistentUser.getPoints().add(point);
        userDAO.update(persistentUser);
    }
}
