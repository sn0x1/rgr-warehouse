package com.example.rgr.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Lombok: геттери, сеттери, toString
@NoArgsConstructor // Lombok: пустий конструктор
@AllArgsConstructor // Lombok: конструктор з усіма полями
@Entity // це таблиця в БД
@Table(name = "users") // Назва таблиці в базі
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // Логін має бути унікальним
    private String username;

    @Column(nullable = false)
    private String password;

    private String role; //"ROLE_ADMIN" або "ROLE_USER"
}