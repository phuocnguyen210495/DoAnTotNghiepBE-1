package com.example.demo.model;

import com.example.demo.model.auth.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BillService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_bill")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "id_service")
    private Service service;
}
