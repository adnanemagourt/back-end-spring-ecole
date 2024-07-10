package com.example.back.services;


import com.example.back.DTO.etudiantDTO.UnlinkedEtudiantDTO;
import com.example.back.DTO.matiereDTO.LinkedMatiereDTO;
import com.example.back.DTO.matiereDTO.UnlinkedMatiereDTO;
import com.example.back.DTO.professeurDTO.UnlinkedProfesseurDTO;
import com.example.back.exceptions.NotExistsException;

import java.util.List;

public interface MatiereService {
    LinkedMatiereDTO create(UnlinkedMatiereDTO matiere) throws Exception;

    void update(UnlinkedMatiereDTO matiere) throws Exception;

    List<UnlinkedMatiereDTO> readAll();

    LinkedMatiereDTO read(Integer id) throws Exception;

    boolean delete(Integer id) throws Exception;

    List<UnlinkedMatiereDTO> searchByNom(String nom);

    List<UnlinkedEtudiantDTO> getEtudiantsMatiere(Integer id) throws NotExistsException;

    List<UnlinkedProfesseurDTO> getProfesseursMatiere(Integer id) throws NotExistsException;

}
