package com.it.app.service;

import com.it.app.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User addUser(String name);

    List<User> findAll();

    User findById(Long id);

    User update(User user);

    void delete(Long id);
}
