package org.example.frontend_spring.controller;

import org.example.frontend_spring.model.User;
import org.example.frontend_spring.service.ClientService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {

    private ClientService userService;

    public ClientController(ClientService userService) {
        this.userService = userService;
    }
    @PostMapping("/users")
    public String addUser(@RequestBody User user, ModelMap model) {
        userService.addUser(user);
        return "admin";

    }

    @PutMapping("/users")
    public String updateUser(@RequestBody User user,ModelMap model) {
        userService.updateUser(user);
        return "admin";

    }

    @GetMapping("/users")
    public String findAllUsers(ModelMap model) {
        List<User> userList = userService.findAllUsers();
        return "admin";

    }

    @GetMapping("/users/{id}")
    public String findUserById(@PathVariable Long id,ModelMap model) {
        userService.findUserById(id);
        return "admin";

    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable Long id,ModelMap model) {
        return "admin";

    }

}
