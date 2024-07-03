package com.example.back.services.Impl;

import com.example.back.DTO.*;
import com.example.back.entities.Matiere;
import com.example.back.repository.MatiereRepository;
import com.example.back.services.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatiereServiceImpl implements MatiereService {

    @Autowired
    private MatiereRepository matiereRepository;

    @Override
    public boolean create(Matiere matiere) {
        matiereRepository.save(matiere);
        return true;
    }

    @Override
    public boolean update(Matiere matiere) {
        matiereRepository.save(matiere);
        return true;
    }

    @Override
    public List<MatiereDTO> readAll() {
        return DTOListMapper.mapMatiere(matiereRepository.findBy());
    }

    @Override
    public MatiereDTO read(Integer id) {
        Optional<Matiere> t = matiereRepository.findById(id);
        return new MatiereDTO(t.orElse(null));
    }

    @Override
    public boolean delete(Integer id) {
        matiereRepository.deleteById(id);
        return true;
    }

    @Override
    public List<MatiereDTO> searchByNom(String nom) {
        return DTOListMapper.mapMatiere(matiereRepository.findByNomContains(nom));

    }
}
