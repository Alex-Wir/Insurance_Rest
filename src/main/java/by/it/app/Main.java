package by.it.app;

import static by.it.app.Initialization.*;
import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.MONTHS;

public class Main extends DAOImport {

    public static void main(String[] args) {

        createShift();

        createCar("FR", "PRS123", "JKHFJ123");
        createCar("BY", "AA1234", "Q1W2E3R4");

        createInsurance(1L, 10F, 900F,
                now().plus(6, MONTHS), carDAO.getOne(1L), shiftDAO.getOne(1L));
        createInsurance(1L, 8F, 700F,
                now().plus(3, MONTHS), carDAO.getOne(2L), shiftDAO.getOne(1L));

        updateInsuranceAmount(insuranceDAO.getOne(1L), 1000F);
    }
}