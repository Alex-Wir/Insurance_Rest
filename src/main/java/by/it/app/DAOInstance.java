package by.it.app;

import by.it.dao.*;
import by.it.dao.impl.*;

class DAOInstance {
    protected static final CarDAO carDAO = CarDAOImpl.getInstance();
    protected static final InsuranceDAO insuranceDAO = InsuranceDAOImpl.getInstance();
    protected static final ShiftDAO shiftDAO = ShiftDAOImpl.getInstance();
    protected static final PosDAO posDAO = PosDAOImpl.getInstance();
    protected static final PointDAO pointDAO = PointDAOImpl.getInstance();
    protected static final AddressDAO addressDAO = AddressDAOImpl.getInstance();
}
