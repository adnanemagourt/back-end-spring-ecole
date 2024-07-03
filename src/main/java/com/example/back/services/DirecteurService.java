package com.example.back.services;

import com.example.back.DTO.DirecteurDTO;
import com.example.back.entities.Directeur;

import java.util.List;

public interface DirecteurService {
    boolean create(Directeur directeur);
    boolean update(Directeur directeur);
    Iterable<DirecteurDTO> readAll();
    DirecteurDTO read(Integer id);
    boolean delete(Integer id);
    List<DirecteurDTO> searchByNom(String nom);
    DirecteurDTO getByEmail(String email);
}
