package com.example.rgr.config;

import com.example.rgr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Створюємо Адміна (логін: admin, пароль: admin)
        userService.createUser("admin", "admin", "ROLE_ADMIN");

        // Створюємо звичайного юзера для тесту (логін: user, пароль: user)
        userService.createUser("user", "user", "ROLE_USER");
    }
}