package com.example.agencevoiture.controleurs;

import com.example.agencevoiture.entités.Vehicule;
import com.example.agencevoiture.services.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeApiController {

    @Autowired
    private VehiculeService vehiculeService;

    @GetMapping
    public List<Vehicule> getAllVehicules() {
        return vehiculeService.getAllVehicules();
    }

    @GetMapping("/disponibles")
    public List<Vehicule> getVehiculesDisponibles() {
        return vehiculeService.getVehiculesDisponibles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable Long id) {
        return vehiculeService.getVehiculeById(id)
                .map(vehicule -> ResponseEntity.ok().body(vehicule))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vehicule> createVehicule(@RequestBody Vehicule vehicule) {
        vehicule.setDateArrivee(LocalDate.now());
        Vehicule savedVehicule = vehiculeService.saveVehicule(vehicule);
        return new ResponseEntity<>(savedVehicule, HttpStatus.CREATED);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        return vehiculeService.getVehiculeById(id)
                .map(vehicule -> {
                    vehiculeService.deleteVehicule(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

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
