package com.ccsw.ludoteca.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
