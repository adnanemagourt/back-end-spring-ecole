package com.example.back.services.Impl;

import com.example.back.DTO.EtudiantDTO;
import com.example.back.entities.Etudiant;
import com.example.back.repository.EtudiantRepository;
import com.example.back.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public boolean create(Etudiant etudiant) {
        if(etudiantRepository.findEtudiantByEmail(etudiant.getEmail()) != null){
            return false;
        }
        etudiantRepository.save(etudiant);
        return true;
    }

    @Override
    public Iterable<EtudiantDTO> readAll() {
        return etudiantRepository.findBy();
    }

    @Override
    public EtudiantDTO read(Integer id) {
        Optional<Etudiant> t = etudiantRepository.findById(id);
        return new EtudiantDTO(t.orElse(null));
    }

    @Override
    public boolean update(Etudiant etudiant) {
        etudiantRepository.save(etudiant);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        etudiantRepository.deleteById(id);
        return true;
    }

    @Override
    public Iterable<EtudiantDTO> searchByNom(String nom) {
        Iterable<EtudiantDTO> etudiants = etudiantRepository.searchEtudiantsByNom(nom);
        return etudiants;
    }

    @Override
    public Iterable<EtudiantDTO> getByClasseId(Integer classeId) {
        Iterable<EtudiantDTO> etudiants = etudiantRepository.findEtudiantsByClasse_Id(classeId);
        return etudiants;
    }

    @Override
    public Iterable<EtudiantDTO> getByProfesseurId(Integer professeurId) {
        Iterable<EtudiantDTO> etudiants = etudiantRepository.getEtudiantsByProfesseurid(professeurId);
        return etudiants;
    }
}
