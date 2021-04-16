package com.cfpgetech.mobile.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String description;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Responsable responsable;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Departement departement;
    @JsonIgnore
    @OneToMany(mappedBy = "programme")
    private List<Formation> formations = new ArrayList<Formation>();

    public Programme(String nom, String description, Responsable responsable, Departement departement) {
        this.nom = nom;
        this.description = description;
        this.responsable = responsable;
        this.departement = departement;
    }
}
