package com.example.back.repository;

import com.example.back.DTO.ClasseDTO;
import com.example.back.entities.Classe;
import org.springframework.data.repository.CrudRepository;

public interface ClasseRepository extends CrudRepository<Classe, Integer>{

    Iterable<ClasseDTO> searchClassesByNom(String name);

    Iterable<ClasseDTO> findBy();

}
