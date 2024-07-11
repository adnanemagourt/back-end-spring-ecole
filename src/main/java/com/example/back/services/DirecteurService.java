package com.example.back.services;

import com.example.back.DTO.directeurDTO.DirecteurDTO;
import com.example.back.entities.Directeur;

import java.util.List;

public interface DirecteurService {
    DirecteurDTO create(Directeur directeur) throws Exception;

    boolean update(Directeur directeur) throws Exception;

    List<DirecteurDTO> readAll();

    DirecteurDTO read(Integer id) throws Exception;

    boolean delete(Integer id) throws Exception;

    List<DirecteurDTO> searchByNom(String nom);

    DirecteurDTO getByEmail(String email);

    DirecteurDTO getByNomAndPrenom(String nom, String prenom);
}
