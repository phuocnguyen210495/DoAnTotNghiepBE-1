package com.example.demo.model;

import com.example.demo.model.auth.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RepComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_house")
    private House house;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
