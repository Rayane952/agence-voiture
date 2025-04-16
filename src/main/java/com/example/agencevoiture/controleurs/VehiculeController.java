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

/**
 * Contrôleur Spring MVC pour la gestion des vues liées aux véhicules.
 * Permet d'afficher la page d'accueil, les détails d'un véhicule, le formulaire d'ajout
 * ainsi que l'initialisation des catégories de véhicules.
 */
@Controller
public class VehiculeController {

    /** Service pour gérer les véhicules. */
    @Autowired
    private VehiculeService vehiculeService;

    /** Service pour gérer les catégories de véhicules. */
    @Autowired
    private CategorieService categorieService;

    /**
     * Affiche la page d'accueil avec la liste des véhicules et le formulaire d'ajout.
     *
     * @param model le modèle Spring contenant les données à afficher dans la vue
     * @return le nom de la vue "index"
     */
    @GetMapping("/")
    public String home(Model model) {
        List<Vehicule> vehicules = vehiculeService.getAllVehicules();
        model.addAttribute("vehicules", vehicules);

        Vehicule vehicule = new Vehicule();
        vehicule.setDateArrivee(LocalDate.now());
        model.addAttribute("vehicule", vehicule);

        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("categories", categories);

        return "index";
    }

    /**
     * Affiche les détails d’un véhicule selon son ID.
     *
     * @param id l'identifiant du véhicule
     * @param model le modèle contenant les données à afficher
     * @return le nom de la vue "vehicule-details"
     * @throws IllegalArgumentException si le véhicule n’est pas trouvé
     */
    @GetMapping("/vehicule/{id}")
    public String vehiculeDetails(@PathVariable Long id, Model model) {
        Vehicule vehicule = vehiculeService.getVehiculeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Véhicule non trouvé avec l'ID: " + id));
        model.addAttribute("vehicule", vehicule);
        return "vehicule-details";
    }

    /**
     * Enregistre un nouveau véhicule soumis via le formulaire.
     *
     * @param vehicule le véhicule à ajouter
     * @return une redirection vers la page d'accueil
     */
    @PostMapping("/vehicule/add")
    public String addVehicule(@ModelAttribute Vehicule vehicule) {
        if (vehicule.getDateArrivee() == null) {
            vehicule.setDateArrivee(LocalDate.now());
        }

        vehicule.setDisponibilite(true);
        vehiculeService.saveVehicule(vehicule);
        return "redirect:/";
    }

    /**
     * Affiche le formulaire d’ajout de véhicule.
     *
     * @param model le modèle contenant un véhicule vide et la liste des catégories
     * @return le nom de la vue "vehicule-form"
     */
    @GetMapping("/vehicule/add")
    public String showAddForm(Model model) {
        model.addAttribute("vehicule", new Vehicule());
        model.addAttribute("categories", categorieService.getAllCategories());
        return "vehicule-form";
    }

    /**
     * Initialise des catégories par défaut si aucune n’existe encore.
     *
     * @return une redirection vers la page d'accueil
     */
    @GetMapping("/init-categories")
    public String initCategories() {
        if (categorieService.getAllCategories().isEmpty()) {
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
