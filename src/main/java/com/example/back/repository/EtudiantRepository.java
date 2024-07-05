package com.example.back.repository;

import com.example.back.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {

    Etudiant findEtudiantByEmail(String email);

    List<Etudiant> findBy();

    List<Etudiant> findByNomContains(String nom);

    List<Etudiant> findEtudiantsByClasse_Id(Integer classeId);

    @Query("SELECT e FROM Etudiant e " +
           "JOIN e.classe c " +
           "JOIN c.professeurs p " +
           "WHERE p.id = :id")
    List<Etudiant> getEtudiantsByProfesseurid(@Param("id") Integer id);

    Etudiant findByNomAndPrenom(String nom, String prenom);

    boolean existsByNomAndPrenom(String nom, String prenom);

    boolean existsByEmail(String email);
}
