package by.it.dao.impl;

import by.it.dao.CarDAO;
import by.it.model.Car;
import by.it.model.Insurance;
import by.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CarDAOImpl extends GenericDAOImpl<Car, Long> implements CarDAO {
    private static CarDAOImpl instance;

    private CarDAOImpl() {
        super(Car.class);
    }

    synchronized public static CarDAOImpl getInstance() {
        if (instance == null) {
            instance = new CarDAOImpl();
        }
        return instance;
    }

    /**
     * Find all Cars
     * HQL implementation
     *
     * @param firstResult - firstResult
     * @param maxResult   - maxResult
     * @return List<Car>
     */
    @Override
    public List<Car> findAll(Integer firstResult, Integer maxResult) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Car";
            Query query = session.createQuery(hql);
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            return query.list();
        }
    }
}
