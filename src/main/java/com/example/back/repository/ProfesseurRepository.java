package com.example.back.repository;

import com.example.back.entities.Professeur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProfesseurRepository extends CrudRepository<Professeur, Integer> {
    Professeur findProfesseurByEmail(String email);

    void updateById(Integer id, Professeur professeur);

    Iterable<Professeur> searchProfesseursByNom(String name);

    @Query("SELECT p FROM Professeur p " +
           "JOIN p.classes c " +
           "JOIN c.etudiants e " +
           "WHERE e.id = :id")
    Iterable<Professeur> getProfesseursByEtudiantId(@Param("id") Integer id);



}
