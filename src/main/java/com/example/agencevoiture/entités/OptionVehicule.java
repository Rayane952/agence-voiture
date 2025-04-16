package com.example.agencevoiture.entités;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Set;

@Entity
@Table(name = "option_vehicule")
@Data
public class OptionVehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_option")
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String description;

    @ManyToMany(mappedBy = "options")
    private Set<Vehicule> vehicules;
}
