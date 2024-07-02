package com.example.back.repository;

import com.example.back.DTO.MatiereDTO;
import com.example.back.entities.Matiere;
import org.springframework.data.repository.CrudRepository;

public interface MatiereRepository extends CrudRepository<Matiere, Integer> {

    Iterable<MatiereDTO> searchMatieresByNom(String name);

    Iterable<MatiereDTO> findBy();

}
