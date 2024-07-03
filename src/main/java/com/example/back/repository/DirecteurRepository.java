package com.example.back.repository;

import com.example.back.DTO.DirecteurDTO;
import com.example.back.entities.Directeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DirecteurRepository extends JpaRepository<Directeur, Integer> {

    Directeur findDirecteurByEmail(String email);

    List<Directeur> findByNomContains(String name);

    List<Directeur> findBy();
}
