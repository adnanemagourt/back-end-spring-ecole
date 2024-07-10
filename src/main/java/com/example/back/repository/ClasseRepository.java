package com.example.back.repository;

import com.example.back.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe, Integer> {

    @Override
    List<Classe> findAll();

    List<Classe> findByNomContains(String nom);

    boolean existsByNom(String nom);

    Classe findByNom(String nom);
}
