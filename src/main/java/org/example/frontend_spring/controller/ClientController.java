package org.example.frontend_spring.controller;

import org.example.frontend_spring.pojo.FullUserDTO;
import org.example.frontend_spring.pojo.UserDTO;
import org.example.frontend_spring.service.ClientService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("client")
public class ClientController {

    private ClientService userService;

    public ClientController(ClientService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody FullUserDTO user, ModelMap model) {
        userService.addUser(user);
        return "admin";

    }

    @PutMapping("/id")
    public String updateUser(@RequestBody FullUserDTO user, ModelMap model) {
        userService.updateUser(user);
        return "admin";

    }

    @GetMapping("/read")
    public String findAllUsers(ModelMap model) {
        List<UserDTO> userList = userService.findAllUsers();
        return "user";

    }

    @GetMapping("/read/id")
    public String findUserById(@PathVariable Long id,ModelMap model) {
        userService.findUserById(id);
        return "admin";

    }

    @DeleteMapping("/delete/id")
    public String deleteUserById(@PathVariable Long id,ModelMap model) {
        userService.deleteUser(id);
        return "admin";

    }

}
