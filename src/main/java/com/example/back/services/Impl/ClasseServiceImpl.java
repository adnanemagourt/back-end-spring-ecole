package com.example.back.services.Impl;

import com.example.back.DTO.ClasseDTO;
import com.example.back.DTO.DTOListMapper;
import com.example.back.entities.Classe;
import com.example.back.repository.ClasseRepository;
import com.example.back.services.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClasseServiceImpl implements ClasseService {

    @Autowired
    private ClasseRepository classeRepository;

    @Override
    public boolean create(Classe classe) {
        classeRepository.save(classe);
        return true;
    }

    @Override
    public List<ClasseDTO> readAll() {
        return DTOListMapper.mapClasse(classeRepository.findAll());
    }

    @Override
    public ClasseDTO read(Integer id) {
        Optional<Classe> t = classeRepository.findById(id);
        return new ClasseDTO(t.orElse(null));
    }

    @Override
    public boolean update(Classe classe) {
        classeRepository.save(classe);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        classeRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ClasseDTO> searchByNom(String nom) {
        return DTOListMapper.mapClasse(classeRepository.findByNomContains(nom));
    }
}
