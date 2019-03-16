package by.it.dao.impl;

import by.it.dao.InsuranceDAO;
import by.it.model.Insurance;
import by.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    /**
     * Delete Insurance by Number
     * HQL implementation
     *
     * @param number - Insurance Number
     */
    @Override
    public void deleteByNumber(Long number) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "DELETE FROM Insurance WHERE number = :number";
            Query query = session.createQuery(hql);
            query.setParameter("number", number);
            int result = query.executeUpdate();
            transaction.commit();
            System.out.println("### Rows affected " + result + " in Insurance");
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
