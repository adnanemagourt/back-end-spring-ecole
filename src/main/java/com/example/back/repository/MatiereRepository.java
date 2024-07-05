package com.example.back.repository;

import com.example.back.entities.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatiereRepository extends JpaRepository<Matiere, Integer> {

    List<Matiere> findByNomContains(String name);

    Matiere findByNom(String nom);

    boolean existsByNom(String nom);

}
