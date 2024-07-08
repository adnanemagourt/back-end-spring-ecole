package com.example.back.services;

import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Classe;
import com.example.back.entities.Matiere;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.NotExistsException;

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

    List<Classe> findProfesseurClasses(Integer id) throws NotExistsException;
    boolean linkClasses(Integer professeurId, List<Integer> classes) throws NotExistsException, AlreadyExistsException;
    boolean unlinkClasses(Integer professeurId, List<Integer> classes) throws NotExistsException, AlreadyExistsException;

    Matiere findProfesseurMatiere(Integer id) throws NotExistsException;
    boolean linkMatiere(Integer professeurId, Integer matiereId) throws NotExistsException, AlreadyExistsException;
    boolean unlinkMatiere(Integer professeurId) throws NotExistsException;
}
