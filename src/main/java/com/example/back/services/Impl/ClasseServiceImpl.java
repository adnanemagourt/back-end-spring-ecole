package com.example.back.services.Impl;

import com.example.back.DTO.ClasseDTO;
import com.example.back.DTO.DTOListMapper;
import com.example.back.entities.Classe;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.EmptyFieldsException;
import com.example.back.exceptions.NotExistsException;
import com.example.back.repository.ClasseRepository;
import com.example.back.services.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseServiceImpl implements ClasseService {

    @Autowired
    private ClasseRepository classeRepository;

    @Override
    public ClasseDTO create(Classe classe) throws Exception {
         if(saveCheck(classe)) classeRepository.save(classe);
         return new ClasseDTO(classe);
    }
    private boolean saveCheck(Classe classe) throws Exception {
        if(classe.getNom() == null || classe.getNom().isEmpty()){
            throw new EmptyFieldsException(List.of("nom"), "classe");
        }
        boolean existsbyNom = classeRepository.existsByNom(classe.getNom());
        if (existsbyNom) {
            throw new AlreadyExistsException(List.of("nom"), "classe");
        }
        return true;
    }

    @Override
    public List<ClasseDTO> readAll() {
        return DTOListMapper.mapClasse(classeRepository.findAll());
    }

    @Override
    public Classe read(Integer id) throws Exception {
        Classe classe = classeRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of("id"), "classe"));
        return classe;
    }

    @Override
    public void update(Classe classe) throws Exception {
        Classe classe1 = classeRepository.findById(classe.getId()).orElseThrow(() -> new NotExistsException(List.of("id"), "classe"));
        saveCheck(classe1);
        classe1.setNom(classe.getNom());
        classeRepository.save(classe1);
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if(!classeRepository.existsById(id)){
            throw new NotExistsException(List.of("id"), "classe");
        }
        classeRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ClasseDTO> searchByNom(String nom) {
        return DTOListMapper.mapClasse(classeRepository.findByNomContains(nom));
    }

    @Override
    public Classe findByNom(String nom) {
        return classeRepository.findByNom(nom);
    }
}
