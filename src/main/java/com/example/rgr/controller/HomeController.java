package com.example.rgr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        // Якщо користувач увійшов, покажемо його ім'я
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "home"; // Поверне файл home.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Поверне файл login.html
    }
}