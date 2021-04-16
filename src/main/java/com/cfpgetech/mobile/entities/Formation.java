package com.cfpgetech.mobile.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String numero;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String domaine;
    @Column(nullable = false)
    private String duree;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Programme programme;

    public Formation(String numero, String nom, String domaine, String duree, Programme programme) {
        this.numero = numero;
        this.nom = nom;
        this.domaine = domaine;
        this.duree = duree;
        this.programme = programme;
    }
}
