package com.example.back.repository;

import com.example.back.entities.Matiere;
import org.springframework.data.repository.CrudRepository;

public interface MatiereRepository extends CrudRepository<Matiere, Integer> {
    void updateById(Integer id, Matiere matiere);

    Iterable<Matiere> searchMatieresByNom(String name);
}
