package by.it.dao;

import by.it.model.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Long> {

    List<User> findByName(Integer firstResult, Integer maxResult, String name);
}
