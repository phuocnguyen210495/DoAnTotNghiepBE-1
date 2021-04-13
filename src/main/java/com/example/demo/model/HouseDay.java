package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class HouseDay {
    @Id
    private java.util.Date date;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_house")
    private House house;
}
