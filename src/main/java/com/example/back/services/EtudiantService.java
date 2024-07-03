package com.example.back.services;

import com.example.back.DTO.EtudiantDTO;
import com.example.back.entities.Etudiant;

import java.util.List;

public interface EtudiantService {
    boolean create(Etudiant etudiant);
    boolean update(Etudiant etudiant);
    List<EtudiantDTO> readAll();
    EtudiantDTO read(Integer id);
    boolean delete(Integer id);
    List<EtudiantDTO> searchByNom(String nom);
    List<EtudiantDTO> getByClasseId(Integer classeId);
    List<EtudiantDTO> getByProfesseurId(Integer professeurId);

    EtudiantDTO getByEmail(String email);
}
