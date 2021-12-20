package ru.job4j.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@Controller
public class RegControl {

    private final UserService userService;

    @Autowired
    public RegControl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        boolean isNewUser = userService.saveUser(user);

        if (!isNewUser) {
            model.addAttribute("errorMessage", "User with such name is already exist");
        }

        return isNewUser ? "redirect:/login" : "reg";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}