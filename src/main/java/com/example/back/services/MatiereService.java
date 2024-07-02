package com.example.back.services;


import com.example.back.DTO.MatiereDTO;
import com.example.back.entities.Matiere;

public interface MatiereService {
    boolean create(Matiere matiere);
    boolean update(Matiere matiere);
    Iterable<MatiereDTO> readAll();
    MatiereDTO read(Integer id);
    boolean delete(Integer id);
    Iterable<MatiereDTO> searchByNom(String nom);
}
