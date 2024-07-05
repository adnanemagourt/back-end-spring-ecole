package com.example.back.services;

import com.example.back.DTO.EtudiantDTO;

import java.util.List;

public interface EtudiantService {
    EtudiantDTO create(EtudiantDTO etudiantdto) throws Exception;
    boolean update(EtudiantDTO etudiantdto) throws Exception;
    List<EtudiantDTO> readAll();
    EtudiantDTO read(Integer id) throws Exception;
    boolean delete(Integer id) throws Exception;
    List<EtudiantDTO> searchByNom(String nom);
    List<EtudiantDTO> getByClasseId(Integer classeId) throws Exception;
    List<EtudiantDTO> getByProfesseurId(Integer professeurId) throws Exception;

    EtudiantDTO getByEmail(String email);
}
