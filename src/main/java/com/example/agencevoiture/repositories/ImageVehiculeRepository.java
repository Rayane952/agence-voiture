package com.example.agencevoiture.repositories;

import com.example.agencevoiture.entités.ImageVehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageVehiculeRepository extends JpaRepository<ImageVehicule, Long> {

    List<ImageVehicule> findByVehiculeId(Long vehiculeId);

    ImageVehicule findByVehiculeIdAndEstPrincipale(Long vehiculeId, Boolean estPrincipale);
}
