package by.it.dao.impl;

import by.it.dao.PointDAO;
import by.it.model.Point;
import by.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PointDAOImpl extends GenericDAOImpl<Point, Long> implements PointDAO {
    private static PointDAOImpl instance;

    private PointDAOImpl() {
        super(Point.class);
    }

    synchronized public static PointDAOImpl getInstance() {
        if (instance == null) {
            instance = new PointDAOImpl();
        }
        return instance;
    }

    /**
     * Find all Points
     * HQL implementation
     *
     * @param firstResult - firstResult
     * @param maxResult   - maxResult
     * @return List<Point>
     */
    @Override
    public List<Point> findAll(Integer firstResult, Integer maxResult) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Point";
            Query query = session.createQuery(hql);
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            return query.list();
        }
    }
}
