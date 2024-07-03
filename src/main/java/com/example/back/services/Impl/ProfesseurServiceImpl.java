package com.example.back.services.Impl;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Professeur;
import com.example.back.repository.MatiereRepository;
import com.example.back.repository.ProfesseurRepository;
import com.example.back.services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesseurServiceImpl implements ProfesseurService {

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Override
    public boolean create(Professeur professeur) {
        matiereRepository.findById(professeur.getMatiere().getId()).orElseThrow();
        Professeur.builder().nom(professeur.getNom()).build();
        if(professeurRepository.findProfesseurByEmail(professeur.getEmail()) != null){
            return false;
        }
        professeurRepository.save(professeur);
        return true;
    }

    @Override
    public List<ProfesseurDTO> readAll() {
        return DTOListMapper.mapProfesseur(professeurRepository.findBy());
    }

    @Override
    public ProfesseurDTO read(Integer id) {
        Optional<Professeur> t = professeurRepository.findById(id);
        return new ProfesseurDTO(t.orElse(null));
    }

    @Override
    public boolean update(Professeur professeur) {
        professeurRepository.save(professeur);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        professeurRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ProfesseurDTO> searchByNom(String nom) {
        return DTOListMapper.mapProfesseur(professeurRepository.findByNomContains(nom));
    }

    @Override
    public List<ProfesseurDTO> getByEtudiantId(Integer etudiantId) {
        return DTOListMapper.mapProfesseur(professeurRepository.getProfesseursByEtudiantId(etudiantId));
    }

    @Override
    public ProfesseurDTO getByEmail(String email) {
        return new ProfesseurDTO(professeurRepository.findProfesseurByEmail(email));
    }
}
