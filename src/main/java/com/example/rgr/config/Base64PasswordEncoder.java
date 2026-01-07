package com.example.rgr.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Base64;

@Component
public class Base64PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        // Перетворюємо пароль у Base64
        return Base64.getEncoder().encodeToString(rawPassword.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // Перевіряємо, чи співпадає введений пароль із тим, що в базі
        String encodedRaw = encode(rawPassword);
        return encodedRaw.equals(encodedPassword);
    }
}