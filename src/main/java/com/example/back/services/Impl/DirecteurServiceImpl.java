package com.example.back.services.Impl;

import com.example.back.DTO.DirecteurDTO;
import com.example.back.entities.Directeur;
import com.example.back.repository.DirecteurRepository;
import com.example.back.services.DirecteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DirecteurServiceImpl implements DirecteurService {

    @Autowired
    private DirecteurRepository directeurRepository;

    @Override
    public boolean create(Directeur directeur) {
        if(directeurRepository.findDirecteurByEmail(directeur.getEmail()) != null){
            return false;
        }
        directeurRepository.save(directeur);
        return true;
    }

    @Override
    public Iterable<DirecteurDTO> readAll() {
        return directeurRepository.findBy();
    }

    @Override
    public DirecteurDTO read(Integer id) {
        Optional<Directeur> t = directeurRepository.findById(id);
        return new DirecteurDTO(t.orElse(null));
    }

    @Override
    public boolean update(Directeur diecteur) {
        directeurRepository.save(diecteur);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        directeurRepository.deleteById(id);
        return true;
    }

    @Override
    public Iterable<DirecteurDTO> searchByNom(String nom) {
        Iterable<DirecteurDTO> directeurs = directeurRepository.searchDirecteursByNom(nom);
        return directeurs;
    }
}
