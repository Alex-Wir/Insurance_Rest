package com.it.app.service;

import com.it.app.model.User;

public interface UserService {

    User addUser(User user);

    User addUser(String name);
}
