package by.it.dao.impl;

import by.it.dao.PosDAO;
import by.it.model.Pos;
import by.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PosDAOImpl extends GenericDAOImpl<Pos, Long> implements PosDAO {
    private static PosDAOImpl instance;

    private PosDAOImpl() {
        super(Pos.class);
    }

    synchronized public static PosDAOImpl getInstance() {
        if (instance == null) {
            instance = new PosDAOImpl();
        }
        return instance;
    }

    /**
     * Update Pos number by Id
     * HQL implementation
     *
     * @param id        - id
     * @param newNumber - new number
     */
    @Override
    public void updateNumber(Long id, String newNumber) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE Pos pos set pos.number = :newNumber WHERE pos.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("newNumber", newNumber);
            int result = query.executeUpdate();
            transaction.commit();
            System.out.println("Rows affected " + result + " in Pos");
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
