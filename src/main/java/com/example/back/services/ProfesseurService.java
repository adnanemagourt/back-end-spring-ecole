package com.example.back.services;

import com.example.back.DTO.classeDTO.UnlinkedClasseDTO;
import com.example.back.DTO.matiereDTO.LinkedMatiereDTO;
import com.example.back.DTO.professeurDTO.LinkedProfesseurDTO;
import com.example.back.DTO.professeurDTO.UnlinkedProfesseurDTO;
import com.example.back.exceptions.NotExistsException;

import java.util.List;

public interface ProfesseurService {
    LinkedProfesseurDTO create(UnlinkedProfesseurDTO professeurdto) throws Exception;

    boolean update(UnlinkedProfesseurDTO professeurdto) throws Exception;

    List<UnlinkedProfesseurDTO> readAll();

    LinkedProfesseurDTO read(Integer id) throws Exception;

    boolean delete(Integer id) throws Exception;

    List<UnlinkedProfesseurDTO> searchByNom(String nom);

    List<UnlinkedProfesseurDTO> getByEtudiantId(Integer etudiantId) throws Exception;

    LinkedProfesseurDTO getByEmail(String email);

    List<UnlinkedClasseDTO> findProfesseurClasses(Integer id) throws NotExistsException;

    LinkedMatiereDTO findProfesseurMatiere(Integer id) throws NotExistsException;

}
