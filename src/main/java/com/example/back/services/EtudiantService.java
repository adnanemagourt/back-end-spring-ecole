package com.example.back.services;

import com.example.back.DTO.ClasseDTO;
import com.example.back.DTO.EtudiantDTO;
import com.example.back.DTO.MatiereDTO;
import com.example.back.entities.Etudiant;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.NotExistsException;

import java.util.List;

public interface EtudiantService {
    EtudiantDTO create(EtudiantDTO etudiantdto) throws Exception;
    boolean update(EtudiantDTO etudiantdto) throws Exception;
    List<EtudiantDTO> readAll();
    Etudiant read(Integer id) throws Exception;
    boolean delete(Integer id) throws Exception;
    List<EtudiantDTO> searchByNom(String nom);
    List<EtudiantDTO> getByClasseId(Integer classeId) throws Exception;
    List<EtudiantDTO> getByProfesseurId(Integer professeurId) throws Exception;

    Etudiant getByEmail(String email);

    List<MatiereDTO> getEtudiantMatieres(Integer id) throws NotExistsException;
    boolean linkMatieres(Integer etudiantId, List<Integer> matieres) throws AlreadyExistsException, NotExistsException;
    boolean unlinkMatieres(Integer etudiantId, List<Integer> matieres) throws NotExistsException;

    ClasseDTO getClasseOfEtudiant(Integer id) throws NotExistsException;
    boolean linkClasse(Integer etudiantId, Integer classeId) throws NotExistsException, AlreadyExistsException;
    boolean unlinkClasse(Integer etudiantId) throws NotExistsException;
}
