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

    @GetMapping("/form")
    public String showAdd(ModelMap model) {
        model.addAttribute("user", new FullUserDTO());
        return "Admin_add";
    }


    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") FullUserDTO user) {
        userService.addUser(user);
        return "redirect:/admin/reads";
    }

    @GetMapping("/form/update/{id}")
    public String showAddUpdate(@PathVariable Long id, ModelMap model) {
        UserDTO user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "Admin_update";
    }

    @PutMapping("update/{id}")
    public String updateUser(@ModelAttribute("user") UserDTO user, @PathVariable("id") Long id) {
        userService.updateUser(user, id);
        return "redirect:/admin/reads";

    }

    @GetMapping("/reads")
    public String findAllUsers(ModelMap model) {
        List<UserDTO> userList = userService.findAllUsers();
        model.addAttribute("userDTO", userList);
        return "Admin_Find_All";

    }

    @GetMapping("/read/{id}")
    public String findUserById(@RequestParam("id") Long id, ModelMap model) {
        UserDTO findUser = userService.findUserById(id);
        model.addAttribute("userDTO", findUser);
        return "Admin_Find_id";

    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/reads";

    }

}
