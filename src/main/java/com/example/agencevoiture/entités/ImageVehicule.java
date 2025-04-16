package com.example.agencevoiture.entités;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;


@Entity
@Table(name = "image_vehicule")
@Data
public class ImageVehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_vehicule")
    private Vehicule vehicule;

    @Column(name = "url_image", nullable = false)
    private String urlImage;

    @Column(name = "est_principale")
    private Boolean estPrincipale = false;
}
