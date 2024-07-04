package com.example.back.repository;

import com.example.back.entities.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProfesseurRepository extends JpaRepository<Professeur, Integer> {

    Professeur findProfesseurByEmail(String email);

    List<Professeur> findBy();

    List<Professeur> findByNomContains(String name);

    Professeur findByNomAndPrenom(String nom, String prenom);

    @Query("SELECT p FROM Professeur p " +
           "JOIN p.classes c " +
           "JOIN c.etudiants e " +
           "WHERE e.id = :id")
    List<Professeur> getProfesseursByEtudiantId(@Param("id") Integer id);

}
