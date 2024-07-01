package com.example.back.repository;

import com.example.back.entities.Directeur;
import org.springframework.data.repository.CrudRepository;

public interface DirecteurRepository extends CrudRepository<Directeur, Integer> {
    Directeur findDirecteurByEmail(String email);

    void updateById(Integer id, Directeur directeur);

    Iterable<Directeur> searchDirecteursByNom(String name);
}
