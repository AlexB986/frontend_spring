package org.example.frontend_spring.service;

import org.example.frontend_spring.pojo.FullUserDTO;
import org.example.frontend_spring.pojo.UserDTO;

import java.util.List;

public interface ClientService {
    void addUser(FullUserDTO user);

    void updateUser(FullUserDTO user);

    UserDTO findUserById(Long id);

    List<UserDTO> findAllUsers();

    void deleteUser(Long id);
}
