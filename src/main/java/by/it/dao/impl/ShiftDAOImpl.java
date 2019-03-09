package by.it.dao.impl;

import by.it.dao.ShiftDAO;
import by.it.model.Shift;

public class ShiftDAOImpl extends GenericDAOImpl<Shift, Long> implements ShiftDAO {
    private static ShiftDAOImpl instance;

    private ShiftDAOImpl() {
        super(Shift.class);
    }

    synchronized public static ShiftDAOImpl getInstance() {
        if (instance == null) {
            instance = new ShiftDAOImpl();
        }
        return instance;
    }
}
