package com.example.agencevoiture.services;

import com.example.agencevoiture.entités.Vehicule;
import com.example.agencevoiture.repositories.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Spring pour la gestion des véhicules.
 * Fournit des méthodes métiers pour récupérer, créer, modifier et supprimer des véhicules,
 * ainsi que des fonctionnalités de recherche.
 */
@Service
public class VehiculeService {

    /** Référentiel Spring Data JPA pour les entités Vehicule. */
    @Autowired
    private VehiculeRepository vehiculeRepository;

    /**
     * Récupère tous les véhicules de la base de données.
     *
     * @return une liste de tous les véhicules
     */
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    /**
     * Récupère les véhicules disponibles (non loués).
     *
     * @return une liste de véhicules disponibles
     */
    public List<Vehicule> getVehiculesDisponibles() {
        return vehiculeRepository.findByDisponibilite(true);
    }

    /**
     * Récupère un véhicule par son identifiant.
     *
     * @param id l'identifiant du véhicule
     * @return un Optional contenant le véhicule s'il existe, vide sinon
     */
    public Optional<Vehicule> getVehiculeById(Long id) {
        return vehiculeRepository.findById(id);
    }

    /**
     * Enregistre ou met à jour un véhicule.
     *
     * @param vehicule le véhicule à sauvegarder
     * @return le véhicule sauvegardé
     */
    public Vehicule saveVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    /**
     * Supprime un véhicule par son identifiant.
     *
     * @param id l'identifiant du véhicule à supprimer
     */
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    /**
     * Recherche des véhicules par marque (insensible à la casse).
     *
     * @param marque la marque à rechercher
     * @return une liste de véhicules correspondant à la marque
     */
    public List<Vehicule> searchVehiculesByMarque(String marque) {
        return vehiculeRepository.findByMarqueContainingIgnoreCase(marque);
    }

    /**
     * Récupère les véhicules dont le prix est inférieur ou égal à un maximum donné.
     *
     * @param prixMax le prix maximal
     * @return une liste de véhicules correspondant au critère de prix
     */
    public List<Vehicule> getVehiculesByPrixMax(Double prixMax) {
        return vehiculeRepository.findByPrixMax(prixMax);
    }
}
