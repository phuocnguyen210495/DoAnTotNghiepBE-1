package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Utilitie{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean status;
}
