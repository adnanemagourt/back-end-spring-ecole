package com.example.back.services;

import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Professeur;

public interface ProfesseurService {
    boolean create(Professeur professeur);
    boolean update(Professeur professeur);
    Iterable<ProfesseurDTO> readAll();
    ProfesseurDTO read(Integer id);
    boolean delete(Integer id);
    Iterable<ProfesseurDTO> searchByNom(String nom);
    Iterable<ProfesseurDTO> getByEtudiantId(Integer etudiantId);
}
