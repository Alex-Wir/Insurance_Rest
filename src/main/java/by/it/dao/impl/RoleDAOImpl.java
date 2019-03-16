package by.it.dao.impl;

import by.it.dao.RoleDAO;
import by.it.model.Role;
import by.it.model.RoleEnum;
import by.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class RoleDAOImpl extends GenericDAOImpl<Role, Long> implements RoleDAO {
    private static RoleDAOImpl instance;

    private RoleDAOImpl() {
        super(Role.class);
    }

    synchronized public static RoleDAOImpl getInstance() {
        if (instance == null) {
            instance = new RoleDAOImpl();
        }
        return instance;
    }

    /**
     * Return quantity of Users by Role
     * Criteria implementation
     *
     * @param role - role from RoleEnum
     * @return - Integer quantity of users
     */
    @Override
    public Integer getQuantityOfUsersByRole(RoleEnum role) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Role> cr = cb.createQuery(Role.class);
            Root<Role> root = cr.from(Role.class);
            cr.select(root).where(cb.equal(root.get("role"), role));
            Query<Role> query = session.createQuery(cr);
            Role r = query.getSingleResult();
            return r.getUser().size();
        }
    }
}
