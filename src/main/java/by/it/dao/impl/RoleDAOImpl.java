package by.it.dao.impl;

import by.it.dao.RoleDAO;
import by.it.model.Role;

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
}
