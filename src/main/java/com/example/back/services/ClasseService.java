package com.example.back.services;

import com.example.back.DTO.ClasseDTO;
import com.example.back.entities.Classe;

public interface ClasseService {
    boolean create(Classe classe);
    Iterable<ClasseDTO> readAll();
    ClasseDTO read(Integer id);
    boolean update(Classe classe);
    boolean delete(Integer id);
    Iterable<ClasseDTO> searchByNom(String nom);
}
