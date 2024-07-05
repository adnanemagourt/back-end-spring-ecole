package com.example.back.services;

import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Classe;

import java.util.List;

public interface ProfesseurService {
    ProfesseurDTO create(ProfesseurDTO professeurdto) throws Exception;
    boolean update(ProfesseurDTO professeurdto) throws Exception;
    List<ProfesseurDTO> readAll();
    ProfesseurDTO read(Integer id) throws Exception;
    boolean delete(Integer id) throws Exception;
    List<ProfesseurDTO> searchByNom(String nom);
    List<ProfesseurDTO> getByEtudiantId(Integer etudiantId) throws Exception;
    ProfesseurDTO getByEmail(String email);

    List<Classe> findProfesseurClasses(Integer id);
}
