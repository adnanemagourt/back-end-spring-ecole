package com.example.back.services.Impl;

import com.example.back.DTO.*;
import com.example.back.entities.Matiere;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.EmptyFieldsException;
import com.example.back.exceptions.NotExistsException;
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
    public MatiereDTO create(Matiere matiere) throws Exception {
        if (saveCheck(matiere)) matiereRepository.save(matiere);
        return new MatiereDTO(matiere);
    }

    @Override
    public boolean update(Matiere matiere) throws Exception {
        Matiere oldMatiere = matiereRepository.findById(matiere.getId()).orElseThrow(() -> new Exception("matiere id n'existe pas"));
        if (saveCheck(matiere)) {
            oldMatiere.setNom(matiere.getNom());
            matiereRepository.save(oldMatiere);
            return true;
        }
        return false;
    }

    private boolean saveCheck(Matiere matiere) throws Exception {
        if (matiere.getNom() == null || matiere.getNom().isEmpty()) {
            throw new EmptyFieldsException(List.of("nom"), "matiere");
        }
        if (matiereRepository.existsByNom(matiere.getNom())) {
            throw new AlreadyExistsException(List.of("nom"), "matiere");
        }
        return true;
    }

    @Override
    public List<MatiereDTO> readAll() {
        return DTOListMapper.mapMatiere(matiereRepository.findAll());
    }

    @Override
    public MatiereDTO read(Integer id) throws Exception {
        Optional<Matiere> t = matiereRepository.findById(id);
        return new MatiereDTO(t.orElseThrow(() -> new NotExistsException(List.of("id"), "matiere")));
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if (!matiereRepository.existsById(id)) {
            throw new NotExistsException(List.of("id"), "matiere");
        }
        matiereRepository.deleteById(id);
        return true;
    }

    @Override
    public List<MatiereDTO> searchByNom(String nom) {
        return DTOListMapper.mapMatiere(matiereRepository.findByNomContains(nom));

    }
}
