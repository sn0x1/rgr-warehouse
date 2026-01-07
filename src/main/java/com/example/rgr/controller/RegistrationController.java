package com.example.rgr.controller;

import com.example.rgr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

@PostMapping("/register")
    public String registerUser(@RequestParam String username, 
                               @RequestParam String password,
                               @RequestParam String confirmPassword) {
        
        // 1. Перевірка паролів
        if (!password.equals(confirmPassword)) {
            return "redirect:/register?error"; 
        }

        // 2. Спроба створити юзера (тепер метод повертає true або false)
        boolean isCreated = userService.createUser(username, password, "ROLE_USER");

        if (!isCreated) {
            // Якщо повернуло false - значить юзер вже існує
            return "redirect:/register?userExists"; 
        }
        
        return "redirect:/login?registerSuccess";
    }
}