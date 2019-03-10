package by.it.app;

import by.it.dao.*;
import by.it.dao.impl.*;

class DAOInstance {
    static final CarDAO carDAO = CarDAOImpl.getInstance();
    static final InsuranceDAO insuranceDAO = InsuranceDAOImpl.getInstance();
    static final ShiftDAO shiftDAO = ShiftDAOImpl.getInstance();
    static final PosDAO posDAO = PosDAOImpl.getInstance();
    static final PointDAO pointDAO = PointDAOImpl.getInstance();
    static final AddressDAO addressDAO = AddressDAOImpl.getInstance();
    static final UserDAO userDAO = UserDAOImpl.getInstance();
    static final RoleDAO roleDAO = RoleDAOImpl.getInstance();
}
