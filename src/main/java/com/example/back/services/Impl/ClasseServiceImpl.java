package com.example.back.services.Impl;

import com.example.back.DTO.ClasseDTO;
import com.example.back.DTO.DTOListMapper;
import com.example.back.entities.Classe;
import com.example.back.repository.ClasseRepository;
import com.example.back.services.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseServiceImpl implements ClasseService {

    @Autowired
    private ClasseRepository classeRepository;

    @Override
    public boolean create(Classe classe) throws Exception {
        return saveCheck(classe);
    }

    private boolean saveCheck(Classe classe) throws Exception {
        if(classe.getNom() == null || classe.getNom().isEmpty()){
            throw new Exception("important field empty");
        }
        Classe found = classeRepository.findByNom(classe.getNom());
        if (found != null) {
            throw new Exception("classe name already exists");
        }
        classeRepository.save(classe);
        return true;
    }

    @Override
    public List<ClasseDTO> readAll() {
        return DTOListMapper.mapClasse(classeRepository.findAll());
    }

    @Override
    public ClasseDTO read(Integer id) throws Exception {
        Optional<Classe> t = classeRepository.findById(id);
        return new ClasseDTO(t.orElseThrow(() -> new Exception("Classe id does not exist")));
    }

    @Override
    public boolean update(Classe classe) throws Exception {
        if(!classeRepository.existsById(classe.getId())){
            throw new Exception("Classe id does not exist");
        }
        return saveCheck(classe);
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if(!classeRepository.existsById(id)){
            throw new Exception("Classe id does not exist");
        }
        classeRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ClasseDTO> searchByNom(String nom) {
        return DTOListMapper.mapClasse(classeRepository.findByNomContains(nom));
    }

    @Override
    public ClasseDTO findByNom(String nom) {
        return new ClasseDTO(classeRepository.findByNom(nom));
    }
}
