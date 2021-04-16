package com.cfpgetech.mobile.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Responsable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String matricule;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private String adresse;
    @Column(unique = true,nullable = false)
    private String telephone;
    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;
    @JsonIgnore
    @OneToOne(mappedBy = "responsable", fetch = FetchType.LAZY)
    private Programme programme;
    @JsonIgnore
    @OneToOne(mappedBy = "responsable", fetch = FetchType.LAZY)
    private Departement departement;

    public Responsable(String matricule, String nom, String prenom, String adresse, String telephone, User user) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.user = user;
    }
}
