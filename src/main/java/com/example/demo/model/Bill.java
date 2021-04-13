package com.example.demo.model;

import com.example.demo.model.auth.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nameUser;

    @Column(nullable = false)
    private String telephoneNumber;

    @Column(nullable = false)
    private java.util.Date bookingDate;

    @Column(nullable = false)
    private java.util.Date startDate;

    @Column(nullable = false)
    private java.util.Date endDate;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String evaluate;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_house")
    private House house;
}
