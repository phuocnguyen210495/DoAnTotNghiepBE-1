package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String times;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Boolean status;
}
