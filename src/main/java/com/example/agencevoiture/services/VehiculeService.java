package com.example.agencevoiture.services;

import com.example.agencevoiture.entités.Vehicule;
import com.example.agencevoiture.repositories.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    public List<Vehicule> getVehiculesDisponibles() {
        return vehiculeRepository.findByDisponibilite(true);
    }

    public Optional<Vehicule> getVehiculeById(Long id) {
        return vehiculeRepository.findById(id);
    }

    public Vehicule saveVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    public List<Vehicule> searchVehiculesByMarque(String marque) {
        return vehiculeRepository.findByMarqueContainingIgnoreCase(marque);
    }

    public List<Vehicule> getVehiculesByPrixMax(Double prixMax) {
        return vehiculeRepository.findByPrixMax(prixMax);
    }
}
