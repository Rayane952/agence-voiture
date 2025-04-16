package com.example.agencevoiture.controleurs;

import com.example.agencevoiture.entités.Categorie;
import com.example.agencevoiture.entités.Vehicule;
import com.example.agencevoiture.services.CategorieService;
import com.example.agencevoiture.services.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;

    @Autowired
    private CategorieService categorieService;

    @GetMapping("/")
    public String home(Model model) {
        // Récupérer la liste des véhicules
        List<Vehicule> vehicules = vehiculeService.getAllVehicules();
        model.addAttribute("vehicules", vehicules);

        // Créer un nouvel objet Vehicule vide pour le formulaire du modal
        Vehicule vehicule = new Vehicule();
        // Définir une date d'arrivée par défaut
        vehicule.setDateArrivee(LocalDate.now());
        model.addAttribute("vehicule", vehicule);

        // Récupérer la liste des catégories pour le select du formulaire
        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("categories", categories);

        return "index";
    }

    @GetMapping("/vehicule/{id}")
    public String vehiculeDetails(@PathVariable Long id, Model model) {
        Vehicule vehicule = vehiculeService.getVehiculeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Véhicule non trouvé avec l'ID: " + id));
        model.addAttribute("vehicule", vehicule);
        return "vehicule-details";
    }

    @PostMapping("/vehicule/add")
    public String addVehicule(@ModelAttribute Vehicule vehicule) {
        // Si la date d'arrivée n'est pas définie, utiliser la date actuelle
        if (vehicule.getDateArrivee() == null) {
            vehicule.setDateArrivee(LocalDate.now());
        }

        // Par défaut, le véhicule est disponible
        vehicule.setDisponibilite(true);

        // Sauvegarder le véhicule
        vehiculeService.saveVehicule(vehicule);

        // Rediriger vers la page d'accueil
        return "redirect:/";
    }

    @GetMapping("/vehicule/add")
    public String showAddForm(Model model) {
        model.addAttribute("vehicule", new Vehicule());
        model.addAttribute("categories", categorieService.getAllCategories());
        return "vehicule-form";
    }

    // Méthode pour initialiser la base de données avec quelques catégories si nécessaire
    @GetMapping("/init-categories")
    public String initCategories() {
        // Vérifier si des catégories existent déjà
        if (categorieService.getAllCategories().isEmpty()) {
            // Créer quelques catégories de base
            Categorie berline = new Categorie();
            berline.setNom("Berline");
            berline.setDescription("Voiture à coffre séparé de l'habitacle");
            categorieService.saveCategorie(berline);

            Categorie suv = new Categorie();
            suv.setNom("SUV");
            suv.setDescription("Sport Utility Vehicle, véhicule utilitaire sport");
            categorieService.saveCategorie(suv);

            Categorie citadine = new Categorie();
            citadine.setNom("Citadine");
            citadine.setDescription("Petite voiture pour usage urbain");
            categorieService.saveCategorie(citadine);
        }

        return "redirect:/";
    }
}
