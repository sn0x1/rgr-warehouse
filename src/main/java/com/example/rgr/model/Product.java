package com.example.rgr.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Назва товару

    private String description; // Опис

    private Double price; // Ціна

    private Integer quantity; // Кількість на складі

    private String imageUrl; // URL зображення товару
}