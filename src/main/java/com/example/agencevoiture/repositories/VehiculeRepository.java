package com.example.agencevoiture.repositories;

import com.example.agencevoiture.entités.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    List<Vehicule> findByDisponibilite(Boolean disponibilite);

    List<Vehicule> findByMarqueContainingIgnoreCase(String marque);

    @Query("SELECT v FROM Vehicule v WHERE v.prix <= ?1")
    List<Vehicule> findByPrixMax(Double prixMax);
}
