package com.example.back.services;

import com.example.back.DTO.ClasseDTO;
import com.example.back.DTO.EtudiantDTO;
import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Classe;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.NotExistsException;

import java.util.List;

public interface ClasseService {
    ClasseDTO create(Classe classe) throws Exception;
    List<ClasseDTO> readAll();
    Classe read(Integer id) throws Exception;
    void update(Classe classe) throws Exception;
    boolean delete(Integer id) throws Exception;
    List<ClasseDTO> searchByNom(String nom);
    Classe findByNom(String nom);

    List<ClasseDTO> findByProfesseurId(Integer professeurId) throws NotExistsException;

    List<ProfesseurDTO> getProfesseursClasse(Integer id) throws NotExistsException;
    boolean linkProfesseurs(Integer matiereId, List<Integer> professeurs) throws NotExistsException, AlreadyExistsException;
    boolean unlinkProfesseurs(Integer matiereId, List<Integer> professeurs) throws NotExistsException, AlreadyExistsException;

    Classe findByEtudiantId(Integer id) throws NotExistsException;

    List<EtudiantDTO> getEtudiantsClasse(Integer id) throws NotExistsException;
    boolean linkEtudiants(Integer matiereId, List<Integer> etudiants) throws NotExistsException, AlreadyExistsException;
    boolean unlinkEtudiants(Integer matiereId, List<Integer> etudiants) throws NotExistsException, AlreadyExistsException;
}
