package com.example.back.repository;

import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Professeur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProfesseurRepository extends CrudRepository<Professeur, Integer> {

    ProfesseurDTO findProfesseurByEmail(String email);

    Iterable<ProfesseurDTO> findBy();

    Iterable<ProfesseurDTO> searchProfesseursByNom(String name);

    @Query("SELECT p FROM Professeur p " +
           "JOIN p.classes c " +
           "JOIN c.etudiants e " +
           "WHERE e.id = :id")
    Iterable<ProfesseurDTO> getProfesseursByEtudiantId(@Param("id") Integer id);

}
