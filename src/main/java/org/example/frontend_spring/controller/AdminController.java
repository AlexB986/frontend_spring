package org.example.frontend_spring.controller;

import org.example.frontend_spring.pojo.FullUserDTO;
import org.example.frontend_spring.pojo.UserDTO;
import org.example.frontend_spring.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    private ClientService userService;

    public AdminController(ClientService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(ModelMap model) {
        return "admin";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute FullUserDTO user, ModelMap model) {
        userService.addUser(user);
        return "Admin_add";
    }

    @PutMapping("/{id}")
    public String updateUser(@ModelAttribute FullUserDTO user, ModelMap model) {
        userService.updateUser(user);
        return "Admin_update";

    }

    @GetMapping("/reads")
    public String findAllUsers(ModelMap model) {
        List<UserDTO> userList = userService.findAllUsers();
        model.addAttribute("userDTO",userList);
        return "Admin_Find_All";

    }

    @GetMapping("/read/{id}")
    public String findUserById(@RequestParam("id") Long id,ModelMap model) {
        UserDTO findUser = userService.findUserById(id);
        model.addAttribute("userDTO",findUser);
        return "Admin_Find_id";

    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id,ModelMap model) {
        userService.deleteUser(id);
        return "Admin_Delete";

    }

}
