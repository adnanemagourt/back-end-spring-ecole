package com.example.back.repository;

import com.example.back.DTO.ClasseDTO;
import com.example.back.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe, Integer> {

    @Override
    List<Classe> findAll();

    List<Classe> findByNomContains(String nom);
}
