package by.it.app;

import by.it.model.*;

import java.util.List;

import static by.it.app.Initialization.*;
import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.MONTHS;

public class Main extends DAOInstance {

    public static void main(String[] args) {

        //CRUD
        createRole(RoleEnum.EMPLOYEE);
        createRole(RoleEnum.MANAGER);
        createRole(RoleEnum.CUSTOMER);

        createUser("John", roleDAO.getOne(1L));
        createUser("Jane", roleDAO.getOne(2L));
        createUser("Jack", roleDAO.getOne(3L));

        createPoint("Grodno Office");
        createPoint("Minsk Office");
        createAddress();
        updatePointAddress(pointDAO.getOne(1L), addressDAO.getOne(1L));

        updateUserPoint(userDAO.getOne(1L), pointDAO.getOne(1L));
        updateUserPoint(userDAO.getOne(2L), pointDAO.getOne(1L));
        updateUserPoint(userDAO.getOne(2L), pointDAO.getOne(2L));

        createPos(pointDAO.getOne(1L));
        createShift(posDAO.getOne(1L), userDAO.getOne(1L));

        createCar("FR", "PRS123", "JKHFJ123");
        createCar("BY", "AA1234", "Q1W2E3R4");

        createInsurance(1L, 10F, 900F,
                now().plus(6, MONTHS), carDAO.getOne(1L), shiftDAO.getOne(1L), userDAO.getOne(3L));
        createInsurance(1L, 8F, 700F,
                now().plus(3, MONTHS), carDAO.getOne(2L), shiftDAO.getOne(1L), userDAO.getOne(3L));

        updateInsuranceAmount(insuranceDAO.getOne(1L), 1000F);

        //HQL
        hqlExample();

        //NativeSQL
        nsqlExample();

        //CRUD - delete

    }

    private static void hqlExample() {
        System.out.println("=== HQL section ===");

        List<Point> points = pointDAO.findAll(0, 5);
        System.out.println("### Point: " + points.get(0).getName());
        System.out.println("### Point: " + points.get(1).getName());

        Car car = carDAO.findByCountryAndNumber("BY", "AA1234");
        System.out.println("### Car [BY AA1234] has ID = " + car.getId());

        List<Shift> shiftsListWithInsurances = shiftDAO.findAllWithInsurances(0, 5);
        System.out.println("### Shift with ID = " + shiftsListWithInsurances.get(0).getId() +
                " has " + shiftsListWithInsurances.get(0).getInsurances().size() + " insurances");
    }

    public static void nsqlExample() {
        System.out.println("=== NativeSQL section ===");
        List<User> users = userDAO.findByName(0, 5, "Jane");
        System.out.println("### Find " + users.size() + " user(s) with name \"Jane\"");
    }
}
