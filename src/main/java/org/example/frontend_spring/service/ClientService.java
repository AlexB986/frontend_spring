package org.example.frontend_spring.service;

import org.example.frontend_spring.model.User;

import java.util.List;

public interface ClientService {
    void addUser(User user);

    void updateUser(User user);

    User findUserById(Long id);

    List<User> findAllUsers();

    void deleteUser(Long id);
}
