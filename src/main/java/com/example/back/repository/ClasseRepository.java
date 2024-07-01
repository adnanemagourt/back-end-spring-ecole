package com.example.back.repository;

import com.example.back.entities.Classe;
import org.springframework.data.repository.CrudRepository;

public interface ClasseRepository extends CrudRepository<Classe, Integer>{

    void updateById(Integer id, Classe classe);

    Iterable<Classe> searchClassesByNom(String name);

}
