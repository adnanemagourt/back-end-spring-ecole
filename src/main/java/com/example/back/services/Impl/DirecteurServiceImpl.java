package com.example.back.services.Impl;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.DirecteurDTO;
import com.example.back.entities.Directeur;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.EmptyFieldsException;
import com.example.back.exceptions.NotExistsException;
import com.example.back.exceptions.UnAuthorizedUpdateException;
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
    public DirecteurDTO create(Directeur directeur) throws Exception {
        if(saveCheck(directeur)) directeurRepository.save(directeur);
        return new DirecteurDTO(directeur);
    }

    private boolean saveCheck(Directeur directeur) throws Exception {
        List<String> emptyFields = getEmptyFields(directeur);
        if(!emptyFields.isEmpty()){
            throw new EmptyFieldsException(emptyFields, "directeur");
        }
        if(directeurRepository.existsByEmail(directeur.getEmail())){
            throw new AlreadyExistsException(List.of("email"), "directeur");
        }
        if(directeurRepository.existsByNomAndPrenom(directeur.getNom(), directeur.getPrenom())){
            throw new AlreadyExistsException(List.of("nom", "prenom"), "directeur");
        }
        return true;
    }

    private static List<String> getEmptyFields(Directeur directeur) {
        List<String> emptyFields = new java.util.ArrayList<>(List.of());
        if(directeur.getNom() == null || directeur.getNom().isEmpty()){
            emptyFields.add("nom");
        }
        if(directeur.getPrenom() == null || directeur.getPrenom().isEmpty()){
            emptyFields.add("prenom");
        }
        if(directeur.getEmail() == null || directeur.getEmail().isEmpty()){
            emptyFields.add("email");
        }
        if(directeur.getMotDePasse() == null || directeur.getMotDePasse().isEmpty()){
            emptyFields.add("motDePasse");
        }
        return emptyFields;
    }

    @Override
    public List<DirecteurDTO> readAll() {
        return DTOListMapper.mapDirecteur(directeurRepository.findBy());
    }

    @Override
    public DirecteurDTO read(Integer id) throws Exception {
        Optional<Directeur> t = directeurRepository.findById(id);
        return new DirecteurDTO(t.orElseThrow(()->new NotExistsException(List.of("id"), "directeur")));
    }

    @Override
    public boolean update(Directeur directeur) throws Exception {
        Directeur old = directeurRepository.findById(directeur.getId()).orElseThrow(()->new NotExistsException(List.of("id"), "directeur"));
        if(!old.getNom().equals(directeur.getNom()) || !old.getPrenom().equals(directeur.getPrenom())){
            throw new UnAuthorizedUpdateException(List.of("nom", "prenom"), "directeur");
        }
        old.setAdresse(directeur.getAdresse());
        old.setEmail(directeur.getEmail());
        old.setNom(directeur.getNom());
        old.setPrenom(directeur.getPrenom());
        old.setMotDePasse(directeur.getMotDePasse());
        old.setTelephone(directeur.getTelephone());
        if(saveCheck(directeur)){
            directeurRepository.save(old);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if(!directeurRepository.existsById(id)){
            throw new NotExistsException(List.of("id"), "directeur");
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
