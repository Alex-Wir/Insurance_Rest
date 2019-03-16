package by.it.dao.impl;

import by.it.dao.UserDAO;
import by.it.model.User;
import by.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {
    private static UserDAOImpl instance;

    private UserDAOImpl() {
        super(User.class);
    }

    synchronized public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    /**
     * Find page of Users by Name
     * NativeSQL implementation
     *
     * @param firstResult - first result
     * @param maxResult   - max result
     * @param name        - user name
     * @return List<User>
     */
    @Override
    public List<User> findByName(Integer firstResult, Integer maxResult, String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String nsql = "SELECT user.* FROM User user WHERE user.name=?1";
            Query query = session.createNativeQuery(nsql, User.class);
            query.setParameter(1, name);
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            return query.list();
        }
    }
}
