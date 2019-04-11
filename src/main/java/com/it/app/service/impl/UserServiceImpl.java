package com.it.app.service.impl;

import com.it.app.model.User;
import com.it.app.repository.UserRepository;
import com.it.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        User savedUser = userRepository.saveAndFlush(user);
        return savedUser;
    }

    @Override
    public User addUser(String name) {
        User savedUser = new User();
        savedUser.setName(name);
        userRepository.saveAndFlush(savedUser);
        return savedUser;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
