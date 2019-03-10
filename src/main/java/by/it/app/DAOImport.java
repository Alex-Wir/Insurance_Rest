package by.it.app;

import by.it.dao.CarDAO;
import by.it.dao.InsuranceDAO;
import by.it.dao.ShiftDAO;
import by.it.dao.impl.CarDAOImpl;
import by.it.dao.impl.InsuranceDAOImpl;
import by.it.dao.impl.ShiftDAOImpl;

class DAOImport {
    protected static final CarDAO carDAO = CarDAOImpl.getInstance();
    protected static final InsuranceDAO insuranceDAO = InsuranceDAOImpl.getInstance();
    protected static final ShiftDAO shiftDAO = ShiftDAOImpl.getInstance();
}
