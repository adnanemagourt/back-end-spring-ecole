package com.example.back.services;

import com.example.back.DTO.ClasseDTO;
import com.example.back.entities.Classe;

import java.util.List;

public interface ClasseService {
    boolean create(Classe classe) throws Exception;
    List<ClasseDTO> readAll();
    ClasseDTO read(Integer id) throws Exception;
    boolean update(Classe classe) throws Exception;
    boolean delete(Integer id) throws Exception;
    List<ClasseDTO> searchByNom(String nom);
    ClasseDTO findByNom(String nom);
}
