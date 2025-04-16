package com.example.agencevoiture.repositories;

import com.example.agencevoiture.entités.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
