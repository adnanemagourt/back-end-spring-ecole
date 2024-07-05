package com.example.back.services;


import com.example.back.DTO.MatiereDTO;
import com.example.back.entities.Matiere;

import java.util.List;

public interface MatiereService {
    MatiereDTO create(Matiere matiere) throws Exception;
    boolean update(Matiere matiere) throws Exception;
    List<MatiereDTO> readAll();
    MatiereDTO read(Integer id) throws Exception;
    boolean delete(Integer id) throws Exception;
    List<MatiereDTO> searchByNom(String nom);
}
