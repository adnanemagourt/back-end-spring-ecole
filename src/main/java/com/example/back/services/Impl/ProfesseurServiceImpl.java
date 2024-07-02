package com.example.back.services.Impl;

import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Professeur;
import com.example.back.repository.ProfesseurRepository;
import com.example.back.services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfesseurServiceImpl implements ProfesseurService {

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Override
    public boolean create(Professeur professeur) {
        if(professeurRepository.findProfesseurByEmail(professeur.getEmail()) != null){
            return false;
        }
        professeurRepository.save(professeur);
        return true;
    }

    @Override
    public Iterable<ProfesseurDTO> readAll() {
        return professeurRepository.findBy();
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
    public Iterable<ProfesseurDTO> searchByNom(String nom) {
        return professeurRepository.searchProfesseursByNom(nom);
    }

    @Override
    public Iterable<ProfesseurDTO> getByEtudiantId(Integer etudiantId) {
        return professeurRepository.getProfesseursByEtudiantId(etudiantId);
    }
}
