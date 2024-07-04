package com.example.back.services;

import com.example.back.DTO.ProfesseurDTO;

import java.util.List;

public interface ProfesseurService {
    boolean create(ProfesseurDTO professeurdto) throws Exception;
    boolean update(ProfesseurDTO professeurdto) throws Exception;
    List<ProfesseurDTO> readAll();
    ProfesseurDTO read(Integer id) throws Exception;
    boolean delete(Integer id) throws Exception;
    List<ProfesseurDTO> searchByNom(String nom);
    List<ProfesseurDTO> getByEtudiantId(Integer etudiantId) throws Exception;
    ProfesseurDTO getByEmail(String email);
}
