package com.example.back.services;

import com.example.back.DTO.DirecteurDTO;
import com.example.back.entities.Directeur;

public interface DirecteurService {
    boolean create(Directeur directeur);
    boolean update(Directeur directeur);
    Iterable<DirecteurDTO> readAll();
    DirecteurDTO read(Integer id);
    boolean delete(Integer id);
    Iterable<DirecteurDTO> searchByNom(String nom);
}
