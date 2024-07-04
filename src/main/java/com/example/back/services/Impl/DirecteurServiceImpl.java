package com.example.back.services.Impl;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.DirecteurDTO;
import com.example.back.entities.Directeur;
import com.example.back.repository.DirecteurRepository;
import com.example.back.services.DirecteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirecteurServiceImpl implements DirecteurService {

    @Autowired
    private DirecteurRepository directeurRepository;

    @Override
    public boolean create(Directeur directeur) throws Exception {
        return saveCheck(directeur);
    }

    private boolean saveCheck(Directeur directeur) throws Exception {
        if(directeur.getNom() == null || directeur.getNom().isEmpty() || directeur.getPrenom() == null || directeur.getPrenom().isEmpty() || directeur.getEmail() == null || directeur.getEmail().isEmpty() || directeur.getMotDePasse() == null || directeur.getMotDePasse().isEmpty()){
            throw new Exception("important fields are empty");
        }
        if(directeurRepository.findDirecteurByEmail(directeur.getEmail()) != null){
            throw new Exception("already existing email");
        }
        if(directeurRepository.findByNomAndPrenom(directeur.getNom(), directeur.getPrenom()) != null){
            throw new Exception("already existing nom & prénom");
        }
        directeurRepository.save(directeur);
        return true;
    }

    @Override
    public List<DirecteurDTO> readAll() {
        return DTOListMapper.mapDirecteur(directeurRepository.findBy());
    }

    @Override
    public DirecteurDTO read(Integer id) throws Exception {
        Optional<Directeur> t = directeurRepository.findById(id);
        return new DirecteurDTO(t.orElseThrow(()->new Exception("id not found")));
    }

    @Override
    public boolean update(Directeur directeur) throws Exception {
        Directeur old = directeurRepository.findById(directeur.getId()).orElseThrow(()->new Exception("id not found"));
        if(!old.getNom().equals(directeur.getNom()) || !old.getPrenom().equals(directeur.getPrenom())){
            throw new Exception("can't update nom and prenom");
        }
        return saveCheck(directeur);
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if(!directeurRepository.existsById(id)){
            throw new Exception("id not found");
        }
        directeurRepository.deleteById(id);
        return true;
    }

    @Override
    public List<DirecteurDTO> searchByNom(String nom) {
        return DTOListMapper.mapDirecteur(directeurRepository.findByNomContains(nom));
    }

    @Override
    public DirecteurDTO getByEmail(String email) {
        return new DirecteurDTO(directeurRepository.findDirecteurByEmail(email));
    }

    @Override
    public DirecteurDTO getByNomAndPrenom(String nom, String prenom) {
        return new DirecteurDTO(directeurRepository.findByNomAndPrenom(nom, prenom));
    }
}
