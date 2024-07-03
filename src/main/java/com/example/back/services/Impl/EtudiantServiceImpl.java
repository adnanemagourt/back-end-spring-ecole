package com.example.back.services.Impl;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.EtudiantDTO;
import com.example.back.entities.Etudiant;
import com.example.back.repository.EtudiantRepository;
import com.example.back.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public boolean create(Etudiant etudiant) {
        if(etudiantRepository.findEtudiantByEmail(etudiant.getEmail()) != null){
            return false;
        }
        etudiantRepository.save(etudiant);
        return true;
    }

    @Override
    public List<EtudiantDTO> readAll() {
        return DTOListMapper.mapEtudiant(etudiantRepository.findBy());
    }

    @Override
    public EtudiantDTO read(Integer id) {
        Optional<Etudiant> t = etudiantRepository.findById(id);
        return new EtudiantDTO(t.orElse(null));
    }

    @Override
    public boolean update(Etudiant etudiant) {
        etudiantRepository.save(etudiant);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        etudiantRepository.deleteById(id);
        return true;
    }

    @Override
    public List<EtudiantDTO> searchByNom(String nom) {
        return DTOListMapper.mapEtudiant(etudiantRepository.findByNomContains(nom));
    }

    @Override
    public List<EtudiantDTO> getByClasseId(Integer classeId) {
        return DTOListMapper.mapEtudiant(etudiantRepository.findEtudiantsByClasse_Id(classeId));
    }

    @Override
    public List<EtudiantDTO> getByProfesseurId(Integer professeurId) {
        return DTOListMapper.mapEtudiant(etudiantRepository.getEtudiantsByProfesseurid(professeurId));
    }

    @Override
    public EtudiantDTO getByEmail(String email) {
        return new EtudiantDTO(etudiantRepository.findEtudiantByEmail(email));
    }
}
