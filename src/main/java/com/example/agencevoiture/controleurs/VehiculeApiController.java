package com.example.agencevoiture.controleurs;

import com.example.agencevoiture.entités.Vehicule;
import com.example.agencevoiture.services.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Contrôleur REST pour gérer les opérations liées aux véhicules.
 * Fournit des points d'accès pour consulter, créer, modifier et supprimer des véhicules.
 */
@RestController
@RequestMapping("/api/vehicules")
public class VehiculeApiController {

    /** Service pour les opérations métiers sur les véhicules. */
    @Autowired
    private VehiculeService vehiculeService;

    /**
     * Récupère la liste de tous les véhicules.
     *
     * @return une liste de tous les véhicules
     */
    @GetMapping
    public List<Vehicule> getAllVehicules() {
        return vehiculeService.getAllVehicules();
    }

    /**
     * Récupère la liste des véhicules disponibles (non loués).
     *
     * @return une liste de véhicules disponibles
     */
    @GetMapping("/disponibles")
    public List<Vehicule> getVehiculesDisponibles() {
        return vehiculeService.getVehiculesDisponibles();
    }

    /**
     * Récupère un véhicule spécifique par son identifiant.
     *
     * @param id l'identifiant du véhicule
     * @return le véhicule correspondant ou 404 si non trouvé
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable Long id) {
        return vehiculeService.getVehiculeById(id)
                .map(vehicule -> ResponseEntity.ok().body(vehicule))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée un nouveau véhicule.
     *
     * @param vehicule les données du véhicule à créer
     * @return le véhicule créé avec un code HTTP 201
     */
    @PostMapping
    public ResponseEntity<Vehicule> createVehicule(@RequestBody Vehicule vehicule) {
        vehicule.setDateArrivee(LocalDate.now());
        Vehicule savedVehicule = vehiculeService.saveVehicule(vehicule);
        return new ResponseEntity<>(savedVehicule, HttpStatus.CREATED);
    }

    /**
     * Met à jour les informations d'un véhicule existant.
     *
     * @param id l'identifiant du véhicule à mettre à jour
     * @param vehicule les nouvelles données du véhicule
     * @return le véhicule mis à jour ou 404 si non trouvé
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> updateVehicule(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.getVehiculeById(id)
                .map(existingVehicule -> {
                    vehicule.setId(id);
                    Vehicule updatedVehicule = vehiculeService.saveVehicule(vehicule);
                    return ResponseEntity.ok().body(updatedVehicule);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime un véhicule par son identifiant.
     *
     * @param id l'identifiant du véhicule à supprimer
     * @return une réponse HTTP 200 si supprimé, ou 404 si non trouvé
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        return vehiculeService.getVehiculeById(id)
                .map(vehicule -> {
                    vehiculeService.deleteVehicule(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Recherche des véhicules par marque ou par prix maximum.
     * Si les deux paramètres sont absents, retourne tous les véhicules.
     *
     * @param marque la marque du véhicule à rechercher (optionnel)
     * @param prixMax le prix maximum du véhicule (optionnel)
     * @return une liste de véhicules correspondant aux critères
     */
    @GetMapping("/search")
    public List<Vehicule> searchVehicules(@RequestParam(required = false) String marque,
                                          @RequestParam(required = false) Double prixMax) {
        if (marque != null && !marque.isEmpty()) {
            return vehiculeService.searchVehiculesByMarque(marque);
        } else if (prixMax != null) {
            return vehiculeService.getVehiculesByPrixMax(prixMax);
        }
        return vehiculeService.getAllVehicules();
    }
}
