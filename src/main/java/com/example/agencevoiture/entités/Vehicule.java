package com.example.agencevoiture.entités;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "vehicule")
@Data
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicule")
    private Long id;

    @Column(nullable = false)
    private String marque;

    @Column(nullable = false)
    private String modele;

    @Column(nullable = false)
    private Integer annee;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    private Integer kilometrage;

    private String couleur;

    private String carburant;

    private String transmission;

    private Integer puissance;

    @Column(name = "nombre_places")
    private Integer nombrePlaces;

    @Column(name = "date_arrivee", nullable = false)
    private LocalDate dateArrivee;

    private Boolean disponibilite = true;

    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    private List<ImageVehicule> images;

    @ManyToMany
    @JoinTable(
            name = "vehicule_option",
            joinColumns = @JoinColumn(name = "id_vehicule"),
            inverseJoinColumns = @JoinColumn(name = "id_option")
    )
    private Set<OptionVehicule> options;
}
