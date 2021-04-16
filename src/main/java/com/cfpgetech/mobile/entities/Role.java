package com.cfpgetech.mobile.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String libelle;
    @Column(nullable = false)
    private String description;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    public Role(String libelle, String description, List<User> users) {
        this.libelle = libelle;
        this.description = description;
        this.users = users;
    }

    public Role(String libelle, String description) {
        this.libelle = libelle;
        this.description = description;
    }
}
