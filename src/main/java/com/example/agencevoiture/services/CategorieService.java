package com.example.agencevoiture.services;

import com.example.agencevoiture.entités.Categorie;
import com.example.agencevoiture.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Spring pour la gestion des catégories de véhicules.
 * Fournit des méthodes pour récupérer, créer, modifier et supprimer des catégories.
 */
@Service
public class CategorieService {

    /** Référentiel Spring Data JPA pour l'accès aux données des catégories. */
    @Autowired
    private CategorieRepository categorieRepository;

    /**
     * Récupère toutes les catégories disponibles en base de données.
     *
     * @return une liste de toutes les catégories
     */
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    /**
     * Récupère une catégorie par son identifiant.
     *
     * @param id l'identifiant de la catégorie
     * @return un Optional contenant la catégorie si trouvée, vide sinon
     */
    public Optional<Categorie> getCategorieById(Long id) {
        return categorieRepository.findById(id);
    }

    /**
     * Enregistre ou met à jour une catégorie.
     *
     * @param categorie la catégorie à sauvegarder
     * @return la catégorie enregistrée
     */
    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    /**
     * Supprime une catégorie à partir de son identifiant.
     *
     * @param id l'identifiant de la catégorie à supprimer
     */
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
}
