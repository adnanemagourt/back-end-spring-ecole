package com.example.back.services;


import com.example.back.DTO.MatiereDTO;
import com.example.back.entities.Matiere;

import java.util.List;

public interface MatiereService {
    boolean create(Matiere matiere);
    boolean update(Matiere matiere);
    List<MatiereDTO> readAll();
    MatiereDTO read(Integer id);
    boolean delete(Integer id);
    List<MatiereDTO> searchByNom(String nom);
}
