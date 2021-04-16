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
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nom;
    @JsonIgnore
    @OneToMany(mappedBy = "departement")
    private List<Programme> programmes = new ArrayList<Programme>();
    @OneToOne(cascade = CascadeType.PERSIST)
    private Responsable responsable;

    public Departement(String nom, Responsable responsable) {
        this.nom = nom;
        this.responsable = responsable;
    }
}
