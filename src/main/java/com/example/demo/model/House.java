package com.example.demo.model;

import com.example.demo.model.auth.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class House{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String numberRoom;

    @Column(nullable = false)
    private Boolean status;

    @JsonManagedReference
    @OneToMany(targetEntity = Image.class, mappedBy = "house", cascade = CascadeType.REMOVE)
    private List<Image> images;

    @JsonManagedReference
    @OneToMany(targetEntity = Bill.class, mappedBy = "house", cascade = CascadeType.REMOVE)
    private List<Bill> bill;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "houses_utilities",
            joinColumns = {@JoinColumn(name = "id_house")},
            inverseJoinColumns = {@JoinColumn(name = "id_utilitie")})
    private Set<Utilitie> utilitie;
}
