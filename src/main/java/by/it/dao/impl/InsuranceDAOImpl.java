package by.it.dao.impl;

import by.it.dao.InsuranceDAO;
import by.it.model.Insurance;

public class InsuranceDAOImpl extends GenericDAOImpl<Insurance, Long> implements InsuranceDAO {
    private static InsuranceDAOImpl instance;

    private InsuranceDAOImpl() {
        super(Insurance.class);
    }

    synchronized public static InsuranceDAOImpl getInstance() {
        if (instance == null) {
            instance = new InsuranceDAOImpl();
        }
        return instance;
    }
}
