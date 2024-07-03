package com.example.back.services;

import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Professeur;

import java.util.List;

public interface ProfesseurService {
    boolean create(Professeur professeur);
    boolean update(Professeur professeur);
    List<ProfesseurDTO> readAll();
    ProfesseurDTO read(Integer id);
    boolean delete(Integer id);
    List<ProfesseurDTO> searchByNom(String nom);
    List<ProfesseurDTO> getByEtudiantId(Integer etudiantId);
    ProfesseurDTO getByEmail(String email);
}
