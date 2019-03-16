package by.it.dao;

import by.it.model.Role;
import by.it.model.RoleEnum;

public interface RoleDAO extends GenericDAO<Role, Long> {

    Integer getQuantityOfUsersByRole(RoleEnum role);
}
