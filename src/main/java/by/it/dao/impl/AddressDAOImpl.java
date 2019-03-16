package by.it.dao.impl;

import by.it.dao.AddressDAO;
import by.it.model.Address;
import by.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AddressDAOImpl extends GenericDAOImpl<Address, Long> implements AddressDAO {
    private static AddressDAOImpl instance;

    private AddressDAOImpl() {
        super(Address.class);
    }

    synchronized public static AddressDAOImpl getInstance() {
        if (instance == null) {
            instance = new AddressDAOImpl();
        }
        return instance;
    }

    /**
     * Find page of Addresses by City
     * HQL implementation
     *
     * @param firstResult - first result
     * @param maxResult   - max result
     * @param city        - city
     * @return List<Address>
     */
    @Override
    public List<Address> findByCity(Integer firstResult, Integer maxResult, String city) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Address A WHERE A.city = :city";
            Query query = session.createQuery(hql);
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            query.setParameter("city", city);
            return query.list();
        }
    }
}

