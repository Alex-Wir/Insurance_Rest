package by.it.dao.impl;

import by.it.dao.CarDAO;
import by.it.model.Car;
import by.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
     * Find Car by country and number
     * HQL implemantation
     *
     * @param country - country
     * @param number  - number
     * @return Car
     */
    @Override
    public Car findByCountryAndNumber(String country, String number) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Car car WHERE car.country = :country and car.carNumber = :number";
            Query query = session.createQuery(hql);
            query.setParameter("country", country);
            query.setParameter("number", number);
            return (Car) query.getSingleResult();
        }
    }
}
