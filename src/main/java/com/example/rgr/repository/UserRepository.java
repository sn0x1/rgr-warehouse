package com.example.rgr.repository;

import com.example.rgr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// працюємо з таблицею User, де ID має тип Long
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Spring сам згенерує SQL-запит для пошуку по імені
    Optional<User> findByUsername(String username);
}