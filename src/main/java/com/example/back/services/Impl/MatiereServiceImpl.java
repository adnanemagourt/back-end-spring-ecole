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
    public boolean create(Matiere matiere) throws Exception {
        return saveCheck(matiere);
    }

    @Override
    public boolean update(Matiere matiere) throws Exception {
        if(!matiereRepository.existsById(matiere.getId())){
            throw new Exception("matiere id n'existe pas");
        }
        return saveCheck(matiere);
    }

    private boolean saveCheck(Matiere matiere) throws Exception {
        if(matiere.getNom() == null || matiere.getNom().isEmpty()){
            throw new Exception("important field empty");
        }
        if(matiereRepository.findByNom(matiere.getNom()) != null){
            throw new Exception("matiere nom already exists");
        }
        matiereRepository.save(matiere);
        return true;
    }

    @Override
    public List<MatiereDTO> readAll() {
        return DTOListMapper.mapMatiere(matiereRepository.findAll());
    }

    @Override
    public MatiereDTO read(Integer id) throws Exception {
        Optional<Matiere> t = matiereRepository.findById(id);
        return new MatiereDTO(t.orElseThrow(()->new Exception("id not found")));
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if(!matiereRepository.existsById(id)){
            throw new Exception("id not found");
        }
        matiereRepository.deleteById(id);
        return true;
    }

    @Override
    public List<MatiereDTO> searchByNom(String nom) {
        return DTOListMapper.mapMatiere(matiereRepository.findByNomContains(nom));

    }
}
