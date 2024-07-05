package com.example.back.services;

import com.example.back.DTO.ClasseDTO;
import com.example.back.entities.Classe;

import java.util.List;

public interface ClasseService {
    ClasseDTO create(Classe classe) throws Exception;
    List<ClasseDTO> readAll();
    Classe read(Integer id) throws Exception;
    void update(Classe classe) throws Exception;
    boolean delete(Integer id) throws Exception;
    List<ClasseDTO> searchByNom(String nom);
    Classe findByNom(String nom);
}
