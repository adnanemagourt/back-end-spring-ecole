package com.example.back.repository;

import com.example.back.entities.Directeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirecteurRepository extends JpaRepository<Directeur, Integer> {

    Directeur findDirecteurByEmail(String email);

    List<Directeur> findByNomContains(String name);

    List<Directeur> findBy();

    Directeur findByNomAndPrenom(String nom, String prenom);

    boolean existsByEmail(String email);

    boolean existsByNomAndPrenom(String nom, String prenom);
}
