package com.example.back.services;

import com.example.back.DTO.EtudiantDTO;
import com.example.back.entities.Etudiant;

public interface EtudiantService {
    boolean create(Etudiant etudiant);
    boolean update(Etudiant etudiant);
    Iterable<EtudiantDTO> readAll();
    EtudiantDTO read(Integer id);
    boolean delete(Integer id);
    Iterable<EtudiantDTO> searchByNom(String nom);
    Iterable<EtudiantDTO> getByClasseId(Integer classeId);
    Iterable<EtudiantDTO> getByProfesseurId(Integer professeurId);
}
