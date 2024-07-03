package com.example.back.services;

import com.example.back.DTO.ClasseDTO;
import com.example.back.entities.Classe;

import java.util.List;

public interface ClasseService {
    boolean create(Classe classe);
    List<ClasseDTO> readAll();
    ClasseDTO read(Integer id);
    boolean update(Classe classe);
    boolean delete(Integer id);
    List<ClasseDTO> searchByNom(String nom);
}
