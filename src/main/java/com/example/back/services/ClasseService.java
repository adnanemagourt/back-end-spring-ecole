package com.example.back.services;

import com.example.back.DTO.classeDTO.LinkedClasseDTO;
import com.example.back.DTO.classeDTO.UnlinkedClasseDTO;
import com.example.back.DTO.etudiantDTO.UnlinkedEtudiantDTO;
import com.example.back.DTO.professeurDTO.UnlinkedProfesseurDTO;
import com.example.back.exceptions.NotExistsException;

import java.util.List;

public interface ClasseService {
    LinkedClasseDTO create(UnlinkedClasseDTO classe) throws Exception;

    List<UnlinkedClasseDTO> readAll();

    LinkedClasseDTO read(Integer id) throws Exception;

    void update(UnlinkedClasseDTO classe) throws Exception;

    boolean delete(Integer id) throws Exception;

    List<UnlinkedClasseDTO> searchByNom(String nom);

    LinkedClasseDTO findByNom(String nom);

    List<UnlinkedClasseDTO> findByProfesseurId(Integer professeurId) throws NotExistsException;

    List<UnlinkedProfesseurDTO> getProfesseursClasse(Integer id) throws NotExistsException;

    LinkedClasseDTO findByEtudiantId(Integer id) throws NotExistsException;

    List<UnlinkedEtudiantDTO> getEtudiantsClasse(Integer id) throws NotExistsException;
}
