package com.example.back.repository;

import com.example.back.DTO.DirecteurDTO;
import com.example.back.entities.Directeur;
import org.springframework.data.repository.CrudRepository;

public interface DirecteurRepository extends CrudRepository<Directeur, Integer> {

    DirecteurDTO findDirecteurByEmail(String email);

    Iterable<DirecteurDTO> searchDirecteursByNom(String name);

    Iterable<DirecteurDTO> findBy();
}
