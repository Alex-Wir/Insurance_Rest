package by.it.dao.impl;

import by.it.dao.ShiftDAO;
import by.it.model.Shift;
import by.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

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

    /**
     * Find all Shifts and fetching Insurances
     * HQL implementation
     *
     * @param firstResult - first result
     * @param maxResult   - max result
     * @return List<Shift>
     */
    @Override
    public List<Shift> findAllWithInsurances(Integer firstResult, Integer maxResult) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Shift shift JOIN FETCH shift.insurances";
            Query query = session.createQuery(hql);
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            return query.list();
        }
    }
}
