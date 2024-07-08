package com.example.back.services;


import com.example.back.DTO.EtudiantDTO;
import com.example.back.DTO.MatiereDTO;
import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Matiere;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.NotExistsException;

import java.util.List;

public interface MatiereService {
    MatiereDTO create(Matiere matiere) throws Exception;
    boolean update(Matiere matiere) throws Exception;
    List<MatiereDTO> readAll();
    Matiere read(Integer id) throws Exception;
    boolean delete(Integer id) throws Exception;
    List<MatiereDTO> searchByNom(String nom);

    List<EtudiantDTO> getEtudiantsMatiere(Integer id) throws NotExistsException;
    boolean linkEtudiants(Integer matiereId, List<Integer> etudiants) throws AlreadyExistsException, NotExistsException;
    boolean unlinkEtudiants(Integer matiereId, List<Integer> etudiants) throws NotExistsException;

    List<ProfesseurDTO> getProfesseursMatiere(Integer id) throws NotExistsException;
    boolean linkProfesseurs(Integer matiereId, List<Integer> professeurs) throws AlreadyExistsException, NotExistsException;
    boolean unlinkProfesseurs(Integer matiereId, List<Integer> professeurs) throws NotExistsException;

}
