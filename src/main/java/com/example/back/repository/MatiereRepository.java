package com.example.back.repository;

import com.example.back.DTO.MatiereDTO;
import com.example.back.entities.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatiereRepository extends JpaRepository<Matiere, Integer> {

    List<Matiere> findByNomContains(String name);

    List<Matiere> findBy();

}
