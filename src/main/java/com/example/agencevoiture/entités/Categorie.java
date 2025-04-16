package com.example.agencevoiture.entités;

import com.example.agencevoiture.entités.Vehicule;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Table(name = "categorie")
@Data
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorie")
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String description;

    @OneToMany(mappedBy = "categorie")
    private List<Vehicule> vehicules;
}
