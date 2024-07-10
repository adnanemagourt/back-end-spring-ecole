package com.example.back.services;

import com.example.back.DTO.classeDTO.LinkedClasseDTO;
import com.example.back.DTO.etudiantDTO.LinkedEtudiantDTO;
import com.example.back.DTO.etudiantDTO.UnlinkedEtudiantDTO;
import com.example.back.DTO.matiereDTO.UnlinkedMatiereDTO;
import com.example.back.exceptions.NotExistsException;

import java.util.List;

public interface EtudiantService {
    LinkedEtudiantDTO create(UnlinkedEtudiantDTO etudiantdto) throws Exception;

    boolean update(UnlinkedEtudiantDTO etudiantdto) throws Exception;

    List<UnlinkedEtudiantDTO> readAll();

    LinkedEtudiantDTO read(Integer id) throws Exception;

    boolean delete(Integer id) throws Exception;

    List<UnlinkedEtudiantDTO> searchByNom(String nom);

    List<UnlinkedEtudiantDTO> getByClasseId(Integer classeId) throws Exception;

    List<UnlinkedEtudiantDTO> getByProfesseurId(Integer professeurId) throws Exception;

    LinkedEtudiantDTO getByEmail(String email);

    List<UnlinkedMatiereDTO> getEtudiantMatieres(Integer id) throws NotExistsException;

    LinkedClasseDTO getClasseOfEtudiant(Integer id) throws NotExistsException;

}
